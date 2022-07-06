package com.fcorrionero.inditex.application;

import com.fcorrionero.inditex.domain.ProductPrice;
import com.fcorrionero.inditex.domain.repository.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class GetPricesByApplicationDateProductIdAndBrandIdQueryHandler {

    @Autowired
    private final PriceRepository priceRepository;

    public GetPricesByApplicationDateProductIdAndBrandIdQueryHandler(
            PriceRepository priceRepository
    ) {
        this.priceRepository = priceRepository;
    }

    public List<PricesDataDto> dispatch(GetPricesByApplicationDateProductIdAndBrandIdQuery query) {
        List<ProductPrice> productPrices = this.priceRepository.findPricesByApplicationDateProductIdAndBrandId(
                query.applicationDate(),
                query.productId(),
                query.brandId()
        );

        Map<String, ProductPrice> pricesFilteredByPriceList = new HashMap<>();
        productPrices.forEach(it -> {
            ProductPrice p = pricesFilteredByPriceList.get(it.getId().toString());
            if (null == p) {
                pricesFilteredByPriceList.put(it.getId().toString(), it);
            } else if (it.getPriority() > p.getPriority()) {
                pricesFilteredByPriceList.replace(it.getId().toString(), it);
            }
        });

        List<PricesDataDto> pricesDataDtos = new ArrayList<>();

        pricesFilteredByPriceList.forEach((key,it) -> pricesDataDtos.add(new PricesDataDto(
                it.getCurrency(),
                it.getPrice(),
                it.getProductId(),
                it.getBrandId(),
                it.getPriceList(),
                it.getStartDate().toString(),
                it.getEndDate().toString()
        )));

        return pricesDataDtos;
    }
}
