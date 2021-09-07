package ru.vakoom.gunmarket.admin.web.api;

import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import ru.vakoom.gunmarket.commondatalayer.dto.OfferDto;
import ru.vakoom.gunmarket.commondatalayer.model.Product;

import java.util.List;

public interface OfferReceivingApi {

    @ApiOperation(value = "!!!",
            notes = "!!!",
            response = ResponseEntity.class)
    ResponseEntity<List<OfferDto>> receiveOffersFromScrapping(List<OfferDto> offerDtos);

}
