package com.fcorrionero.inditex.entrypoint.api;

import com.fcorrionero.inditex.application.GetPricesByApplicationDateProductIdAndBrandIdQueryHandler;
import com.fcorrionero.inditex.infrastructure.persistence.repository.H2PriceRepository;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpUriRequest;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;
import java.io.IOException;

@RunWith(SpringRunner.class)
@EnableAutoConfiguration
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,
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
            "'14-06-2020,10:00',35455,1,'[{\"currency\":\"EUR\",\"price\":35.5,\"productId\":35455,\"brandId\":1,\"priceList\":1,\"startDate\":\"2020-06-14\",\"endDate\":\"2020-12-31\"}]'",
            "'14-06-2020,16:00',35455,1,'[{\"currency\":\"EUR\",\"price\":35.5,\"productId\":35455,\"brandId\":1,\"priceList\":1,\"startDate\":\"2020-06-14\",\"endDate\":\"2020-12-31\"},{\"currency\":\"EUR\",\"price\":25.45,\"productId\":35455,\"brandId\":1,\"priceList\":2,\"startDate\":\"2020-06-14\",\"endDate\":\"2020-06-14\"}]'",
            "'14-06-2020,21:00',35455,1,'[{\"currency\":\"EUR\",\"price\":35.5,\"productId\":35455,\"brandId\":1,\"priceList\":1,\"startDate\":\"2020-06-14\",\"endDate\":\"2020-12-31\"}]'",
            "'15-06-2020,10:00',35455,1,'[{\"currency\":\"EUR\",\"price\":35.5,\"productId\":35455,\"brandId\":1,\"priceList\":1,\"startDate\":\"2020-06-14\",\"endDate\":\"2020-12-31\"},{\"currency\":\"EUR\",\"price\":30.5,\"productId\":35455,\"brandId\":1,\"priceList\":3,\"startDate\":\"2020-06-15\",\"endDate\":\"2020-06-15\"}]'",
            "'15-06-2020,21:00',35455,1,'[{\"currency\":\"EUR\",\"price\":35.5,\"productId\":35455,\"brandId\":1,\"priceList\":1,\"startDate\":\"2020-06-14\",\"endDate\":\"2020-12-31\"},{\"currency\":\"EUR\",\"price\":38.95,\"productId\":35455,\"brandId\":1,\"priceList\":4,\"startDate\":\"2020-06-15\",\"endDate\":\"2020-12-31\"}]'"
    })
    public void should_return_products(String date, int productId, int brandId, String expected) throws IOException {
        String ENDPOINT = "http://localhost:8080/prices/all/%s/%d/%d";
        String route = String.format(ENDPOINT, date, productId, brandId);
        HttpUriRequest request = new HttpGet(route);
        CloseableHttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);

        HttpEntity entity = httpResponse.getEntity();
        try {
            String responseString = EntityUtils.toString(entity, "UTF-8");
            Assertions.assertEquals(expected, responseString);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

    }

}
