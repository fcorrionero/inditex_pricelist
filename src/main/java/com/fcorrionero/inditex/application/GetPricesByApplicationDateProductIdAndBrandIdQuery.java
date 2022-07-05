package com.fcorrionero.inditex.application;

import java.util.Date;

public class GetPricesByApplicationDateProductIdAndBrandIdQuery {
    private final Date applicationDate;
    private final int productId;
    private final int brandId;

    public GetPricesByApplicationDateProductIdAndBrandIdQuery(
            Date applicationDate,
            int productId,
            int brandId
    ) {
        this.applicationDate = applicationDate;
        this.productId = productId;
        this.brandId = brandId;
    }

    public Date getApplicationDate() {
        return applicationDate;
    }

    public int getProductId() {
        return productId;
    }

    public int getBrandId() {
        return brandId;
    }
}
