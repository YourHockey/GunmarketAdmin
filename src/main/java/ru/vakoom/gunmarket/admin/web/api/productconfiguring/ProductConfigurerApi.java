package ru.vakoom.gunmarket.admin.web.api.productconfiguring;

import io.swagger.annotations.ApiOperation;
import ru.vakoom.gunmarket.admin.web.dto.ProductDto;
import ru.vakoom.gunmarket.commondatalayer.model.Product;

import java.util.List;

public interface ProductConfigurerApi {

    @ApiOperation(value = "!!!",
            notes = "!!!",
            response = Product.class)
    Product addProduct(ProductDto product);

    @ApiOperation(value = "!!!",
            notes = "!!!",
            response = List.class)
    List<ProductDto> getProducts();

}
