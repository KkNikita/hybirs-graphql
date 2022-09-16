package org.commerce.graphql.fetcher;

import de.hybris.platform.core.model.user.CustomerModel;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

import java.util.Collections;
import java.util.List;

public class GraphQLDataFetchers implements DataFetcher<CustomerModel> {

    private static List<CustomerModel> customerModels = Collections.singletonList(new CustomerModel());

    @Override
    public CustomerModel get(DataFetchingEnvironment dataFetchingEnvironment) throws Exception {
        String customerUID = dataFetchingEnvironment.getArgument("UAID");
        return customerModels
                .stream()
                .filter(customer -> customer.getUid().equals(customerUID))
                .findFirst()
                .orElse(null);
    }
}


