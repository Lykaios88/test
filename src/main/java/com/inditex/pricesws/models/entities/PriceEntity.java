package com.inditex.pricesws.models.entities;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
@Entity
@Table(name="PRICES")
public class PriceEntity {

    @Getter @Setter
    @Column(name="BRAND_ID")
    private int brandId;

    @Getter @Setter
    @Column(name = "START_DATE")
    private LocalDateTime startDate;

    @Getter @Setter
    @Column(name = "END_DATE")
    private LocalDateTime endDate;

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Getter @Setter
    @Column(name="PRICE_LIST")
    private int priceList;

    @Getter @Setter
    @Column(name="PRODUCT_ID")
    private int productId;

    @Getter @Setter
    @Column(name="PRIORITY")
    private int priority;

    @Getter @Setter
    @Column(name="PRICE")
    private double price;

    @Getter @Setter
    @Column(name="CURR")
    private String currency;
}