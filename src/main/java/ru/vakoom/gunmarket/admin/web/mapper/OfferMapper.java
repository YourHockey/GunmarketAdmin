package ru.vakoom.gunmarket.admin.web.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.vakoom.gunmarket.admin.service.ProductService;
import ru.vakoom.gunmarket.admin.service.ShopService;
import ru.vakoom.gunmarket.commondatalayer.dto.OfferDto;
import ru.vakoom.gunmarket.commondatalayer.model.Offer;
import ru.vakoom.gunmarket.commondatalayer.model.Product;
import ru.vakoom.gunmarket.commondatalayer.model.Shop;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OfferMapper {

    private final ShopService shopService;
    private final ProductService productService;

    public List<OfferDto> offersToOfferDtos(List<Offer> offers) {
        return offers.stream().map(this::offerToOfferDto).collect(Collectors.toList());
    }

    public List<Offer> offerDtosToOffers(List<OfferDto> offerDtos) {
        return offerDtos.stream().map(this::offerDtoToOffer).collect(Collectors.toList());
    }

    public OfferDto offerToOfferDto(Offer offer) {
        return new OfferDto()
                .setProductId(offer.getProduct().getProductId().toString())
                .setShopName(offer.getShop().getName())
                .setLink(offer.getLink())
                .setPrice(offer.getPrice());
    }

    public Offer offerDtoToOffer(OfferDto offerDto) {
        Shop shop = shopService.getByName(offerDto.getShopName());
        Product product = productService.getById(Long.parseLong(offerDto.getProductId()));
        return new Offer()
                .setProductInShopId(product.getProductId() + "&" + shop.getShopId())
                .setLink(offerDto.getLink())
                .setPrice(offerDto.getPrice())
                .setProduct(product)
                .setShop(shop);
    }

}
