package com.fcorrionero.inditex.infrastructure.persistence.repository;

import com.fcorrionero.inditex.domain.ProductPrice;
import com.fcorrionero.inditex.domain.repository.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.Date;
import java.util.List;

@Repository
public class H2PriceRepository implements PriceRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<ProductPrice> findPricesByApplicationDateProductIdAndBrandId(Date appDate, int productId, int brandId) {
        jdbcTemplate.query("Select * From Product_Price", new ProductPriceMapper());

        return null;
    }
}
