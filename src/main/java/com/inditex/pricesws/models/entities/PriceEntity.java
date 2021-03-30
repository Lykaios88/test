package com.inditex.pricesws.models.entities;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="PRICES")
@DynamicInsert
@DynamicUpdate
public class PriceEntity {

    @NonNull
    @Column(name="BRAND_ID")
    private Integer brandId;

    @NonNull
    @Column(name = "START_DATE")
    private LocalDateTime startDate;

    @NonNull
    @Column(name = "END_DATE")
    private LocalDateTime endDate;

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name="PRICE_LIST")
    private Integer priceList;

    @NonNull
    @Column(name="PRODUCT_ID")
    private Integer productId;

    @NonNull
    @Column(name="PRIORITY")
    private Integer priority;

    @NonNull
    @Column(name="PRICE")
    private Double price;

    @NonNull
    @Column(name="CURR")
    private String currency;
}