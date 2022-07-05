package com.fcorrionero.inditex.application;

public class PricesDataDto {
    private String currency;
    private float price;
    private int productId;
    private int brandId;

    private int priceList;
    private String startDate;
    private String endDate;

    public String getCurrency() {
        return currency;
    }

    public float getPrice() {
        return price;
    }

    public int getProductId() {
        return productId;
    }

    public int getBrandId() {
        return brandId;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public PricesDataDto(String currency, float price, int productId, int brandId, int priceList, String startDate, String endDate) {
        this.currency = currency;
        this.price = price;
        this.productId = productId;
        this.brandId = brandId;
        this.priceList = priceList;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getPriceList() {
        return priceList;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof PricesDataDto)) {
            return false;
        }

        return this.brandId == ((PricesDataDto) obj).getBrandId()
                && this.price == ((PricesDataDto) obj).getPrice()
                && this.currency.equals(((PricesDataDto) obj).getCurrency())
                && this.startDate.equals(((PricesDataDto) obj).getStartDate())
                && this.endDate.equals(((PricesDataDto) obj).getEndDate())
                && this.productId == ((PricesDataDto) obj).getProductId()
                && this.priceList == ((PricesDataDto) obj).getPriceList();
    }
}
