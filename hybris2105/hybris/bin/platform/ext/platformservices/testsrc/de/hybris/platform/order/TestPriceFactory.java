/*
 * Copyright (c) 2021 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.order;

import de.hybris.platform.core.PK;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.order.AbstractOrder;
import de.hybris.platform.jalo.order.AbstractOrderEntry;
import de.hybris.platform.jalo.order.price.JaloPriceFactoryException;
import de.hybris.platform.jalo.order.price.PriceFactory;
import de.hybris.platform.jalo.order.price.ProductPriceInformations;
import de.hybris.platform.jalo.product.Product;
import de.hybris.platform.jalo.user.User;
import de.hybris.platform.order.exceptions.CalculationException;
import de.hybris.platform.order.strategies.calculation.FindDiscountValuesStrategy;
import de.hybris.platform.order.strategies.calculation.FindPriceStrategy;
import de.hybris.platform.order.strategies.calculation.FindTaxValuesStrategy;
import de.hybris.platform.order.strategies.calculation.ServiceLayerOnlyCalculationVerifier;
import de.hybris.platform.util.DiscountValue;
import de.hybris.platform.util.PriceValue;
import de.hybris.platform.util.TaxValue;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Price factory implementation for testing order calculation only. It allows to specify prices, taxes and discounts per
 * order/cart entry or per product.
 * <p/>
 * Please not that price info methods are not implemented yet!
 * <p/>
 * Use like this:
 *
 * <pre>
 * Cart = ...
 * CartEntry e1 = ...
 * CartEntry e2 = ...
 *
 * TestPriceFactory pf = new TestPriceFactory();
 * jaloSession.getSessionContext().setPriceFactory(pf);
 *
 * // e1 = 5 EUR
 * pf.setBasePrice(e1, new PriceValue(&quot;EUR&quot;, 5, cart.isNet()));
 * // e1 = VAT_FULL 19%
 * pf.setTaxes(e1, new TaxValue(&quot;VAT_FULL&quot;, 19, false, null));
 *
 * // e2 = 1.5 EUR
 * pf.setBasePrice(e2, new PriceValue(&quot;EUR&quot;, 1.5, cart.isNet()));
 * // e2 = VAT_HALF 7%
 * pf.setTaxes(e2, new TaxValue(&quot;VAT_HALF&quot;, 7, false, null));
 *
 * cart.calculate();
 * </pre>
 */
public class TestPriceFactory
		implements PriceFactory, FindPriceStrategy, FindDiscountValuesStrategy, FindTaxValuesStrategy,
		ServiceLayerOnlyCalculationVerifier//, ForceFindStrategyWithCurrentPriceFactory
{
	private final Map<AbstractOrderEntry, PriceValue> basePrices = new HashMap<AbstractOrderEntry, PriceValue>();
	private final Map<AbstractOrderEntry, List<TaxValue>> taxes = new HashMap<AbstractOrderEntry, List<TaxValue>>();
	private final Map<AbstractOrderEntry, List<DiscountValue>> discounts = new HashMap<AbstractOrderEntry, List<DiscountValue>>();
	private final Map<AbstractOrder, List<DiscountValue>> globalDiscounts = new HashMap<AbstractOrder, List<DiscountValue>>();

	private final Map<Product, PriceValue> basePrices2 = new HashMap<Product, PriceValue>();
	private final Map<Product, List<TaxValue>> taxes2 = new HashMap<Product, List<TaxValue>>();
	private final Map<Product, List<DiscountValue>> discounts2 = new HashMap<Product, List<DiscountValue>>();

	//---
	private final Map<PK, PriceValue> basePricesPK = new HashMap<PK, PriceValue>();
	private final Map<PK, List<TaxValue>> taxesPK = new HashMap<PK, List<TaxValue>>();
	private final Map<PK, List<DiscountValue>> discountsPK = new HashMap<PK, List<DiscountValue>>();
	private final Map<PK, List<DiscountValue>> globalDiscountsPK = new HashMap<PK, List<DiscountValue>>();

	private final Map<PK, PriceValue> basePrices2PK = new HashMap<PK, PriceValue>();
	private final Map<PK, List<TaxValue>> taxes2PK = new HashMap<PK, List<TaxValue>>();
	private final Map<PK, List<DiscountValue>> discounts2PK = new HashMap<PK, List<DiscountValue>>();

	public void clearAll()
	{
		basePrices.clear();
		taxes.clear();
		discounts.clear();
		globalDiscounts.clear();
		basePrices2.clear();
		taxes2.clear();
		discounts2.clear();

		basePricesPK.clear();
		taxesPK.clear();
		discountsPK.clear();
		globalDiscountsPK.clear();
		basePrices2PK.clear();
		taxes2PK.clear();
		discounts2PK.clear();
	}

	public void setBasePrice(final AbstractOrderEntry entry, final PriceValue priceValue)
	{
		basePrices.put(entry, priceValue);
		basePricesPK.put(entry.getPK(), priceValue);
	}

	public void setBasePrice(final Product product, final PriceValue priceValue)
	{
		basePrices2.put(product, priceValue);
		basePrices2PK.put(product.getPK(), priceValue);

	}

	public void setTaxes(final AbstractOrderEntry entry, final TaxValue... taxValues)
	{
		setTaxes(entry, Arrays.asList(taxValues));
	}

	public void setTaxes(final AbstractOrderEntry entry, final List<TaxValue> taxValues)
	{
		taxes.put(entry, taxValues);
		taxesPK.put(entry.getPK(), taxValues);
	}

	public void setTaxes(final Product product, final List<TaxValue> taxValues)
	{
		taxes2.put(product, taxValues);
		taxes2PK.put(product.getPK(), taxValues);
	}

	public void setDiscounts(final AbstractOrderEntry entry, final DiscountValue... discountValues)
	{
		setDiscounts(entry, Arrays.asList(discountValues));
	}

	public void setDiscounts(final AbstractOrderEntry entry, final List<DiscountValue> discountValues)
	{
		discounts.put(entry, discountValues);
		discountsPK.put(entry.getPK(), discountValues);
	}

	public void setDiscounts(final Product product, final List<DiscountValue> discountValues)
	{
		discounts2.put(product, discountValues);
		discounts2PK.put(product.getPK(), discountValues);
	}

	public void setGLobalDiscounts(final AbstractOrder abstractOrder, final DiscountValue... discountValues)
	{
		setGLobalDiscounts(abstractOrder, Arrays.asList(discountValues));
	}

	public void setGLobalDiscounts(final AbstractOrder abstractOrder, final List<DiscountValue> discountValues)
	{
		globalDiscounts.put(abstractOrder, discountValues);
		globalDiscountsPK.put(abstractOrder.getPK(), discountValues);
	}


	@Override
	public PriceValue getBasePrice(final AbstractOrderEntry entry) throws JaloPriceFactoryException
	{
		PriceValue priceValue = basePrices.get(entry);
		if (priceValue == null)
		{
			priceValue = basePrices2.get(entry.getProduct());
			if (priceValue == null)
			{
				throw new JaloPriceFactoryException("no price for " + System.identityHashCode(entry), 0);
			}
		}
		return priceValue;
	}

	@Override
	public List getDiscountValues(final AbstractOrderEntry entry) throws JaloPriceFactoryException
	{
		List<DiscountValue> discountValues = discounts.get(entry);
		if (discountValues == null)
		{
			discountValues = discounts2.get(entry.getProduct());
		}
		return discountValues != null ? discountValues : Collections.EMPTY_LIST;
	}

	@Override
	public List getDiscountValues(final AbstractOrder order) throws JaloPriceFactoryException
	{
		final List<DiscountValue> discountValues = discounts.get(order);
		return discountValues != null ? discountValues : Collections.EMPTY_LIST;
	}

	@Override
	public Collection getTaxValues(final AbstractOrderEntry entry) throws JaloPriceFactoryException
	{
		List<TaxValue> taxValues = taxes.get(entry);
		if (taxValues == null)
		{
			taxValues = taxes2.get(entry.getProduct());
		}
		return taxValues != null ? taxValues : Collections.EMPTY_LIST;
	}

	@Override
	public boolean isNetUser(final User user)
	{
		return false;
	}

	@Override
	public ProductPriceInformations getAllPriceInformations(final SessionContext ctx, final Product product, final Date date,
	                                                        final boolean net) throws JaloPriceFactoryException
	{
		throw new UnsupportedOperationException();
	}


	@Override
	public List getProductDiscountInformations(final SessionContext ctx, final Product product, final Date date,
	                                           final boolean net)
			throws JaloPriceFactoryException
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public List getProductPriceInformations(final SessionContext ctx, final Product product, final Date date, final boolean net)
			throws JaloPriceFactoryException
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public List getProductTaxInformations(final SessionContext ctx, final Product product, final Date date)
			throws JaloPriceFactoryException
	{
		throw new UnsupportedOperationException();
	}


	@Override
	public Collection<TaxValue> findTaxValues(final AbstractOrderEntryModel entry) throws CalculationException
	{

		List<TaxValue> taxValues = taxesPK.get(entry.getPk());
		if (taxValues == null)
		{
			taxValues = taxes2PK.get(entry.getProduct().getPk());
		}
		return taxValues != null ? taxValues : Collections.EMPTY_LIST;
	}

	@Override
	public List<DiscountValue> findDiscountValues(final AbstractOrderEntryModel entry) throws CalculationException
	{
		List<DiscountValue> discountValues = discountsPK.get(entry.getPk());
		if (discountValues == null)
		{
			discountValues = discounts2PK.get(entry.getProduct().getPk());
		}
		return discountValues != null ? discountValues : Collections.EMPTY_LIST;
	}

	@Override
	public List<DiscountValue> findDiscountValues(final AbstractOrderModel order) throws CalculationException
	{
		final List<DiscountValue> discountValues = discountsPK.get(order.getPk());
		return discountValues != null ? discountValues : Collections.EMPTY_LIST;
	}

	@Override
	public PriceValue findBasePrice(final AbstractOrderEntryModel entry) throws CalculationException
	{

		PriceValue priceValue = basePricesPK.get(entry.getPk());
		if (priceValue == null)
		{
			priceValue = basePrices2PK.get(entry.getProduct().getPk());
			if (priceValue == null)
			{
				throw new IllegalStateException("no price for " + System.identityHashCode(entry));
			}
		}
		return priceValue;
	}
}
