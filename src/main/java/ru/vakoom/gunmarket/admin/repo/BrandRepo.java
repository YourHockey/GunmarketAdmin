package ru.vakoom.gunmarket.admin.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gunmarket.model.Brand;

public interface BrandRepo extends JpaRepository<Brand, Long> {
    Brand findByShortName(String shortName);
}
