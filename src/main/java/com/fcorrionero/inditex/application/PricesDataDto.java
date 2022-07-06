package com.fcorrionero.inditex.application;

public record PricesDataDto(String currency, float price, int productId, int brandId, int priceList, String startDate, String endDate) {
}
