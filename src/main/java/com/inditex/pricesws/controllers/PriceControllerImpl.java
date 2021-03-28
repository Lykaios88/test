package com.inditex.pricesws.controllers;

import com.inditex.pricesws.dtos.Price;
import com.inditex.pricesws.services.PriceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RestController
public class PriceControllerImpl implements PriceController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PriceControllerImpl.class);
    private final HttpServletRequest request;

    @Autowired
    PriceService priceService;

    public PriceControllerImpl(HttpServletRequest request) {
        this.request = request;
    }

    @GetMapping("/prices")
    public ResponseEntity<List<Price>> getPrices (){
        try {
            return new ResponseEntity<>(priceService.getPrices(), HttpStatus.OK);
        }catch (Exception e){
            LOGGER.error("Couldn't find prices ",e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/prices/{priceListId}")
    public ResponseEntity<Price> getPriceById (@PathVariable Integer priceListId){
        try {
            return new ResponseEntity<>(priceService.getPriceById(priceListId), HttpStatus.OK);
        }catch (Exception e){
            LOGGER.error("Couldn't find prices ",e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/prices/{priceListId}")
    public ResponseEntity<Void> deletePriceById (@PathVariable Integer priceListId){
        try {
            priceService.deletePricebyId(priceListId);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            LOGGER.error("Couldn't find prices ",e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/price")
    public ResponseEntity<Price> updateOrSave(@RequestBody @Valid Price priceRequest){
        try {
            return new ResponseEntity<>(priceService.updateOrSavePrice(priceRequest), HttpStatus.OK);
        }catch (Exception e){
            LOGGER.error("Couldn't find prices ",e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/prices/products/{productId}")
    public ResponseEntity<List<Price>> getPricesByProductId (@PathVariable Integer productId){
        try {
            return new ResponseEntity<>(priceService.getPricesByProductId(productId), HttpStatus.OK);
        }catch (Exception e){
            LOGGER.error("Couldn't find prices ",e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/prices/brands/{brandId}/products/{productId}")
    public ResponseEntity<Price> getByBrandProductAndDate (@PathVariable Integer brandId, @PathVariable Integer productId,
                                 @RequestParam("date") @DateTimeFormat(pattern="yyyy-MM-dd-HH.mm.ss") LocalDateTime startDate){
        try {
            return new ResponseEntity<>(priceService.findFirstByBrandProductDate(brandId, productId, startDate), HttpStatus.OK);
        }catch (Exception e){
            LOGGER.error("Couldn't find prices ",e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}