package com.se.shal.trading.exception;

import graphql.ErrorClassification;
import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.language.SourceLocation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LessThanStartingBidException extends RuntimeException implements GraphQLError {
    Double startingBid;

    public LessThanStartingBidException(Double startingBid) {
        super(String.format("Bid has to be greater than starting bid %s THB.", startingBid));
        this.startingBid = startingBid;
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
        extension.put("message", String.format("Bid has to be greater than starting bid %s THB.", startingBid));
        extension.put("displayMessage", String.format("Bid has to be greater than starting bid %s THB.", startingBid));
        return extension;
    }
}
