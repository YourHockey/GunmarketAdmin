package ru.vakoom.gunmarket.admin.web.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.vakoom.gunmarket.admin.exception.TypeNotFoundException;
import ru.vakoom.gunmarket.admin.service.BrandService;
import ru.vakoom.gunmarket.admin.service.TypeService;
import ru.vakoom.gunmarket.admin.web.dto.ProductDto;
import ru.vakoom.gunmarket.commondatalayer.model.Product;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ProductMapper {

    private final BrandService brandService;
    private final TypeService typeService;

    public List<ProductDto> productsToProductDtos(List<Product> products) {
        return products.stream().map(this::productToProductDto).collect(Collectors.toList());
    }

    public List<Product> productDtosToProducts(List<ProductDto> productDtos) {
        return productDtos.stream().map(this::productDtoToProduct).collect(Collectors.toList());
    }

    public Product productDtoToProduct(ProductDto productDto){
        return new Product()
                .setName(productDto.getName())
                .setBrand(brandService.getByShortName(productDto.getBrand()))
                .setSrcImageUrl(productDto.getSrcImageUrl())
                .setType(typeService.findByShowName(productDto.getType()).orElseThrow(TypeNotFoundException::new));
    }

    public ProductDto productToProductDto(Product product) {
        return new ProductDto()
                .setName(product.getName())
                .setBrand(product.getBrand().getShortName())
                .setSrcImageUrl(product.getSrcImageUrl())
                .setType(product.getType().getShowName());
    }


}

