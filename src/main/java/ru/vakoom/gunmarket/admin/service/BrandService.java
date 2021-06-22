package ru.vakoom.gunmarket.admin.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.vakoom.gunmarket.admin.repo.BrandRepo;
import ru.vakoom.gunmarket.commondatalayer.model.Brand;

@Service
@RequiredArgsConstructor
public class BrandService {

    private final BrandRepo brandRepo;

    public Brand getByShortName(String shortName) {
        return brandRepo.findByShortName(shortName);
    }

}
