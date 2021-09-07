package ru.vakoom.gunmarket.admin.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.vakoom.gunmarket.admin.repo.OfferRepo;
import ru.vakoom.gunmarket.commondatalayer.model.Offer;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OfferService {

    private final OfferRepo offerRepo;

    public List<Offer> refresh(List<Offer> offers) {
        offerRepo.deleteAll();
        List<Offer> savedOffers = offerRepo.saveAll(offers);
        return savedOffers;
    }

    public void deleteAll() {
        offerRepo.deleteAll();
    }

    public Double calculateMinPriceByProduct(Long productId) {
        return offerRepo.calculateMinPriceByProduct(productId);
    }

    public Double calculateMaxPriceByProduct(Long productId) {
        return offerRepo.calculateMaxPriceByProduct(productId);
    }
}
