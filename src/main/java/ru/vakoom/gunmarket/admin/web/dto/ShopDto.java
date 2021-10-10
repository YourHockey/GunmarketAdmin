package ru.vakoom.gunmarket.admin.web.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ShopDto {

    private Long id;
    private String shopName;

}
