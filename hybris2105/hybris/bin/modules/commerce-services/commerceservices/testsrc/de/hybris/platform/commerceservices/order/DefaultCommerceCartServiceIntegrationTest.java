/*
 * Copyright (c) 2019 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.commerceservices.order;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.commerceservices.impersonation.ImpersonationContext;
import de.hybris.platform.commerceservices.impersonation.ImpersonationService;
import de.hybris.platform.commerceservices.order.impl.DefaultCommerceCartService;
import de.hybris.platform.commerceservices.service.data.CommerceCartMetadataParameter;
import de.hybris.platform.commerceservices.service.data.CommerceCartParameter;
import de.hybris.platform.commerceservices.service.data.CommerceSaveCartParameter;
import de.hybris.platform.commerceservices.service.data.CommerceSaveCartResult;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.core.model.product.UnitModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.europe1.model.PriceRowModel;
import de.hybris.platform.jalo.CoreBasicDataCreator;
import de.hybris.platform.jalo.JaloSession;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.order.Cart;
import de.hybris.platform.jalo.order.CartEntry;
import de.hybris.platform.order.CartService;
import de.hybris.platform.order.exceptions.CalculationException;
import de.hybris.platform.product.ProductService;
import de.hybris.platform.product.UnitService;
import de.hybris.platform.promotions.jalo.ProductPercentageDiscountPromotion;
import de.hybris.platform.promotions.jalo.PromotionOrderEntryAdjustAction;
import de.hybris.platform.promotions.jalo.PromotionResult;
import de.hybris.platform.promotions.jalo.PromotionsManager;
import de.hybris.platform.servicelayer.ServicelayerTest;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.session.SessionService;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.site.BaseSiteService;
import de.hybris.platform.tx.Transaction;

import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;


/**
 * Integration test suite for {@link DefaultCommerceCartService}
 */
@IntegrationTest
public class DefaultCommerceCartServiceIntegrationTest extends ServicelayerTest
{
	private static final Logger LOG = Logger.getLogger(DefaultCommerceCartServiceIntegrationTest.class);
	private static final String TEST_BASESITE_UID = "testSite";
	private static final String UNAVAILABLE_PRODUCT_CODE = "unavailable";

	@Resource
	private CommerceCartService commerceCartService;

	@Resource
	private UserService userService;

	@Resource
	private ModelService modelService;

	@Resource
	private ProductService productService;

	@Resource
	private CatalogVersionService catalogVersionService;

	@Resource
	private CartService cartService;

	@Resource
	private CommerceSaveCartService commerceSaveCartService;

	@Resource
	private SessionService sessionService;

	@Resource
	private UnitService unitService;

	@Resource
	private BaseSiteService baseSiteService;

	@Resource
	private ImpersonationService impersonationService;

	@Resource(name = "processengine.taskExecutionTransactionTemplate")
	private TransactionTemplate transactionTemplate;


	@Before
	public void setUp() throws Exception
	{
		// final Create data for tests
		LOG.info("Creating data for commerce cart ..");
		userService.setCurrentUser(userService.getAdminUser());
		final long startTime = System.currentTimeMillis();
		new CoreBasicDataCreator().createEssentialData(Collections.EMPTY_MAP, null);
		// importing test csv
		importCsv("/commerceservices/test/testCommerceCart.csv", "utf-8");

		baseSiteService.setCurrentBaseSite(baseSiteService.getBaseSiteForUID(TEST_BASESITE_UID), false);

		LOG.info("Finished data for commerce cart " + (System.currentTimeMillis() - startTime) + "ms");
	}

	@Test
	public void testAddToCart() throws CommerceCartModificationException
	{
		final CatalogVersionModel catalogVersionModel = catalogVersionService.getCatalogVersion("testCatalog", "Online");
		final ProductModel productModel = productService.getProductForCode(catalogVersionModel, "HW1210-3423");
		final UnitModel unitModel = unitService.getUnitForCode("pieces");
		final UserModel ahertz = userService.getUserForUID("ahertz");
		final Collection<CartModel> cartModels = ahertz.getCarts();


		Assert.assertEquals(1, cartModels.size());
		final CartModel ahertzCart = cartModels.iterator().next();
		final CommerceCartParameter parameter = new CommerceCartParameter();
		parameter.setEnableHooks(true);
		parameter.setCart(ahertzCart);
		parameter.setProduct(productModel);
		parameter.setQuantity(3);
		parameter.setUnit(unitModel);
		parameter.setCreateNewEntry(false);

		// Add new entry
		commerceCartService.addToCart(parameter);

		Assert.assertEquals(2, ahertzCart.getEntries().size());

		for (final AbstractOrderEntryModel cartEntryModel : ahertzCart.getEntries())
		{
			if (cartEntryModel.getQuantity().intValue() == 3)
			{
				Assert.assertEquals("HW1210-3423", cartEntryModel.getProduct().getCode());
				Assert.assertEquals(unitModel, cartEntryModel.getUnit());
			}
		}
	}

	@Test
	public void testAddToCartInsufficientStock() throws CommerceCartModificationException
	{
		final CatalogVersionModel catalogVersionModel = catalogVersionService.getCatalogVersion("testCatalog", "Online");
		final ProductModel productModel = productService.getProductForCode(catalogVersionModel, "HW1210-3423");
		final UnitModel unitModel = unitService.getUnitForCode("pieces");
		final UserModel ahertz = userService.getUserForUID("ahertz");
		final Collection<CartModel> cartModels = ahertz.getCarts();

		Assert.assertEquals(1, cartModels.size());
		final CartModel ahertzCart = cartModels.iterator().next();
		final CommerceCartParameter parameter = new CommerceCartParameter();
		parameter.setEnableHooks(true);
		parameter.setCart(ahertzCart);
		parameter.setProduct(productModel);
		parameter.setQuantity(12);
		parameter.setUnit(unitModel);
		parameter.setCreateNewEntry(false);

		// Add new entry
		final CommerceCartModification result = commerceCartService.addToCart(parameter);
		Assert.assertEquals(10L, result.getQuantityAdded());
	}

	@Test
	public void testAddToCartUnavailable() throws CommerceCartModificationException
	{
		final ProductModel productModel = modelService.create(ProductModel.class);
		productModel.setCode(UNAVAILABLE_PRODUCT_CODE);
		productModel.setSupercategories(Collections.emptyList());
		final UnitModel unitModel = unitService.getUnitForCode("pieces");
		final UserModel ahertz = userService.getUserForUID("ahertz");
		final Collection<CartModel> cartModels = ahertz.getCarts();

		Assert.assertEquals(1, cartModels.size());
		final CartModel ahertzCart = cartModels.iterator().next();
		final CommerceCartParameter parameter = new CommerceCartParameter();
		parameter.setEnableHooks(true);
		parameter.setCart(ahertzCart);
		parameter.setProduct(productModel);
		parameter.setQuantity(1);
		parameter.setUnit(unitModel);
		parameter.setCreateNewEntry(false);

		// Add new entry
		final CommerceCartModification result = commerceCartService.addToCart(parameter);
		Assert.assertEquals(CommerceCartModificationStatus.UNAVAILABLE, result.getStatusCode());
		Assert.assertEquals(0L, result.getQuantityAdded());
		Assert.assertEquals(UNAVAILABLE_PRODUCT_CODE, result.getEntry().getProduct().getCode());
		Assert.assertEquals(null, result.getEntry().getBasePrice());
		Assert.assertEquals(null, result.getEntry().getTotalPrice());
	}

	@Test
	public void testAddToCartWhenNoCartPassed() throws CommerceCartModificationException
	{
		final CatalogVersionModel catalogVersionModel = catalogVersionService.getCatalogVersion("testCatalog", "Online");
		final ProductModel productModel = productService.getProductForCode(catalogVersionModel, "HW1210-3423");
		final UnitModel unitModel = unitService.getUnitForCode("pieces");
		final UserModel ahertz = userService.getUserForUID("ahertz");
		final Collection<CartModel> cartModels = ahertz.getCarts();

		Assert.assertEquals(1, cartModels.size());

		final CartModel ahertzCart = cartModels.iterator().next();
		cartService.setSessionCart(ahertzCart);
		// Add new entry

		final CommerceCartParameter parameter = new CommerceCartParameter();
		parameter.setEnableHooks(true);
		parameter.setCart(ahertzCart);
		parameter.setProduct(productModel);
		parameter.setQuantity(3);
		parameter.setUnit(unitModel);
		parameter.setCreateNewEntry(false);

		commerceCartService.addToCart(parameter);

		Assert.assertEquals(2, ahertzCart.getEntries().size());

		for (final AbstractOrderEntryModel cartEntryModel : ahertzCart.getEntries())
		{
			if (cartEntryModel.getQuantity().intValue() == 3)
			{
				Assert.assertEquals("HW1210-3423", cartEntryModel.getProduct().getCode());
				Assert.assertEquals(unitModel, cartEntryModel.getUnit());
			}
		}
	}

	@Test
	public void testAddToCartToTheSameEntry() throws CommerceCartModificationException
	{
		final CatalogVersionModel catalogVersionModel = catalogVersionService.getCatalogVersion("testCatalog", "Online");
		final ProductModel productModel = productService.getProductForCode(catalogVersionModel, "HW1210-3423");
		final UnitModel unitModel = unitService.getUnitForCode("pieces");
		final UserModel ahertz = userService.getUserForUID("ahertz");
		final Collection<CartModel> cartModels = ahertz.getCarts();

		Assert.assertEquals(1, cartModels.size());
		final CartModel ahertzCart = cartModels.iterator().next();
		final CommerceCartParameter parameter = new CommerceCartParameter();
		parameter.setEnableHooks(true);
		parameter.setCart(ahertzCart);
		parameter.setProduct(productModel);
		parameter.setQuantity(3);
		parameter.setUnit(unitModel);
		parameter.setCreateNewEntry(false);

		// Add the same product - increase quantity
		commerceCartService.addToCart(parameter);
		parameter.setQuantity(5);
		commerceCartService.addToCart(parameter);

		Assert.assertEquals(2, ahertzCart.getEntries().size());

		for (final AbstractOrderEntryModel cartEntryModel : ahertzCart.getEntries())
		{
			if (cartEntryModel.getProduct().getCode().equals("HW1210-3423"))
			{
				Assert.assertEquals(8, cartEntryModel.getQuantity().longValue());
			}
		}
	}

	@Test
	public void testAddToCartForceNewEntry() throws CommerceCartModificationException
	{
		final CatalogVersionModel catalogVersionModel = catalogVersionService.getCatalogVersion("testCatalog", "Online");
		final ProductModel productModel = productService.getProductForCode(catalogVersionModel, "HW1210-3423");
		final UnitModel unitModel = unitService.getUnitForCode("pieces");
		final UserModel ahertz = userService.getUserForUID("ahertz");
		final Collection<CartModel> cartModels = ahertz.getCarts();

		Assert.assertEquals(1, cartModels.size());
		final CartModel ahertzCart = cartModels.iterator().next();

		final CommerceCartParameter parameter = new CommerceCartParameter();
		parameter.setEnableHooks(true);
		parameter.setCart(ahertzCart);
		parameter.setProduct(productModel);
		parameter.setQuantity(10);
		parameter.setUnit(unitModel);
		parameter.setCreateNewEntry(true);

		// Add the same product but force new entry
		commerceCartService.addToCart(parameter);

		Assert.assertEquals(2, ahertzCart.getEntries().size());

		for (final AbstractOrderEntryModel cartEntryModel : ahertzCart.getEntries())
		{
			if (cartEntryModel.getQuantity().intValue() == 9)
			{
				Assert.assertEquals("HW1210-3423", cartEntryModel.getProduct().getCode());
			}
		}
	}

	@Test(expected = CommerceCartModificationException.class)
	public void shouldReportOnAddingToNonExistingEntry() throws CommerceCartModificationException
	{
		final CatalogVersionModel catalogVersionModel = catalogVersionService.getCatalogVersion("testCatalog", "Online");
		final ProductModel productModel = productService.getProductForCode(catalogVersionModel, "HW1210-3423");
		final UnitModel unitModel = unitService.getUnitForCode("pieces");
		final UserModel ahertz = userService.getUserForUID("ahertz");
		final Collection<CartModel> cartModels = ahertz.getCarts();

		Assert.assertEquals(1, cartModels.size());
		final CartModel ahertzCart = cartModels.iterator().next();
		Assert.assertEquals(1, ahertzCart.getEntries().size());

		final CommerceCartParameter parameter = new CommerceCartParameter();
		parameter.setEnableHooks(true);
		parameter.setCart(ahertzCart);
		parameter.setProduct(productModel);
		parameter.setQuantity(10);
		parameter.setUnit(unitModel);
		parameter.setEntryNumber(ahertzCart.getEntries().get(0).getEntryNumber().intValue() + 2);

		commerceCartService.addToCart(parameter);
	}

	@Test
	public void testCalculateCart()
	{
		final UserModel promoted = userService.getUserForUID("promoted");
		final Collection<CartModel> cartModels = promoted.getCarts();

		Assert.assertEquals(1, cartModels.size());
		final CartModel promotedCart = cartModels.iterator().next();

		final CommerceCartParameter parameter = new CommerceCartParameter();
		parameter.setEnableHooks(true);
		parameter.setCart(promotedCart);
		commerceCartService.calculateCart(parameter);
		Assert.assertEquals(Boolean.TRUE, promotedCart.getCalculated());
		Assert.assertEquals(Double.valueOf(827.95), promotedCart.getTotalPrice());
	}

	@Test
	public void testCalculateCartEntriesAfterAddingToCart() throws CommerceCartModificationException
	{
		final CatalogVersionModel catalogVersionModel = catalogVersionService.getCatalogVersion("testCatalog", "Online");
		final ProductModel productModel = productService.getProductForCode(catalogVersionModel, "HW1210-3423");
		final UnitModel unitModel = unitService.getUnitForCode("pieces");
		final UserModel ahertz = userService.getUserForUID("ahertz");
		final Collection<CartModel> cartModels = ahertz.getCarts();
		Assert.assertEquals(1, cartModels.size());

		final CartModel ahertzCart = cartModels.iterator().next();
		CommerceCartParameter parameter = new CommerceCartParameter();
		parameter.setEnableHooks(true);
		parameter.setCart(ahertzCart);
		commerceCartService.calculateCart(parameter);
		Assert.assertEquals(1, ahertzCart.getEntries().size());
		Assert.assertEquals(Boolean.TRUE, ahertzCart.getCalculated());
		Assert.assertEquals(Double.valueOf(115.9), ahertzCart.getTotalPrice());

		parameter = new CommerceCartParameter();
		parameter.setEnableHooks(true);
		parameter.setCart(ahertzCart);
		parameter.setProduct(productModel);
		parameter.setQuantity(3);
		parameter.setUnit(unitModel);
		parameter.setCreateNewEntry(false);
		commerceCartService.addToCart(parameter);
		Assert.assertEquals(2, ahertzCart.getEntries().size());

		parameter.setQuantity(5);
		commerceCartService.addToCart(parameter);
		Assert.assertEquals(2, ahertzCart.getEntries().size());

		for (final AbstractOrderEntryModel cartEntryModel : ahertzCart.getEntries())
		{
			if (cartEntryModel.getProduct().getCode().equals("HW1210-3423"))
			{
				Assert.assertEquals(8, cartEntryModel.getQuantity().longValue());
				Assert.assertEquals(Double.valueOf(543.6), cartEntryModel.getTotalPrice());
			}
		}
		Assert.assertEquals(Double.valueOf(659.5), ahertzCart.getTotalPrice());
	}

	@Ignore("BIT-2420 (explicitly testing promotion functionality)")
	@Test
	public void testPreviewTime() throws CalculationException
	{
		final UserModel promoted = userService.getUserForUID("promoted");
		final Collection<CartModel> cartModels = promoted.getCarts();
		Assert.assertEquals(1, cartModels.size());
		final CartModel promotedCart = cartModels.iterator().next();
		sessionService.setAttribute("previewTime", new Date());
		final CommerceCartParameter parameter = new CommerceCartParameter();
		parameter.setEnableHooks(true);
		parameter.setCart(promotedCart);
		commerceCartService.calculateCart(parameter);
		Assert.assertEquals(Boolean.TRUE, promotedCart.getCalculated());
		Assert.assertEquals(Double.valueOf(163.95), promotedCart.getTotalPrice());

		commerceCartService.recalculateCart(parameter);

		Assert.assertEquals(Boolean.TRUE, promotedCart.getCalculated());
		Assert.assertEquals(Double.valueOf(163.95), promotedCart.getTotalPrice());
		// set a date after promotion endDate
		final Calendar cal = Calendar.getInstance();
		cal.set(2401, 0, 1);
		sessionService.setAttribute(SessionContext.TIMEOFFSET, Long.valueOf(cal.getTimeInMillis() - new Date().getTime()));
		promotedCart.setCalculated(Boolean.FALSE);
		commerceCartService.calculateCart(parameter);
		Assert.assertEquals(Boolean.TRUE, promotedCart.getCalculated());
		Assert.assertEquals(Double.valueOf(827.95), promotedCart.getTotalPrice());

		commerceCartService.recalculateCart(parameter);

		Assert.assertEquals(Boolean.TRUE, promotedCart.getCalculated());
		Assert.assertEquals(Double.valueOf(827.95), promotedCart.getTotalPrice());
	}

	@Test
	public void testRecalculateCart() throws CalculationException
	{
		final UserModel promoted = userService.getUserForUID("promoted");
		final Collection<CartModel> cartModels = promoted.getCarts();

		Assert.assertEquals(1, cartModels.size());
		final CartModel promotedCart = cartModels.iterator().next();

		final CommerceCartParameter parameter = new CommerceCartParameter();
		parameter.setEnableHooks(true);
		parameter.setCart(promotedCart);
		commerceCartService.calculateCart(parameter);
		Assert.assertEquals(Boolean.TRUE, promotedCart.getCalculated());
		Assert.assertEquals(Double.valueOf(827.95), promotedCart.getTotalPrice());

		final CatalogVersionModel catalogVersionModel = catalogVersionService.getCatalogVersion("testCatalog", "Online");
		final ProductModel productModel = productService.getProductForCode(catalogVersionModel, "HW1210-3423");

		final Collection<PriceRowModel> priceRowModels = productModel.getEurope1Prices();
		final PriceRowModel priceRowModel = priceRowModels.iterator().next();
		priceRowModel.setPrice(Double.valueOf(100.0));
		modelService.save(priceRowModel);

		commerceCartService.recalculateCart(parameter);
		Assert.assertEquals(Double.valueOf(860.0), promotedCart.getTotalPrice());
	}

	@Test
	public void testRemoveAllEntries()
	{
		final UserModel abrode = userService.getUserForUID("abrode");
		final Collection<CartModel> cartModels = abrode.getCarts();

		Assert.assertEquals(1, cartModels.size());
		final CartModel abrodeCart = cartModels.iterator().next();
		final CommerceCartParameter parameter = new CommerceCartParameter();
		parameter.setEnableHooks(true);
		parameter.setCart(abrodeCart);
		commerceCartService.removeAllEntries(parameter);
		Assert.assertEquals(0, abrodeCart.getEntries().size());
	}

	@Test
	public void testUpdateQuantityForCartEntry() throws CommerceCartModificationException
	{
		final UserModel abrode = userService.getUserForUID("abrode");
		final Collection<CartModel> cartModels = abrode.getCarts();

		Assert.assertEquals(1, cartModels.size());

		final CartModel abrodeCart = cartModels.iterator().next();
		final CommerceCartParameter parameter = new CommerceCartParameter();
		parameter.setEnableHooks(true);
		parameter.setCart(abrodeCart);
		parameter.setEntryNumber(2);
		parameter.setQuantity(12);

		final CommerceCartModification result = commerceCartService.updateQuantityForCartEntry(parameter);
		Assert.assertEquals(5L, result.getQuantityAdded());

		Assert.assertEquals(Double.valueOf(795.4), abrodeCart.getTotalPrice());
	}

	@Test
	public void testUpdateQuantityForCartEntryToZero() throws CommerceCartModificationException
	{
		final UserModel abrode = userService.getUserForUID("abrode");
		final Collection<CartModel> cartModels = abrode.getCarts();

		Assert.assertEquals(1, cartModels.size());
		final CartModel abrodeCart = cartModels.iterator().next();
		final CommerceCartParameter parameter = new CommerceCartParameter();
		parameter.setEnableHooks(true);
		parameter.setCart(abrodeCart);
		parameter.setEntryNumber(2);
		parameter.setQuantity(0);

		final CommerceCartModification result = commerceCartService.updateQuantityForCartEntry(parameter);
		Assert.assertEquals(CommerceCartModificationStatus.SUCCESS, result.getStatusCode());
		Assert.assertEquals(0L, result.getQuantity());
		Assert.assertEquals("HW1210-3423", result.getEntry().getProduct().getCode());
		Assert.assertEquals(null, result.getEntry().getBasePrice());
		Assert.assertEquals(null, result.getEntry().getTotalPrice());
	}

	@Test
	public void testValidateCart() throws CommerceCartModificationException
	{
		final ProductModel productModelUnavailable = modelService.create(ProductModel.class);
		productModelUnavailable.setCode(UNAVAILABLE_PRODUCT_CODE);
		final UserModel abrode = userService.getUserForUID("abrode"); //abrode
		final Collection<CartModel> cartModels = abrode.getCarts();

		Assert.assertEquals(1, cartModels.size());
		final CartModel abrodeCart = cartModels.iterator().next();
		abrodeCart.setCalculated(true);
		final List<AbstractOrderEntryModel> cartEntries = abrodeCart.getEntries();

		Assert.assertEquals(2, cartEntries.size());
		cartEntries.iterator().next().setProduct(productModelUnavailable);

		final CommerceCartParameter parameter = new CommerceCartParameter();
		parameter.setEnableHooks(true);
		parameter.setCart(abrodeCart);
		parameter.setCreateNewEntry(false);

		final List<CommerceCartModification> resultList = commerceCartService.validateCart(parameter);
		Assert.assertEquals(1, resultList.size());

		final CommerceCartModification result = resultList.iterator().next();
		Assert.assertEquals(CommerceCartModificationStatus.UNAVAILABLE, result.getStatusCode());
		Assert.assertEquals(0L, result.getQuantityAdded());
		Assert.assertEquals(UNAVAILABLE_PRODUCT_CODE, result.getEntry().getProduct().getCode());
		Assert.assertEquals(null, result.getEntry().getBasePrice());
		Assert.assertEquals(null, result.getEntry().getTotalPrice());
	}

	@Ignore("BIT-2420 (explicitly testing promotion functionality)")
	@Test
	public void shouldCreatePromotionActionForPromotionResult() throws Exception
	{
		final UserModel ahertz = userService.getUserForUID("ahertz");
		final Collection<CartModel> cartModels = ahertz.getCarts();
		Assert.assertEquals(1, cartModels.size());
		final CartModel cart = cartModels.iterator().next();

		transactionTemplate.execute(new TransactionCallback<Object>()
		{
			@Override
			public Object doInTransaction(final TransactionStatus status)
			{

				Transaction.current().enableDelayedStore(false);

				final ImpersonationContext context = new ImpersonationContext();
				context.setOrder(cart);
				impersonationService.executeInContext(context,
						new ImpersonationService.Executor<CartModel, ImpersonationService.Nothing>()
						{
							@Override
							public CartModel execute()
							{
								final SessionContext sessionContext = JaloSession.getCurrentSession().getSessionContext();
								final ProductPercentageDiscountPromotion productPercentageDiscountPromotion = PromotionsManager
										.getInstance().createProductPercentageDiscountPromotion(sessionContext,
												Collections.singletonMap(ProductPercentageDiscountPromotion.CODE, "test"));

								final PromotionResult result = PromotionsManager.getInstance().createPromotionResult(sessionContext,
										productPercentageDiscountPromotion, (Cart) modelService.toPersistenceLayer(cart), 1.0F);

								Assert.assertNotNull("PromotionResult", result);

								final PromotionOrderEntryAdjustAction action = PromotionsManager.getInstance()
										.createPromotionOrderEntryAdjustAction(sessionContext,
												(CartEntry) modelService.toPersistenceLayer(cart.getEntries().iterator().next()), 0, 0D);
								Assert.assertNotNull("PromotionOrderEntryAdjustAction", action);

								result.addAction(sessionContext, action);


								Assert.assertTrue("Should have found one action associated to PromotionResult",
										result.getActions().size() == 1);

								return cart;
							}
						});

				return null;
			}
		});
	}

	@Test
	public void testGetCartForGuidAndSiteAndUserWithNullGuid() throws InterruptedException, CommerceSaveCartException
	{
		final UserModel john = userService.getUserForUID("john");
		final Collection<CartModel> cartModels = john.getCarts();

		Assert.assertEquals("A single cart should exist for user", 1, cartModels.size());
		final CartModel availableCart = cartModels.iterator().next();
		TimeUnit.MILLISECONDS.sleep(100);

		final CommerceSaveCartParameter commerceSaveCartParameter = new CommerceSaveCartParameter();
		commerceSaveCartParameter.setName("Saved_Cart_Name");
		commerceSaveCartParameter.setDescription("Saved_Cart_Description");

		final CartModel cartModel = modelService.create(CartModel.class);
		cartModel.setSite(availableCart.getSite());
		cartModel.setUser(john);
		cartModel.setCode("Saved_Cart_Code");
		cartModel.setCurrency(availableCart.getCurrency());
		cartModel.setDate(new Date());
		cartModel.setTotalPrice(new Double(commerceSaveCartParameter.getName().length()));
		commerceSaveCartParameter.setCart(cartModel);

		final CommerceSaveCartResult savedCartResult = commerceSaveCartService.saveCart(commerceSaveCartParameter);
		Assert.assertNotNull("Saved cart result should not be null", savedCartResult);

		final CartModel selectedCart = commerceCartService.getCartForGuidAndSiteAndUser(null, availableCart.getSite(), john);
		Assert.assertNotNull("Selected cart should not be null", selectedCart);

		Assert.assertEquals("Selected cart should the same with user's available cart", availableCart.getGuid(),
				selectedCart.getGuid());
	}

	@Test
	public void testUpdateCartMetadata()
	{
		final CommerceCartMetadataParameter metadataParameter = new CommerceCartMetadataParameter();
		final UserModel john = userService.getUserForUID("john");
		final CartModel cartModel = john.getCarts().iterator().next();

		cartModel.setName("FOO");
		cartModel.setDescription("FOO");
		cartModel.setExpirationTime(new Date());

		metadataParameter.setName(Optional.of("myQuoteName"));
		metadataParameter.setDescription(Optional.of("myQuoteDescription"));
		metadataParameter.setExpirationTime(Optional.empty());
		metadataParameter.setRemoveExpirationTime(true);
		metadataParameter.setCart(cartModel);

		commerceCartService.updateCartMetadata(metadataParameter);

		Assert.assertEquals("Name should be updated", "myQuoteName", cartModel.getName());
		Assert.assertEquals("Description should be updated", "myQuoteDescription", cartModel.getDescription());
		Assert.assertNull("Expiration time should be removed", cartModel.getExpirationTime());
	}
}
