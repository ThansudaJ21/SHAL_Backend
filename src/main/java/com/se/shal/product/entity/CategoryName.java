package com.se.shal.product.entity;

public enum CategoryName {
    NA("None",100),
    BEAUTY("Beauty & Personal Care",1),
    ELECTRONIC("Electronic",2),
    TOY("Toy, DIY, and Hobbies",3),
    HEALTH("Health & Wellness",4),
    SPORTS("Sports & Travel",5),
    HOME("Home Appliances",6),
    PETS("Pets",7);

    private String category;
    private Integer order;

    CategoryName(String saleType, Integer order){
        this.category = saleType;
        this.order = order;
    }

    public  Integer getOrder(){
        return this.order;
    }

    public String getCategoryName(){
        return this.category;
    }

    @Override
    public String toString(){
        return this.getCategoryName();
    }
}
