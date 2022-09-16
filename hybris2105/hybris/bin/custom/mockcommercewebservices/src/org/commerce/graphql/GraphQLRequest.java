package org.commerce.graphql;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Map;

@JsonAutoDetect
public class GraphQLRequest {

    @JsonIgnore
    private String query;
    @JsonIgnore
    private String operationName;
    @JsonIgnore
    private Map<String, Object> variables;

    public String getQuery() {
        return query;
    }

    public String getOperationName() {
        return operationName;
    }

    public Map<String, Object> getVariables() {
        return variables;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public void setOperationName(String operationName) {
        this.operationName = operationName;
    }

    public void setVariables(Map<String, Object> variables) {
        this.variables = variables;
    }
}
