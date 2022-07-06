package com.fcorrionero.inditex.domain;

import java.util.Date;
import java.util.UUID;

public class ProductPrice {

    private UUID id;
    private int brandId;
    private Date startDate;
    private Date endDate;
    private int priceList;
    private int productId;
    private int priority;
    private float price;
    private String currency;

    public int getBrandId() {
        return brandId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public int getPriceList() {
        return priceList;
    }

    public int getProductId() {
        return productId;
    }

    public int getPriority() {
        return priority;
    }

    public float getPrice() {
        return price;
    }

    public String getCurrency() {
        return currency;
    }

    public ProductPrice(
            UUID id,
            int brandId,
            Date startDate,
            Date endDate,
            int priceList,
            int productId,
            int priority,
            float price,
            String currency
    ) {
        this.id = id;
        this.brandId = brandId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.priceList = priceList;
        this.productId = productId;
        this.priority = priority;
        this.price = price;
        this.currency = currency;
    }

    public UUID getId() {
        return id;
    }
}
