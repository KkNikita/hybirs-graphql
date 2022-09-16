package org.commerce.graphql;

import com.fasterxml.jackson.databind.ObjectMapper;
import graphql.ExecutionResult;
import graphql.spring.web.servlet.ExecutionResultHandler;

import java.util.concurrent.CompletableFuture;

public class DefaultExecutionResultHandler implements ExecutionResultHandler {
    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Object handleExecutionResult(CompletableFuture<ExecutionResult> executionResultCF) {
        return executionResultCF.thenApply(ExecutionResult::toSpecification);
    }
}
