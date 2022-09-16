package org.commerce.graphql.component;

import de.hybris.platform.core.model.user.CustomerModel;
import graphql.ExecutionInput;
import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.schema.GraphQLFieldDefinition;
import graphql.schema.GraphQLObjectType;
import graphql.schema.GraphQLSchema;
import graphql.spring.web.servlet.ExecutionInputCustomizer;
import graphql.spring.web.servlet.GraphQLInvocation;
import graphql.spring.web.servlet.GraphQLInvocationData;
import org.commerce.graphql.fetcher.GraphQLDataFetchers;
import org.commerce.graphql.model.CustomerGraphQLObjectType;
import org.dataloader.DataLoaderRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.WebRequest;

import java.util.concurrent.CompletableFuture;

public class DefaultGraphQLInvocation implements GraphQLInvocation {

    private GraphQL graphQL;
    private DataLoaderRegistry dataLoaderRegistry;
    private ExecutionInputCustomizer executionInputCustomizer;

    @Autowired
    public DefaultGraphQLInvocation(ExecutionInputCustomizer executionInputCustomizer) {
        this.executionInputCustomizer = executionInputCustomizer;
    }


    public CompletableFuture<ExecutionResult> invoke(GraphQLInvocationData invocationData, WebRequest webRequest) {

        GraphQLObjectType queryType = GraphQLObjectType.newObject()
                .name("userQuery")
                .field(GraphQLFieldDefinition.newFieldDefinition()
                        .type(CustomerGraphQLObjectType.getInstance())
                        .name(CustomerModel._TYPECODE)
                        .dataFetcher(new GraphQLDataFetchers())
                )
                .build();

        GraphQLSchema graphQLSchema = GraphQLSchema.newSchema().query(queryType).build();
        this.graphQL = GraphQL.newGraphQL(graphQLSchema).build();

        ExecutionInput.Builder executionInputBuilder = ExecutionInput.newExecutionInput().query(invocationData.getQuery()).operationName(invocationData.getOperationName()).variables(invocationData.getVariables());
        if (this.dataLoaderRegistry != null) {
            executionInputBuilder.dataLoaderRegistry(this.dataLoaderRegistry);
        }

        ExecutionInput executionInput = executionInputBuilder.build();
        CompletableFuture<ExecutionInput> customizedExecutionInput = this.executionInputCustomizer.customizeExecutionInput(executionInput, webRequest);
        GraphQL var10001 = this.graphQL;
        var10001.getClass();
        return customizedExecutionInput.thenCompose(var10001::executeAsync);
    }
}
