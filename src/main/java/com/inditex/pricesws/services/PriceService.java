package com.inditex.pricesws.services;

import com.inditex.pricesws.models.entities.PriceEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface PriceService {

    List<PriceEntity> getPrices();

    PriceEntity getPriceById(Integer priceListId);

    PriceEntity updateOrSavePrice(PriceEntity priceRequest);

    Integer deletePriceById(Integer priceListId);

    List<PriceEntity> getPricesByProductId (Integer productId);

    List<PriceEntity> findByBrandProductDate (Integer brandId, Integer productId, LocalDateTime date);

    PriceEntity findFirstByBrandProductDate (Integer brandId, Integer productId, LocalDateTime date);
}
