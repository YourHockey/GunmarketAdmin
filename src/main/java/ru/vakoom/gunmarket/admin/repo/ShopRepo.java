package ru.vakoom.gunmarket.admin.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vakoom.gunmarket.commondatalayer.model.Brand;
import ru.vakoom.gunmarket.commondatalayer.model.Shop;

import java.util.Optional;

public interface ShopRepo extends JpaRepository<Shop, Long> {
    Optional<Shop> findByName(String name);
}
