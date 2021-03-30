package com.inditex.pricesws.models.repositories;

import com.inditex.pricesws.models.entities.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface PriceRepository extends JpaRepository <PriceEntity, Integer> {

    Optional<PriceEntity> findByPriceList(Integer priceListId);
    Optional<Integer> deleteByPriceList(Integer priceListId);
    List<PriceEntity> findAllByProductIdOrderByPriorityDesc(Integer productId);

    @Query("SELECT p FROM PriceEntity p " +
            "WHERE p.brandId = :brandId " +
            "and p.productId = :productId " +
            "and p.startDate <= :date " +
            "and p.endDate >= :date " +
            "order by p.priority desc")
    List<PriceEntity>findByBrandProductAndDatePriorityDesc(Integer brandId, Integer productId, LocalDateTime date);
}
