package com.fcorrionero.inditex.application;

import java.util.Date;

public record GetPricesByApplicationDateProductIdAndBrandIdQuery(Date applicationDate, int productId, int brandId) {

    public static final String DATE_FORMAT = "d-MM-yyyy,HH:mm";
}
