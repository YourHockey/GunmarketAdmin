package ru.vakoom.gunmarket.admin.web.api.offerextractingintegration;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.vakoom.gunmarket.admin.repo.ShopRepo;
import ru.vakoom.gunmarket.admin.service.ShopService;
import ru.vakoom.gunmarket.admin.web.dto.ProductDto;
import ru.vakoom.gunmarket.admin.web.dto.ShopDto;
import ru.vakoom.gunmarket.admin.web.mapper.ShopMapper;
import ru.vakoom.gunmarket.commondatalayer.model.Product;
import ru.vakoom.gunmarket.commondatalayer.model.Shop;

import java.util.List;

@Slf4j@RestController
@RequiredArgsConstructor
public class ShopApiController {

    private final ShopService shopService;
    private final ShopMapper shopMapper;

    @CrossOrigin
    @GetMapping(value = "/shop/{shopName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ShopDto getByName(@PathVariable String shopName) {
        Shop shop = shopService.getByName(shopName);
        return shopMapper.shopToShopDto(shop);
    }

}
