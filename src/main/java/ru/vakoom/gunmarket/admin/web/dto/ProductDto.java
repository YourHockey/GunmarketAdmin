package ru.vakoom.gunmarket.admin.web.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ProductDto {

    private String name;
    private String extendedName;
    private String srcImageUrl;
    private String brand;
    private String type;

}
