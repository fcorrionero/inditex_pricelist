package com.fcorrionero.inditex.infrastructure.rest;

import com.fcorrionero.inditex.application.GetPricesByApplicationDateProductIdAndBrandIdQueryHandler;
import com.fcorrionero.inditex.infrastructure.persistence.repository.H2PriceRepository;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration
@SpringBootTest(
        classes = {
                PricesController.class,
                GetPricesByApplicationDateProductIdAndBrandIdQueryHandler.class,
                H2PriceRepository.class,
        }
)
@ComponentScan(basePackages = {"com.fcorrionero.inditex"})
public class PricesControllerComponentTest {

    @ParameterizedTest
    @CsvSource({
            "'14-06-2020,10:00',35455,1",
            "'14-06-2020,16:00',35455,1",
            "'14-06-2020,21:00',35455,1",
            "'15-06-2020,10:00',35455,1",
            "'15-06-2020,21:00',35455,1"
    })
    public void should_return_products(String date, int productId, int brandId) {

    }

}
