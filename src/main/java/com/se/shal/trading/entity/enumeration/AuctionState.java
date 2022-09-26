package com.se.shal.trading.entity.enumeration;

public enum AuctionState {
    NA("NA"), AUCTIONING("Auctioning"), WAITING_FOR_AUCTION("Waiting for auction");

    private String auctionState;


    AuctionState(String auctionState) {
        this.auctionState = auctionState;
    }


    public String getAuctionState() {
        return this.auctionState;
    }

    @Override
    public String toString() {
        return this.getAuctionState();
    }
}
