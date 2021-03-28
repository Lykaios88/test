package com.inditex.pricesws.services;

import com.inditex.pricesws.dtos.Price;
import com.inditex.pricesws.dtos.Price;
import com.inditex.pricesws.exceptions.NotFoundException;
import com.inditex.pricesws.models.entities.PriceEntity;
import com.inditex.pricesws.models.repositories.PriceRepository;
import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class PriceServiceImpl implements PriceService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PriceServiceImpl.class);

    @Autowired
    PriceRepository priceRepository;

    @Autowired
    Mapper mapper;

    @Override
    public List<Price> getPrices() {
        return mapEntityToDTO(priceRepository.findAll());
    }

    @Override
    public Price getPriceById(Integer priceListId){
        return mapEntityToDTO(priceRepository.findByPriceList(priceListId));
    }

    @Override
    public Price updateOrSavePrice(Price priceRequest) {
        return mapEntityToDTO(priceRepository.saveAndFlush(mapDTOToEntity(priceRequest)));
    }

    @Override
    public void deletePricebyId(Integer priceListId) {
        priceRepository.deleteByPriceList(priceListId);
    }

    @Override
    public List<Price> getPricesByProductId(Integer productId) {
        return mapEntityToDTO(priceRepository.findAllByProductIdOrderByPriorityDesc(productId));
    }

    @Override
    public List<Price> findByBrandProductDate(Integer brandId, Integer productId, LocalDateTime date) {
        return mapEntityToDTO(priceRepository.findByBrandIdAndProductIdAndStartDateBeforeAndEndDateAfterOrderByPriorityDesc(brandId, productId, date, date));
    }

    @Override
    public Price findFirstByBrandProductDate(Integer brandId, Integer productId, LocalDateTime date) {
        List<Price> Price = mapEntityToDTO(priceRepository.findByBrandIdAndProductIdAndStartDateBeforeAndEndDateAfterOrderByPriorityDesc(brandId, productId, date, date));
        return Price != null ? Price.get(0) : null;
    }

    private Price mapEntityToDTO(PriceEntity priceEntity) {
        // Map entity -> responseDTO
        Price Price = null;
        if (priceEntity != null) {
            try {
                Price = mapper.map(priceEntity, Price.class);
            } catch (Exception e) {
                LOGGER.error("MapperError", e);
            }
        }
        return Price;
    }

    private List<Price> mapEntityToDTO(List<PriceEntity> priceEntity) {
        List<Price> response = new ArrayList<>();
        try {
            // Map entity -> responseDTO
            priceEntity.forEach(p -> response.add(mapper.map(p, Price.class)));
        } catch (Exception e) {
            LOGGER.error("MapperError", e);
        }
        return response;
    }

    private PriceEntity mapDTOToEntity(Price priceRequest) {
        // Map requestDTO -> entity
        PriceEntity priceEntity = null;
        if (priceRequest != null) {
            try {
                priceEntity = mapper.map(priceRequest, PriceEntity.class);
            } catch (Exception e) {
                LOGGER.error("MapperError", e);
            }
        }
        return priceEntity;
    }
}
