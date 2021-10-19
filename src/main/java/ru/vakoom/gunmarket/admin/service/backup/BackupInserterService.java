package ru.vakoom.gunmarket.admin.service.backup;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.vakoom.gunmarket.admin.repo.OfferRepo;
import ru.vakoom.gunmarket.commondatalayer.model.Product;
import ru.vakoom.gunmarket.commondatalayer.repo.ProductRepo;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BackupInserterService {

    private final ProductRepo productRepo;
    private final OfferRepo offerRepo;
    private static ObjectMapper objectMapper = new ObjectMapper();

    private final String srcDir = "src\\main\\resources\\backup-files\\";
    private final String productDir = "product\\";
    private final String productStartFileName = "\\products-";
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_hh");

    /**
     * Обновляем продукты в соответствии с последним бэкапом
     * Чистим таблицу офферов (удаляем все где шоп айди больше 0, то есть все)
     * Чистим таблицу продуктов (удаляем все где продакт айди больше 0, то есть все)
     * Вставляем продукты из последнего бэкапа
     */
    public void refreshProductsLastBackupFile() {
        offerRepo.deleteAll();
        productRepo.deleteAll();
        insertProductsByLastBackupFile();
    }

    /**
     * Вставляем продукты в таблицу по последнему сохраненному бэкап файлу
     */
    public void insertProductsByLastBackupFile() {
        String productLastBackupFileName = getLastObjectBackupFileName(productDir, productStartFileName);
        insertProductsByBackupFileName(productLastBackupFileName);
    }

    /**
     * Вставляем продукты в таблицу по имени бэкап файла
     */
    public void insertProductsByBackupFileName(String productBackupFileName) {
        File productBackup = new File(productBackupFileName);
        List<Product> products = new ArrayList<>();
        try {
            products = objectMapper.readValue(productBackup, new TypeReference<List<Product>>() {
            });
        } catch (IOException e) {
            log.error("Backup inserting ERROR - " + e.getMessage());
        }

        if (!products.isEmpty()) {
            log.info("Inserting products from backup " + productBackup + " started");
            productRepo.saveAll(products);
        }
    }

    /**
     * Формируем имя последнего бэкап файла по директории объекта и началу названия файла зависящее от объекта
     * Ex. brandDir = "brand\\" и brandStartFileName = "\\brands-";
     * Дают src\main\resources\backup-files\brand\brands-2021-10-13_08-50.json
     */
    private String getLastObjectBackupFileName(String objectDirName, String objLineName) {
        File objectDir = new File(srcDir + objectDirName);
        String lastModifyDate = dateFormat.format(new Date(objectDir.lastModified()));
        return objectDir + objLineName + lastModifyDate + ".json";
    }

}
