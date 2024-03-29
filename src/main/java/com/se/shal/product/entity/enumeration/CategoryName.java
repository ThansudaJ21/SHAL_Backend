package com.se.shal.product.entity.enumeration;

public enum CategoryName {
    NA("None",100),
    BEAUTY("Beauty & Personal Care",1),
    FASHION("Fashion",2),
    ELECTRONIC("Electronic",3),
    TOY("Toy, DIY, and Hobbies",4),
    HEALTH("Health & Wellness",5),
    SPORTS("Sports & Travel",6),
    HOME("Home Appliances",7),
    PETS("Pets",8);

    private final String category;
    private final Integer order;

    CategoryName(String category, Integer order){
        this.category = category;
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
