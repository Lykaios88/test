package com.inditex.pricesws.controllers;

import com.inditex.pricesws.dtos.PriceDto;
import com.inditex.pricesws.models.entities.PriceEntity;
import com.inditex.pricesws.services.PriceService;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
public class PriceControllerImpl implements PriceController {

    @Autowired
    PriceService priceService;

    @Autowired
    Mapper mapper;

    @GetMapping("/prices")
    public ResponseEntity<List<PriceDto>> getPrices (){
        List<PriceDto> response = new ArrayList<>();
        priceService.getPrices().forEach(p -> response.add(mapper.map(p, PriceDto.class)));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/prices/{priceListId}")
    public ResponseEntity<PriceDto> getPriceById (@PathVariable Integer priceListId){
        var responsePrice = mapper.map(priceService.getPriceById(priceListId), PriceDto.class);
        return new ResponseEntity<>(responsePrice, HttpStatus.OK);
    }

    @DeleteMapping("/prices/{priceListId}")
    public ResponseEntity<Void> deletePriceById (@PathVariable Integer priceListId){
        priceService.deletePriceById(priceListId);
            return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/price")
    public ResponseEntity<PriceDto> updateOrSave(@RequestBody @Valid PriceDto priceDtoRequest){
        var requestprice = mapper.map(priceDtoRequest, PriceEntity.class);
        var responsePrice = mapper.map(priceService.updateOrSavePrice(requestprice), PriceDto.class);
        return new ResponseEntity<>(responsePrice, HttpStatus.OK);
    }

    @GetMapping("/prices/products/{productId}")
    public ResponseEntity<List<PriceDto>> getPricesByProductId (@PathVariable Integer productId){
        List<PriceDto> response = new ArrayList<>();
        priceService.getPricesByProductId(productId).forEach(p -> response.add(mapper.map(p, PriceDto.class)));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/prices/brands/{brandId}/products/{productId}")
    public ResponseEntity<PriceDto> getByBrandProductAndDate (@PathVariable Integer brandId, @PathVariable Integer productId,
                                 @RequestParam("date") @DateTimeFormat(pattern="yyyy-MM-dd-HH.mm.ss") LocalDateTime startDate){
        var responsePrice= mapper.map(priceService.findFirstByBrandProductDate(brandId, productId, startDate), PriceDto.class);
        return new ResponseEntity<>(responsePrice, HttpStatus.OK);

    }
}