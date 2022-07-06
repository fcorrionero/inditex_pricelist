package com.fcorrionero.inditex.infrastructure.persistence.repository;

import com.fcorrionero.inditex.domain.ProductPrice;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;


public class ProductPriceMapper implements RowMapper<ProductPrice> {

    @Override
    public ProductPrice mapRow(ResultSet rs, int rowNum) throws SQLException {
        return null;
    }
}
