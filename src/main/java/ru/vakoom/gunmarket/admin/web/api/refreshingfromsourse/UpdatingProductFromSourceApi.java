package ru.vakoom.gunmarket.admin.web.api.refreshingfromsourse;

import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import ru.vakoom.gunmarket.admin.web.dto.ProductDto;
import ru.vakoom.gunmarket.commondatalayer.model.Product;

import java.util.List;

public interface UpdatingProductFromSourceApi {

    @ApiOperation(value = "!!!",
            notes = "!!!",
            response = ResponseEntity.class)
    ResponseEntity<List<Product>> refreshProducts();

    @ApiOperation(value = "!!!",
            notes = "!!!")
    void addList( List<ProductDto> productDtos);

}
