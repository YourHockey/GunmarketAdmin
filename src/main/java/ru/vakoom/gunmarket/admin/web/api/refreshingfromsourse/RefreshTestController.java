package ru.vakoom.gunmarket.admin.web.api.refreshingfromsourse;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.vakoom.gunmarket.admin.service.ImageLoaderService;
import ru.vakoom.gunmarket.admin.service.backup.BackupCreatorService;
import ru.vakoom.gunmarket.admin.service.backup.BackupInserterService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class RefreshTestController {

    private final ImageLoaderService imageLoaderService;
    private final BackupCreatorService backupCreatorService;
    private final BackupInserterService backupInserterService;

    @GetMapping("/getImages")
    @ApiOperation(value = "Load images for products by links",
            response = List.class)
    public List<String> loadImages() {
        return imageLoaderService.loadImages();
    }

    @GetMapping(value = "/backup/create", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Create backup")
    public void createBackup() {
        backupCreatorService.create();
    }

    @GetMapping(value = "/backup/refresh/product", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Refresh product backup")
    public void refreshProductsByLastBackup() {
        backupInserterService.refreshProductsLastBackupFile();
    }

    @GetMapping(value = "/backup/insert/product", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Insert product backup")
    public void insertLastProductBackup() {
        backupInserterService.insertProductsByLastBackupFile();
    }

    @GetMapping(value = "/backup/insert/product/{backupFileName}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Insert product backup")
    public void insertProductBackupByName(@PathVariable String backupFileName) {
        backupInserterService.insertProductsByBackupFileName(backupFileName);
    }
}
