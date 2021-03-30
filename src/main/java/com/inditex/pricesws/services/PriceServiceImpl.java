package com.inditex.pricesws.services;

import com.inditex.pricesws.exceptions.InternalServerErrorException;
import com.inditex.pricesws.exceptions.NoDataFoundException;
import com.inditex.pricesws.models.entities.PriceEntity;
import com.inditex.pricesws.models.repositories.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PriceServiceImpl implements PriceService {

    private static final String PRICES_NOT_FOUND = "PRICES_NOT_FOUND ";
    private static final String PRICE_NOT_FOUND_BY_PRICE_ID = "PRICES_NOT_FOUND_BY_PRICE_ID ";
    private static final String PRICE_NOT_FOUND_BY_PRICE_ID_TO_DELETE = "PRICE_NOT_FOUND_BY_PRICE_ID_TO_DELETE ";
    private static final String PRICES_NOT_FOUND_BY_PRODUCT_ID = "PRICES_NOT_FOUND_BY_PRODUCT_ID ";
    private static final String PRICES_NOT_FOUND_BY_BRAND_PRODUCT_DATE = "PRICES_NOT_FOUND_BY_BRAND_PRODUCT_DATE ";
    private static final String DELETE_PRICE_ERROR = "DELETE_PRICE_ERROR ";
    private static final String SAVE_PRICE_ERROR = "SAVE_PRICE_ERROR ";

    @Autowired
    PriceRepository priceRepository;

    @Override
    public List<PriceEntity> getPrices() {
        return Optional.of(priceRepository.findAll())
                .orElseThrow(() -> new NoDataFoundException(PRICES_NOT_FOUND));
    }

    @Override
    public PriceEntity getPriceById(Integer priceListId){
        return priceRepository.findByPriceList(priceListId)
                .orElseThrow(() -> new NoDataFoundException(PRICE_NOT_FOUND_BY_PRICE_ID + priceListId));
    }

    @Override
    public PriceEntity updateOrSavePrice(@RequestBody @Valid PriceEntity priceRequest) {
        return Optional.of(priceRepository.saveAndFlush(priceRequest)).orElseThrow(() -> new InternalServerErrorException(SAVE_PRICE_ERROR));
    }

    @Override
    public Integer deletePriceById(Integer priceListId) {
        var result = priceRepository.deleteByPriceList(priceListId)
                .orElseThrow(() -> new InternalServerErrorException(DELETE_PRICE_ERROR + priceListId ));

        if (result == 0){
            throw new NoDataFoundException(PRICE_NOT_FOUND_BY_PRICE_ID_TO_DELETE + priceListId);
        }
        return result;
    }

    @Override
    public List<PriceEntity> getPricesByProductId(Integer productId) {
        var priceList = priceRepository.findAllByProductIdOrderByPriorityDesc(productId);

        if (priceList.isEmpty()){
            throw new NoDataFoundException(PRICES_NOT_FOUND_BY_PRODUCT_ID + productId);
        }

        return priceList;
    }

    @Override
    public List<PriceEntity> findByBrandProductDate(Integer brandId, Integer productId, LocalDateTime date) {
        var priceList = priceRepository.findByBrandProductAndDatePriorityDesc(brandId, productId, date);
        if (priceList.isEmpty()){
            throw new NoDataFoundException(PRICES_NOT_FOUND_BY_BRAND_PRODUCT_DATE +brandId +" "+productId+" "+date);
        }

        return priceList;
    }

    @Override
    public PriceEntity findFirstByBrandProductDate(Integer brandId, Integer productId, LocalDateTime date) {
        var price = priceRepository.findByBrandProductAndDatePriorityDesc(brandId, productId, date);
        if (price.isEmpty()){
            throw new NoDataFoundException(PRICES_NOT_FOUND_BY_BRAND_PRODUCT_DATE +brandId +" "+productId+" "+date);
        }

        return price.get(0);
    }
}
