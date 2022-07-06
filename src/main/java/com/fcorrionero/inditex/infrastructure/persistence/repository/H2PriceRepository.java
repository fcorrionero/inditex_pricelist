package com.fcorrionero.inditex.infrastructure.persistence.repository;

import com.fcorrionero.inditex.domain.ProductPrice;
import com.fcorrionero.inditex.domain.repository.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Repository
public class H2PriceRepository implements PriceRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<ProductPrice> findPricesByApplicationDateProductIdAndBrandId(Date appDate, int productId, int brandId)
    {
        String query = "SELECT * FROM Product_Price WHERE PRODUCT_ID = ? AND BRAND_ID = ? AND ? BETWEEN START_DATE AND END_DATE  ";
        return jdbcTemplate.query(query, new ProductPriceMapper(), productId, brandId, appDate);

    }
}
