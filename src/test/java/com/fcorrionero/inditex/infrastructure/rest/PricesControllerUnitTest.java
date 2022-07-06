package com.fcorrionero.inditex.infrastructure.rest;

import com.fcorrionero.inditex.application.GetPricesByApplicationDateProductIdAndBrandIdQuery;
import com.fcorrionero.inditex.application.GetPricesByApplicationDateProductIdAndBrandIdQueryHandler;
import com.fcorrionero.inditex.application.PricesDataDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;

class PricesControllerUnitTest {

    @Mock
    private GetPricesByApplicationDateProductIdAndBrandIdQueryHandler getPricesByApplicationDateProductIdAndBrandIdQueryHandlerMock;

    @Captor
    private ArgumentCaptor<GetPricesByApplicationDateProductIdAndBrandIdQuery> queryCaptor;

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
        DateFormat formatter = new SimpleDateFormat(GetPricesByApplicationDateProductIdAndBrandIdQuery.DATE_FORMAT);
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
                        formatter.format(givenDate),
                        formatter.format(givenDate)
                )
        );

        Mockito.when(getPricesByApplicationDateProductIdAndBrandIdQueryHandlerMock.dispatch(any())).thenReturn(givenListOfPricesDataDto);

        PricesController pricesController = new PricesController(getPricesByApplicationDateProductIdAndBrandIdQueryHandlerMock);
        List<PricesDataDto> result = pricesController.getPricesByApplicationDateProductIdAndBrandId(formatter.format(givenDate), givenProductId, givenBrandId);

        Mockito.verify(getPricesByApplicationDateProductIdAndBrandIdQueryHandlerMock).dispatch(queryCaptor.capture());
        GetPricesByApplicationDateProductIdAndBrandIdQuery queryValue = queryCaptor.getValue();

//        Assertions.assertEquals(query.applicationDate().toString(), queryValue.applicationDate().toString());
        Assertions.assertEquals(query.brandId(), queryValue.brandId());
        Assertions.assertEquals(query.productId(), queryValue.productId());
        Assertions.assertEquals(givenListOfPricesDataDto, result);
    }

}