package ru.vakoom.gunmarket.admin.web.api.offerextractingintegration;

import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import ru.vakoom.gunmarket.commondatalayer.dto.OfferDto;

import java.util.List;

public interface OfferReceivingApi {

    @ApiOperation(value = "!!!",
            notes = "!!!",
            response = ResponseEntity.class)
    ResponseEntity<List<OfferDto>> receiveOffersFromScrapping(List<OfferDto> offerDtos);

}
