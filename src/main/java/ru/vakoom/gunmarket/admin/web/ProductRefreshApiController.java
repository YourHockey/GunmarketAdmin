package ru.vakoom.gunmarket.admin.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.vakoom.gunmarket.admin.scrapper.ProductScrapper;
import ru.vakoom.gunmarket.commondatalayer.model.Product;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ProductRefreshApiController implements ProductRefreshApi {

    private final ProductScrapper productScrapper;

    @CrossOrigin
    @GetMapping(value = "/refreshProducts", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Product>> refreshProducts() {
        List<Product> products = productScrapper.refreshGunProducts();
        return ResponseEntity.ok(products);
    }

}
