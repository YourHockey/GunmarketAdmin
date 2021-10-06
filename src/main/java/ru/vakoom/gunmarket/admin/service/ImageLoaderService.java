package ru.vakoom.gunmarket.admin.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import ru.vakoom.gunmarket.commondatalayer.model.Product;
import ru.vakoom.gunmarket.commondatalayer.repo.ProductRepo;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class ImageLoaderService {
    private final ProductRepo productRepo;
    @Value("${product.photo.dir}")
    private String imgDir;

    /**
     * Load and save/update all images from product.imageLink
     * Saving goes in format product.brand_product.model_product.age.png
     * return List of images names that was loaded
     */
    public List<String> loadImages() {
        List<Product> products = productRepo.findAll();
        List<String> loadedImgLinks = new ArrayList<>();
        // TODO: проход и скачивание по тем, у кого есть срцИмгЛинк, но нет имгЛинк
        for (Product product : products) {
            String type = product.getType().getShowName().replaceAll("/","-");
            String imgName = product.getBrand().getShortName() + "_" + product.getName() + ".png";
            try {
                var url = new URL(product.getSrcImageUrl());
                String imgDirName = System.getProperty("user.home") + "/" + imgDir;
                if (Files.notExists(Paths.get(imgDirName)))
                    Files.createDirectory(Paths.get(imgDirName)); // redundant perhaps
                if (Files.notExists(Paths.get(imgDirName, type))) Files.createDirectory(Paths.get(imgDirName, type));
                String imgPath = imgDirName + "/" + type + "/" + imgName;
                FileCopyUtils.copy(url.openStream(), new FileOutputStream(imgPath));
                product.setImageUrl(imgPath);
                loadedImgLinks.add(imgPath);
            } catch (IOException e) {
                log.error("Image downloading failed [srcImageLink={}; {}]", product.getSrcImageUrl(), e.getMessage());
            }
        }
        productRepo.saveAll(products);
        return loadedImgLinks;
    }
}
