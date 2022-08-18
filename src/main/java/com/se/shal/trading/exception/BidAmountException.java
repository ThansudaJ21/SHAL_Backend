package com.se.shal.trading.exception;

import graphql.ErrorClassification;
import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.language.SourceLocation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BidAmountException extends RuntimeException implements GraphQLError {
    Double bidAmount;

    public BidAmountException(Double bidAmount) {
        super(String.format("Bid has to be greater than max bid %s THB.", bidAmount));
        this.bidAmount = bidAmount;
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
        extension.put("message", String.format("Bid has to be greater than max bid %s THB.", bidAmount));
        extension.put("displayMessage", String.format("Bid has to be greater than max bid %s THB.", bidAmount));
        return extension;
    }
}
