package ru.vakoom.gunmarket.admin.web.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vakoom.gunmarket.admin.scrapper.ProductScrapper;
import ru.vakoom.gunmarket.admin.service.ProductService;
import ru.vakoom.gunmarket.admin.web.dto.ProductDto;
import ru.vakoom.gunmarket.admin.web.mapper.ProductMapper;
import ru.vakoom.gunmarket.commondatalayer.model.Product;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ProductRefreshApiController implements ProductRefreshApi {

    private final ProductService productService;
    private final ProductScrapper productScrapper;
    private final ProductMapper productMapper;


    @CrossOrigin
    @PostMapping(value = "products", produces = MediaType.APPLICATION_JSON_VALUE)
    public void addList(@RequestBody List<ProductDto> productDtos){
        productService.saveList(productMapper.productDtosToProducts(productDtos));
    }

    @CrossOrigin
    @GetMapping(value = "/refreshProducts", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Product>> refreshProducts() {
        List<Product> products = productScrapper.refreshGunProducts();
        return ResponseEntity.ok(products);
    }

}
