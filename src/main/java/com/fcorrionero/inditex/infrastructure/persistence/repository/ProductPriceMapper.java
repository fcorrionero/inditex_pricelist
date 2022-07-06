package com.fcorrionero.inditex.infrastructure.persistence.repository;

import com.fcorrionero.inditex.domain.ProductPrice;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.UUID;


public class ProductPriceMapper implements RowMapper<ProductPrice> {

    @Override
    public ProductPrice mapRow(ResultSet rs, int rowNum) throws SQLException {
        try{
//            String fieldID = rs.getString("ID");
//            UUID id = UUID.fromString(fieldID);
            UUID id = UUID.randomUUID();
            int brandId = rs.getInt("BRAND_ID");
            int priceList = rs.getInt("PRICE_LIST");
            int productId = rs.getInt("PRODUCT_ID");
            int priority = rs.getInt("PRIORITY");
            float price = rs.getFloat("PRICE");
            String currency = rs.getString("CURRENCY");
            Date startDate = rs.getDate("START_DATE");
            Date endDate = rs.getDate("END_DATE");
            return new ProductPrice(
                    id,
                    brandId,
                    startDate,
                    endDate,
                    priceList,
                    productId,
                    priority,
                    price,
                    currency
            );
        }catch (RuntimeException e){
            return null; //TODO Add proper error handling
        }

    }
}
