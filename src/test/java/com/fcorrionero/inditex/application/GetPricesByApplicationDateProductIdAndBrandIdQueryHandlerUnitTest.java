package com.fcorrionero.inditex.application;

import com.fcorrionero.inditex.domain.ProductPrice;
import com.fcorrionero.inditex.domain.repository.PriceRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.UUID;

final class GetPricesByApplicationDateProductIdAndBrandIdQueryHandlerUnitTest {

    @Mock
    private PriceRepository priceRepositoryMock;

    private AutoCloseable autoCloseable;


    @BeforeEach
    private void beforeEach() {
        autoCloseable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    private void afterEach() throws Exception {
        autoCloseable.close();
    }

    @Test
    void should_return_a_price_list() {
        Date givenDate = Date.from(Instant.now());
        int givenProductId = 1;
        int givenBrandId = 1;

        ProductPrice productPrice = new ProductPrice(
                UUID.randomUUID(),
                givenBrandId,
                givenDate,
                givenDate,
                1,
                givenProductId,
                1,
                35.35F,
                "EUR"
        );

        List<PricesDataDto> givenListOfPricesDataDto = List.of(
                new PricesDataDto(
                        productPrice.getCurrency(),
                        productPrice.getPrice(),
                        productPrice.getProductId(),
                        productPrice.getBrandId(),
                        productPrice.getPriceList(),
                        productPrice.getStartDate().toString(),
                        productPrice.getEndDate().toString()
                )
        );

        List<ProductPrice> pricesList = List.of(productPrice);
        Mockito.when(
                priceRepositoryMock.findPricesByApplicationDateProductIdAndBrandId(givenDate, givenProductId, givenProductId)
        ).thenReturn(pricesList);

        GetPricesByApplicationDateProductIdAndBrandIdQuery query = new GetPricesByApplicationDateProductIdAndBrandIdQuery(givenDate, givenProductId, givenBrandId);

        GetPricesByApplicationDateProductIdAndBrandIdQueryHandler queryHandler = new GetPricesByApplicationDateProductIdAndBrandIdQueryHandler(priceRepositoryMock);

        List<PricesDataDto> result = queryHandler.dispatch(query);

        Assertions.assertEquals(givenListOfPricesDataDto.get(0), result.get(0));
        Assertions.assertEquals(givenListOfPricesDataDto.size(), result.size());
    }

    @Test
    void should_return_a_price_list_ordered() {
        Date givenDate = Date.from(Instant.now());
        int givenProductId = 1;
        int givenBrandId = 1;
        UUID givenUuid = UUID.randomUUID();
        float expectedPrice = 40.00F;

        ProductPrice productPrice = new ProductPrice(
                givenUuid,
                givenBrandId,
                givenDate,
                givenDate,
                1,
                givenProductId,
                1,
                35.35F,
                "EUR"
        );

        ProductPrice productPrice2 = new ProductPrice(
                givenUuid,
                givenBrandId,
                givenDate,
                givenDate,
                1,
                givenProductId,
                2,
                expectedPrice,
                "EUR"
        );


        List<ProductPrice> pricesList = List.of(productPrice, productPrice2);
        Mockito.when(
                priceRepositoryMock.findPricesByApplicationDateProductIdAndBrandId(givenDate, givenProductId, givenProductId)
        ).thenReturn(pricesList);

        GetPricesByApplicationDateProductIdAndBrandIdQuery query = new GetPricesByApplicationDateProductIdAndBrandIdQuery(givenDate, givenProductId, givenBrandId);

        GetPricesByApplicationDateProductIdAndBrandIdQueryHandler queryHandler = new GetPricesByApplicationDateProductIdAndBrandIdQueryHandler(priceRepositoryMock);

        List<PricesDataDto> result = queryHandler.dispatch(query);

        Assertions.assertEquals(expectedPrice, result.get(0).price());
        Assertions.assertEquals(1, result.size());
    }

}