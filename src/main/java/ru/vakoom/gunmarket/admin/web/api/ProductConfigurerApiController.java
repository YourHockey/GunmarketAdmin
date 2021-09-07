package ru.vakoom.gunmarket.admin.web.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.vakoom.gunmarket.admin.service.ProductService;
import ru.vakoom.gunmarket.admin.web.dto.ProductDto;
import ru.vakoom.gunmarket.admin.web.mapper.ProductMapper;
import ru.vakoom.gunmarket.commondatalayer.model.Product;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ProductConfigurerApiController implements ProductConfigurerApi {

    private final ProductService productService;
    private final ProductMapper productMapper;

    @CrossOrigin
    @GetMapping(value = "/products", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ProductDto> getProducts() {
        List<Product> products = productService.getAll();
        return productMapper.productsToProductDtos(products);
    }

    //ToDo возвращать айди продукта взад
    @CrossOrigin
    @PostMapping(value = "product", produces = MediaType.APPLICATION_JSON_VALUE)
    public Product addProduct(@RequestBody ProductDto productDto) {
        Product product = productMapper.productDtoToProduct(productDto);
        try {
            productService.save(product);
        } catch (Exception e) {
            throw e;
        }
        return product;
    }

}
