package com.inditex.pricesws.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class Price {

    @Getter @Setter
    private int brandId;

    @Getter @Setter
    @JsonFormat(pattern = "yyyy-MM-dd-HH.mm.ss")
    @ApiModelProperty( value = "2020-05-10-00.00.00", example = "2020-05-10-00.00.00")
    private LocalDateTime startDate;

    @Getter @Setter
    @JsonFormat(pattern = "yyyy-MM-dd-HH.mm.ss")
    @ApiModelProperty( value = "2020-06-14-00.00.00", example = "2020-06-14-00.00.00")
    private LocalDateTime endDate;

    @Getter @Setter
    private int priceList;

    @Getter @Setter
    private int productId;

    @Getter @Setter
    private int priority;

    @Getter @Setter
    private double price;

    @Getter @Setter
    @ApiModelProperty( value = "EUR", example = "EUR")
    private String currency;

}