package com.fcorrionero.inditex.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.UUID;

@Entity
@Table
public class ProductPrice {

    @Id
    @Column
    private UUID id;
    @Column
    private int brandId;
    @Column
    private Date startDate;
    @Column
    private Date endDate;
    @Column
    private int priceList;
    @Column
    private int productId;
    @Column
    private int priority;
    @Column
    private float price;
    @Column
    private String currency;

    public ProductPrice() {

    }

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
}
