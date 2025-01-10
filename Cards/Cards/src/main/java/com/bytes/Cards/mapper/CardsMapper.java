package com.bytes.Cards.mapper;

import com.bytes.Cards.dto.CardsDto;
import com.bytes.Cards.entity.Cards;


public class CardsMapper {

    public CardsDto mapperToDto(Cards card, CardsDto cardsDto) {
        cardsDto.setAmountUsed(card.getAmountUsed());
        cardsDto.setAvailableAmount(card.getAvailableAmount());
        cardsDto.setCardType(card.getCardType());
        cardsDto.setCardNumber(card.getCardNumber());
        cardsDto.setTotalLimit(card.getTotalLimit());
        cardsDto.setMobileNumber(card.getMobileNumber());
        return cardsDto;

    }

    public Cards mapperToCards(CardsDto cardDto, Cards card) {
        card.setAmountUsed(cardDto.getAmountUsed());
        card.setAvailableAmount(cardDto.getAvailableAmount());
        card.setCardType(cardDto.getCardType());
        card.setCardNumber(cardDto.getCardNumber());
        card.setTotalLimit(cardDto.getTotalLimit());
        card.setMobileNumber(cardDto.getMobileNumber());
        return card;
    }
}
