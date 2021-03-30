package com.inditex.pricesws.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PriceDto {

    @NonNull
    private int brandId;

    @NonNull
    @JsonFormat(pattern = "yyyy-MM-dd-HH.mm.ss")
    @ApiModelProperty( value = "2020-05-10-00.00.00", example = "2020-05-10-00.00.00")
    private LocalDateTime startDate;

    @NonNull
    @JsonFormat(pattern = "yyyy-MM-dd-HH.mm.ss")
    @ApiModelProperty( value = "2020-06-14-00.00.00", example = "2020-06-14-00.00.00")
    private LocalDateTime endDate;

    @NonNull
    private Integer priceList;

    @NonNull
    private Integer productId;

    @NonNull
    private Integer priority;

    @NonNull
    private Double price;

    @NonNull
    @ApiModelProperty( value = "EUR", example = "EUR")
    private String currency;
}