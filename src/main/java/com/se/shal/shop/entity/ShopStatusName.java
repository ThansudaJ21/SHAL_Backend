package com.se.shal.shop.entity;

public enum ShopStatusName {
    NA("None"),
    ENABLE("Enable"),
    DISABLE("Disable");

    private String shopStatus;


    ShopStatusName(String shopStatus){
        this.shopStatus = shopStatus;
    }


    public String getShopStatus(){
        return this.shopStatus;
    }

    @Override
    public String toString(){
        return this.getShopStatus();
    }
}
