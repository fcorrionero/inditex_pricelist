package com.fcorrionero.inditex.entrypoint.api;

import com.fcorrionero.inditex.application.GetPricesByApplicationDateProductIdAndBrandIdQuery;
import com.fcorrionero.inditex.application.GetPricesByApplicationDateProductIdAndBrandIdQueryHandler;
import com.fcorrionero.inditex.application.PricesDataDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/prices")
public class PricesController {

    @Autowired
    private GetPricesByApplicationDateProductIdAndBrandIdQueryHandler getPricesByApplicationDateProductIdAndBrandIdQueryHandler;

    public PricesController(
            GetPricesByApplicationDateProductIdAndBrandIdQueryHandler getPricesByApplicationDateProductIdAndBrandIdQueryHandler
    ) {
        this.getPricesByApplicationDateProductIdAndBrandIdQueryHandler = getPricesByApplicationDateProductIdAndBrandIdQueryHandler;
    }

    @RequestMapping(
            value = "/all/{date}/{productId}/{brandId}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public List<PricesDataDto> getPricesByApplicationDateProductIdAndBrandId(
            @PathVariable("date") String date,
            @PathVariable("productId") int productId,
            @PathVariable("brandId") int brandId
    ) {
        DateFormat formatter = new SimpleDateFormat(GetPricesByApplicationDateProductIdAndBrandIdQuery.DATE_FORMAT);
        try {
            Date appDate = formatter.parse(date);
            return this.getPricesByApplicationDateProductIdAndBrandIdQueryHandler
                    .dispatch(new GetPricesByApplicationDateProductIdAndBrandIdQuery(
                            appDate,
                            productId,
                            brandId
                    ));
        } catch (ParseException e) {
            return List.of(); // TODO create proper errors
        }
    }
}
