package ru.vakoom.gunmarket.admin.web.api;

import io.swagger.annotations.ApiOperation;

import java.util.Map;

public interface CompatibilityConfigurerApi {

    @ApiOperation(value = "!!!",
            notes = "!!!",
            response = Long.class)
    Long addOrUpdateCompatibility(Map<String, String> compatibilityIds);

    @ApiOperation(value = "!!!",
            notes = "!!!")
    void deleteCompatibility(String productIdsPair);

}
