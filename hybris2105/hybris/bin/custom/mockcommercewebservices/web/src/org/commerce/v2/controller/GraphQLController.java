package org.commerce.v2.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import graphql.ExecutionResult;
import graphql.spring.web.servlet.ExecutionResultHandler;
import graphql.spring.web.servlet.GraphQLInvocation;
import graphql.spring.web.servlet.GraphQLInvocationData;
import graphql.spring.web.servlet.JsonSerializer;
import graphql.spring.web.servlet.components.GraphQLRequestBody;
import graphql.spring.web.servlet.components.JacksonJsonSerializer;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.InvalidMediaTypeException;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Controller
@RequestMapping(value = "/{baseSiteId}/graphql")
public class GraphQLController extends BaseCommerceController {

    static String APPLICATION_GRAPHQL_VALUE = "application/graphql";
    static MediaType APPLICATION_GRAPHQL = MediaType.parseMediaType(APPLICATION_GRAPHQL_VALUE);

    @Resource(name = "defaultGraphQLInvocation")
    private GraphQLInvocation graphQLInvocation;

    @Resource(name = "executionResultHandler")
    private ExecutionResultHandler executionResultHandler;

    private JsonSerializer jsonSerializer = new JacksonJsonSerializer(new ObjectMapper());

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Object postGraphRequest(@RequestHeader(value = HttpHeaders.CONTENT_TYPE, required = false) String contentType,
                                   @RequestParam(value = "query", required = false) String query,
                                   @RequestParam(value = "operationName", required = false) String operationName,
                                   @RequestParam(value = "variables", required = false) String variablesJson,
                                   @RequestBody(required = false) String body,
                                   WebRequest webRequest) {
        MediaType mediaType = null;
        if (!StringUtils.isEmpty(contentType)) {
            try {
                mediaType = MediaType.parseMediaType(contentType);
            } catch (InvalidMediaTypeException ignore) {
            }
        }

        if (body == null) {
            body = "";
        }

        if (MediaType.APPLICATION_JSON.equalsTypeAndSubtype(mediaType)) {
            GraphQLRequestBody request = jsonSerializer.deserialize(body, GraphQLRequestBody.class);
            if (request.getQuery() == null) {
                request.setQuery("");
            }
            return executeRequest(request.getQuery(), request.getOperationName(), request.getVariables(), webRequest);
        }

        if (query != null) {
            return executeRequest(query, operationName, convertVariablesJson(variablesJson), webRequest);
        }

        if (APPLICATION_GRAPHQL.equalsTypeAndSubtype(mediaType)) {
            return executeRequest(body, null, null, webRequest);
        }

        throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Could not process GraphQL request");
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Object getGraphQLRequest(@RequestParam("query") String query,
                                    @RequestParam(value = "operationName", required = false) String operationName,
                                    @RequestParam(value = "variables", required = false) String variablesJson,
                                    WebRequest webRequest) {
        return executeRequest(query, operationName, convertVariablesJson(variablesJson), webRequest);
    }

    private Map<String, Object> convertVariablesJson(String jsonMap) {
        if (jsonMap == null) {
            return Collections.emptyMap();
        }
        return jsonSerializer.deserialize(jsonMap, Map.class);
    }

    private Object executeRequest(
            String query,
            String operationName,
            Map<String, Object> variables,
            WebRequest webRequest) {
        GraphQLInvocationData invocationData = new GraphQLInvocationData(query, operationName, variables);
        CompletableFuture<ExecutionResult> executionResult = graphQLInvocation.invoke(invocationData, webRequest);
        return executionResultHandler.handleExecutionResult(executionResult);

        //return getDataMapper().map(executionResultHandler.handleExecutionResult(executionResult), CustomerWsDTO.class);
    }

    @Autowired
    public void setGraphQLInvocation(GraphQLInvocation graphQLInvocation) {
        this.graphQLInvocation = graphQLInvocation;
    }

    @Autowired
    public void setExecutionResultHandler(ExecutionResultHandler executionResultHandler) {
        this.executionResultHandler = executionResultHandler;
    }
}

