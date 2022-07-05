package com.fcorrionero.inditex.domain.repository;

import com.fcorrionero.inditex.domain.ProductPrice;

import java.util.Date;
import java.util.List;

public interface PriceRepository {
    List<ProductPrice> findPricesByApplicationDateProductIdAndBrandId(Date appDate, int productId, int brandId);
}
