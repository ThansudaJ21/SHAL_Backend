package com.se.shal.product.entity.enumeration;

public enum ShipmentName {
    NA("None",100),
    REGISTEREDMAILS("Thailand Post - Registered Mail",1),
    EMS("Thailand Post - EMS",2),
    KERRY("Kerry",3),
    JANDT("J&T",4),
    FLASH("Flash Express",5),
    DHL("DHL Domestic",6),
    NINJA("Ninja Van",7);

    private String shipmentName;
    private Integer order;

    ShipmentName(String shipmentName, Integer order){
        this.shipmentName = shipmentName;
        this.order = order;
    }

    public  Integer getOrder(){
        return this.order;
    }

    public String getShipmentName(){
        return this.shipmentName;
    }

    @Override
    public String toString(){
        return this.getShipmentName();
    }
}
