package com.se.shal.trading.exception;

import graphql.ErrorClassification;
import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.language.SourceLocation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductTypeException extends RuntimeException implements GraphQLError {
    String productName;

    public ProductTypeException(String productName) {
        super(String.format("No such Auction exists with Product name: %s", productName));
        this.productName = productName;
    }

    @Override
    public List<SourceLocation> getLocations() {
        return null;
    }

    @Override
    public ErrorClassification getErrorType() {
        return ErrorType.ExecutionAborted;
    }

    @Override
    public Map<String, Object> getExtensions() {
        Map<String, Object> extension = new HashMap<>();

        extension.put("error_code", 520);
        extension.put("message", String.format("No such Auction exists with Product name: %s", productName));
        extension.put("displayMessage", String.format("No such Auction exists with Product name: %s", productName));
        return extension;
    }
}
