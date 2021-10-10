package ru.vakoom.gunmarket.admin.web.mapper;

import ru.vakoom.gunmarket.admin.web.dto.ShopDto;
import ru.vakoom.gunmarket.commondatalayer.model.Shop;

public class ShopMapper {

    public ShopDto shopToShopDto(Shop shop){
        return new ShopDto()
                .setId(shop.getShopId())
                .setShopName(shop.getName());
    }

}
