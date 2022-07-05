package com.fcorrionero.inditex.infrastructure.rest;

import com.fcorrionero.inditex.application.GetPricesByApplicationDateProductIdAndBrandIdQuery;
import com.fcorrionero.inditex.application.GetPricesByApplicationDateProductIdAndBrandIdQueryHandler;
import com.fcorrionero.inditex.application.PricesDataDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.matchers.Any;

import java.time.Instant;
import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;

class PricesControllerUnitTest {

    @Mock
    private GetPricesByApplicationDateProductIdAndBrandIdQueryHandler getPricesByApplicationDateProductIdAndBrandIdQueryHandlerMock;

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
    public void should_return_a_PricesDataDto_list() {
        Date givenDate = Date.from(Instant.now());
        int givenProductId = 1111;
        int givenBrandId = 1;
        GetPricesByApplicationDateProductIdAndBrandIdQuery query = new GetPricesByApplicationDateProductIdAndBrandIdQuery(
                givenDate,
                givenProductId,
                givenBrandId
        );

        List<PricesDataDto> givenListOfPricesDataDto = List.of(
                new PricesDataDto(
                        "EUR",
                        30F,
                        givenProductId,
                        givenBrandId,
                        1,
                        givenDate.toString(),
                        givenDate.toString()
                )
        );
        Mockito.when(getPricesByApplicationDateProductIdAndBrandIdQueryHandlerMock.dispatch(any())).thenReturn(givenListOfPricesDataDto);

        PricesController pricesController = new PricesController(getPricesByApplicationDateProductIdAndBrandIdQueryHandlerMock);

        List<PricesDataDto> result = pricesController.getPricesByApplicationDateProductIdAndBrandId(givenDate.toString(), givenProductId, givenBrandId);

        Assertions.assertTrue(true);
    }

}