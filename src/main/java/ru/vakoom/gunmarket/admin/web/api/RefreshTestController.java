package ru.vakoom.gunmarket.admin.web.api;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.vakoom.gunmarket.admin.service.ImageLoaderService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class RefreshTestController {

    private final ImageLoaderService imageLoaderService;

    @GetMapping("/getImages")
    @ApiOperation(value = "Load images for products by links",
            response = List.class)
    public List<String> loadImages() {
        return imageLoaderService.loadImages();
    }

}
