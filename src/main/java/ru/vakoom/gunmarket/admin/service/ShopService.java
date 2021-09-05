package ru.vakoom.gunmarket.admin.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.vakoom.gunmarket.admin.exception.ShopNotFoundException;
import ru.vakoom.gunmarket.admin.repo.ShopRepo;
import ru.vakoom.gunmarket.commondatalayer.model.Shop;

@Service
@RequiredArgsConstructor
public class ShopService {

    private final ShopRepo shopRepo;

    public Shop getByName(String name) {
        return shopRepo.findByName(name).orElseThrow(ShopNotFoundException::new);
    }

}
