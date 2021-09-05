package ru.vakoom.gunmarket.admin.web.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.vakoom.gunmarket.admin.service.OfferService;
import ru.vakoom.gunmarket.admin.service.ProductService;
import ru.vakoom.gunmarket.admin.web.mapper.OfferMapper;
import ru.vakoom.gunmarket.commondatalayer.dto.OfferDto;
import ru.vakoom.gunmarket.commondatalayer.model.Offer;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class OfferApiController {

    private final OfferService offerService;
    private final OfferMapper offerMapper;
    private final ProductService productService;

    @CrossOrigin
    @PostMapping(value = "/refresh", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<OfferDto>> refresh(@RequestBody List<OfferDto> offerDtos) {
        log.info("Number of final offers incoming request :{}", offerDtos.size());
        List<Offer> offers = offerMapper.offerDtosToOffers(offerDtos);
        offerService.deleteAll();
        List<Offer> refreshedOffers = offerService.refresh(offers);
        log.info("Number of offers saved to db :{}", refreshedOffers.size());
        //ToDo не работает пересчет
        productService.recalculateMinMaxPrice();
        return ResponseEntity.ok(offerMapper.offersToOfferDtos(refreshedOffers));
    }

}
