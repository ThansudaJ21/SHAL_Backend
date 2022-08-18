package com.se.shal.product.entity.enumeration;

public enum TimeUnit {
    NA("None",100),
    MINUTE("minute",1),
    HOUR("hour",2),
    DAY("Day",3),
    WEEK("Week",4);

    private String timeUnit;
    private Integer order;

    TimeUnit(String timeUnit, Integer order){
        this.timeUnit = timeUnit;
        this.order = order;
    }

    public  Integer getOrder(){
        return this.order;
    }

    public String getTimeUnit(){
        return this.timeUnit;
    }

    @Override
    public String toString(){
        return this.getTimeUnit();
    }
}
