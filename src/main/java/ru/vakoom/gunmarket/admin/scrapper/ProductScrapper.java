package ru.vakoom.gunmarket.admin.scrapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import ru.vakoom.gunmarket.admin.exception.TypeNotFoundException;
import ru.vakoom.gunmarket.admin.service.BrandService;
import ru.vakoom.gunmarket.admin.service.ProductService;
import ru.vakoom.gunmarket.admin.service.TypeService;
import ru.vakoom.gunmarket.commondatalayer.model.Brand;
import ru.vakoom.gunmarket.commondatalayer.model.Product;
import ru.vakoom.gunmarket.commondatalayer.model.Type;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProductScrapper implements InitializingBean {

    private final TypeService typeService;
    private final BrandService brandService;
    private final ProductService productService;

    Map<String, Map<String, String>> scrappingItems;

    @Override
    public void afterPropertiesSet() throws Exception {
        scrappingItems = fromJson("/scrappingitems.json");
    }

    public List<Product> refreshGunProducts() {
        List<Product> products = scrappingItems.entrySet().stream()
                .map(item -> {
                    String type = item.getKey();
                    return item.getValue().entrySet().stream()
                            .map(nameUrl -> getProduct(type, nameUrl.getKey(), nameUrl.getValue()))
                            .collect(Collectors.toList());
                })
                .flatMap(List::stream)
                .peek(productService::save)
                .collect(Collectors.toList());
        return products;
    }

    private Product getProduct(String type, String name, String url) {
        Document doc;
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            log.error("Failed to get product page with url {}", url);
            throw new RuntimeException("Failed to get product page with url " + url);
        }

        Product product = new Product()
                .setName(name)
                .setExtendedName(scrapExtendedName(doc))
                .setBrand(scrapBrand(doc))
                .setSrcImageUrl(scrapeImageUrl(doc))
                .setType(scrapType(type));
        return product;
    }

    private String scrapeImageUrl(Document doc) {
        String imgLine = doc.getElementsByClass("swiper-wrapper").get(0).getElementsByClass("swiper-slide").get(0).getElementsByTag("img").get(0).attr("src");
        imgLine = imgLine.replaceAll("320_320", "720_720");
        return "https://www.huntworld.ru/" + imgLine;
    }

    private String scrapExtendedName(Document doc) {
        return doc.getElementsByClass("item-head").get(0).getElementsByTag("h1").get(0).text();
    }

    private Brand scrapBrand(Document doc) {
        Element element = doc.getElementsByClass("block-detail").get(0);
        Elements specifications = element.getElementsByClass("specifications").get(0)
                .getElementsByTag("tr");

        Brand brand = specifications.stream()
                .filter(specificationLine -> specificationLine.text().contains("Бренд"))
                .map(specificationLine -> {
                    String text = specificationLine.text().replaceAll("Бренд: ", "");
                    return brandService.getByShortName(text);
                })
                .findFirst().get();

        return brand;
    }

    private Type scrapType(String typeShowName) {
        return typeService.findByShowName(typeShowName).orElseThrow(TypeNotFoundException::new);
    }

    private static Map<String, Map<String, String>> fromJson(String path) {
        try {
            log.info("Reading categories from path={}", path);
            return new ObjectMapper().readValue(new ClassPathResource(path).getInputStream(), Map.class);
        } catch (IOException e) {
            log.error("Converting of json failed. Failed file path: {}", path);
            log.error(e.toString());
            throw new UncheckedIOException(e);
        }
    }
}
