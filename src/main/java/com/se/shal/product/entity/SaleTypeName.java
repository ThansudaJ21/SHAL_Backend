package com.se.shal.product.entity;

public enum SaleTypeName {
    NA("None",100),
    AUCTION("Auction only",1),
    SALE("Sale only",2),
    AUCTIONANDSALE("Auction and Sale",3);

    private String saleType;
    private Integer order;

    SaleTypeName(String saleType, Integer order){
        this.saleType = saleType;
        this.order = order;
    }

    public  Integer getOrder(){
        return this.order;
    }

    public String getSaleTypeName(){
        return this.saleType;
    }

    @Override
    public String toString(){
        return this.getSaleTypeName();
    }
}
