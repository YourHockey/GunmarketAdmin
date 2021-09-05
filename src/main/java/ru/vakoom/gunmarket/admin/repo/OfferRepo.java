package ru.vakoom.gunmarket.admin.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.vakoom.gunmarket.commondatalayer.model.Offer;

import java.util.Optional;

public interface OfferRepo extends JpaRepository<Offer, Long> {

    @Query(value = "SELECT MIN(price) FROM offer WHERE product_id = :productId AND in_stock = true", nativeQuery = true)
    Double calculateMinPriceByProduct(Long productId);

    @Query(value = "SELECT MAX(price) FROM offer WHERE product_id = :productId AND in_stock = true", nativeQuery = true)
    Double calculateMaxPriceByProduct(Long productId);

    Optional<Offer> findByProductInShopId(String productInSopId);

    @Deprecated
    default void saveOrUpdate(Offer offer) {
        this.findByProductInShopId(offer.getProductInShopId()).ifPresentOrElse((o) -> {
            this.save(offer.setProductInShopId(o.getProductInShopId()));
        }, () -> {
            this.save(offer);
        });
    }

}
