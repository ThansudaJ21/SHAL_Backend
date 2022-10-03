package com.se.shal.shop.entity;

public enum PromptPayType {
    NA("NA", 100),
    NATIONAL_ID("National ID", 1),
    PHONE("Mobile number", 2),
    E_WALLET("e-Wallet", 3);

    private String promptPayType;
    private final Integer order;

    PromptPayType(String promptPayType, Integer order) {
        this.promptPayType = promptPayType;
        this.order = order;
    }

    public Integer getOrder() {
        return this.order;
    }

    public String getPromptPayType() {
        return this.promptPayType;
    }

    @Override
    public String toString() {
        return this.getPromptPayType();
    }
}
