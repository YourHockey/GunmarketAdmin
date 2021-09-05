package ru.vakoom.gunmarket.admin.web.controller;

import io.swagger.annotations.ApiOperation;
import ru.vakoom.gunmarket.admin.web.dto.ProductDto;
import ru.vakoom.gunmarket.commondatalayer.model.Product;

public interface ProductApi {

    @ApiOperation(value = "!!!",
            notes = "!!!",
            response = Product.class)
    Product addProduct(ProductDto product);

}
