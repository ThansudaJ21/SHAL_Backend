package com.se.shal.trading.exception;

import graphql.ErrorClassification;
import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.language.SourceLocation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MaximumQuantityException extends RuntimeException implements GraphQLError {


    public MaximumQuantityException() {
        super("You have reached the maximum quantity in the cart available for this item.");
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
        extension.put("message", "You have reached the maximum quantity in the cart available for this item.");
        extension.put("displayMessage", "You have reached the maximum quantity in the cart available for this item.");
        return extension;
    }
}
