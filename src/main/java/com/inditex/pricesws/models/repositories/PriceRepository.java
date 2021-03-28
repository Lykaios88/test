package com.inditex.pricesws.models.repositories;

import com.inditex.pricesws.models.entities.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface PriceRepository extends JpaRepository <PriceEntity, Integer> {

    PriceEntity findByPriceList(Integer priceListId);
    void deleteByPriceList(Integer priceListId);
    List<PriceEntity> findAllByProductIdOrderByPriorityDesc(Integer productId);
    List<PriceEntity>findByBrandIdAndProductIdAndStartDateBeforeAndEndDateAfterOrderByPriorityDesc (Integer brandId, Integer productId, LocalDateTime start, LocalDateTime end);
}
