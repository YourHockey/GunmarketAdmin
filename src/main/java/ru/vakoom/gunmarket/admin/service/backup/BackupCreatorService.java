package ru.vakoom.gunmarket.admin.service.backup;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.vakoom.gunmarket.admin.repo.BrandRepo;
import ru.vakoom.gunmarket.admin.repo.ShopRepo;
import ru.vakoom.gunmarket.admin.repo.TypeRepo;
import ru.vakoom.gunmarket.commondatalayer.model.Brand;
import ru.vakoom.gunmarket.commondatalayer.model.Product;
import ru.vakoom.gunmarket.commondatalayer.model.Shop;
import ru.vakoom.gunmarket.commondatalayer.model.Type;
import ru.vakoom.gunmarket.commondatalayer.repo.ProductRepo;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BackupCreatorService {

    private final BrandRepo brandRepo;
    private final ProductRepo productRepo;
    private final ShopRepo shopRepo;
    private final TypeRepo typeRepo;
    private static ObjectMapper objectMapper = new ObjectMapper();

    private final String srcDir = "src\\main\\resources\\backup-files\\";
    private final String brandDir = "brand\\";
    private final String productDir = "product\\";
    private final String shopDir = "shop\\";
    private final String typeDir = "type\\";

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_hh-mm");

    public void create() {
        //ToDo добавить проверку на наличие директорий, создать если их нет
        try {
            createProductBackup();
            createBrandBackup();
            createShopBackup();
            createTypeBackup();
        } catch (IOException e) {
            log.error("Backup creating ERROR - " + e.getMessage());
        }
    }

    private void createBrandBackup() throws IOException {
        List<Brand> brands = brandRepo.findAll();
        createBackup(brands,brandDir,"brands-");
    }

    private void createProductBackup() throws IOException {
        List<Product> products = productRepo.findAll();
        createBackup(products,productDir,"products-");
    }

    private void createShopBackup() throws IOException {
        List<Shop> shops = shopRepo.findAll();
        createBackup(shops,shopDir,"shops-");
    }

    private void createTypeBackup() throws IOException {
        List<Type> types = typeRepo.findAll();
        createBackup(types,typeDir,"types-");
    }

    private void createBackup(List objects, String objDir, String objName) throws IOException {
        String fileName = srcDir + objDir + objName + dateFormat.format(new Date()) + ".json";
        objectMapper.writeValue(new File(fileName), objects);
        log.info("Backup file " + fileName + " added");
    }

}
