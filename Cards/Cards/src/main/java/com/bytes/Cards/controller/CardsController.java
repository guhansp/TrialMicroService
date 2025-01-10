package com.bytes.Cards.controller;

import com.bytes.Cards.constants.CardsConstants;
import com.bytes.Cards.dto.CardsDto;
import com.bytes.Cards.dto.ResponseDto;
import com.bytes.Cards.service.Impl.CardsServicesImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@Validated
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
public class CardsController {

    private CardsServicesImpl cardsService;

    @PostMapping(path = "/create")
    public ResponseEntity<ResponseDto> createCard(@RequestParam String mobileNumber) {
        cardsService.createCard(mobileNumber);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(CardsConstants.STATUS_201, CardsConstants.MESSAGE_201));

    }

    @GetMapping(path = "/fetch")
    public ResponseEntity<CardsDto> fetchCard(@RequestParam String mobileNumber) {
        CardsDto cardsDto = cardsService.fetchCard(mobileNumber);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(cardsDto);
    }

    @PutMapping(path = "/update")
    public ResponseEntity<ResponseDto> updateCard(@RequestBody CardsDto cardsDto) {
        boolean status = cardsService.updateCard(cardsDto);
        if (status) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(CardsConstants.STATUS_200, CardsConstants.MESSAGE_200));
        } else {
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(CardsConstants.STATUS_417, CardsConstants.STATUS_417));
        }

    }

    @DeleteMapping(path = "/delete")
    public ResponseEntity<ResponseDto> deleteCard(@RequestParam String mobileNumber) {
        boolean status = cardsService.deleteCard(mobileNumber);
        if (status) {
            return ResponseEntity
                    .status(HttpStatus.ACCEPTED)
                    .body(new ResponseDto(CardsConstants.STATUS_200, CardsConstants.MESSAGE_200));
        } else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ResponseDto(CardsConstants.STATUS_417, CardsConstants.MESSAGE_417_DELETE));
        }
    }

}
