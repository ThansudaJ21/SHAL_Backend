package com.se.shal.trading.exception;

import graphql.ErrorClassification;
import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.language.SourceLocation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StorageException extends RuntimeException implements GraphQLError {


    public StorageException() {
        super(String.format("The product is sold out"));
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
        extension.put("message", String.format("The product is sold out"));
        extension.put("displayMessage", String.format("The product is sold out"));
        return extension;
    }
}
