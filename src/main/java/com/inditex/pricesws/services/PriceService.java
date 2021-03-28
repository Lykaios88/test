package com.inditex.pricesws.services;

import com.inditex.pricesws.dtos.Price;

import java.time.LocalDateTime;
import java.util.List;

public interface PriceService {

    List<Price> getPrices();

    Price getPriceById(Integer priceListId);

    Price updateOrSavePrice(Price priceRequest);

    void deletePricebyId(Integer priceListId);

    List<Price> getPricesByProductId (Integer productId);

    List<Price> findByBrandProductDate (Integer brandId, Integer productId, LocalDateTime date);

    Price findFirstByBrandProductDate (Integer brandId, Integer productId, LocalDateTime date);
}
