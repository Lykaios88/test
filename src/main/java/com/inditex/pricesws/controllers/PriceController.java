package com.inditex.pricesws.controllers;

import com.inditex.pricesws.dtos.Price;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.List;

@RequestMapping(path = "/inditex.pricesws" + "/v1")
public interface PriceController {

    @ApiOperation(value = "Retrieves a list of all price records")
    ResponseEntity<List<Price>> getPrices ();

    @ApiOperation(value = "Retrieves a price record by id")
    ResponseEntity<Price> getPriceById (Integer priceListId);
    
    @ApiOperation(value = "Delete a price record by id")
    ResponseEntity<Void> deletePriceById (Integer priceListId);

    @ApiOperation(value = "Save a price record")
    ResponseEntity<Price> updateOrSave(Price priceRequest);

    @ApiOperation(value = "Retrieves a list of all prices by product id")
    ResponseEntity<List<Price>> getPricesByProductId (Integer productId);

    @ApiOperation(value = "Retrieves a price by brand, product and date")
    ResponseEntity<Price> getByBrandProductAndDate (Integer brandId, Integer productId, LocalDateTime startDate);
}
