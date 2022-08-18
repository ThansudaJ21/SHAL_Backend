package com.se.shal.trading.exception;

import graphql.ErrorClassification;
import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.language.SourceLocation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserExistException extends RuntimeException implements GraphQLError {


    public UserExistException() {
        super(String.format("No such user exists"));

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
        extension.put("message", String.format("No such user exists"));
        extension.put("displayMessage", String.format("No such user exists"));
        return extension;
    }
}
