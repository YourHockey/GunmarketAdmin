package ru.vakoom.gunmarket.admin.web;

import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import ru.gunmarket.model.Product;

import java.util.List;

public interface ProductRefreshApi {

    @ApiOperation(value = "!!!",
            notes = "!!!",
            response = ResponseEntity.class)
    ResponseEntity<List<Product>> refreshProducts();

}
