package org.commerce.graphql.model;

import de.hybris.platform.core.model.user.CustomerModel;
import graphql.schema.GraphQLObjectType;

import java.lang.reflect.Field;

import static graphql.Scalars.GraphQLString;
import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;

public class CustomerGraphQLObjectType {

    private static volatile GraphQLObjectType UstaObjectInstance = buildUSTACustomerGraphQLObject();

    private CustomerGraphQLObjectType() {
    }

    private static GraphQLObjectType buildUSTACustomerGraphQLObject() {
        GraphQLObjectType.Builder builder = GraphQLObjectType.newObject().name(CustomerModel._TYPECODE);
        for (Field field : CustomerModel.class.getDeclaredFields()) {
            builder.field(
                    newFieldDefinition().name(field.getName().toLowerCase()).type(GraphQLString)
            );
        }
        return builder.build();
    }

    public static GraphQLObjectType getInstance() {
        return UstaObjectInstance;
    }
}
