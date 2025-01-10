package com.bytes.Cards.service.Impl;

import com.bytes.Cards.constants.CardsConstants;
import com.bytes.Cards.dto.CardsDto;
import com.bytes.Cards.entity.Cards;
import com.bytes.Cards.exception.CardAlreadyExistException;
import com.bytes.Cards.exception.ResourceNotFoundException;
import com.bytes.Cards.mapper.CardsMapper;
import com.bytes.Cards.repository.CardsRepository;
import com.bytes.Cards.service.ICardsServices;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor

public class CardsServicesImpl implements ICardsServices {

    private CardsRepository cardsRepository;


    @Override
    public void createCard(String mobileNumber) {
        Optional<Cards> cards = cardsRepository.findByMobileNumber(mobileNumber);
        if (cards.isPresent()) {
            throw new CardAlreadyExistException("Card already exist with mobileNumber: " + mobileNumber);
        }
        cardsRepository.save(createNewCard(mobileNumber));
    }

    private Cards createNewCard(String mobileNumber) {
        Cards newCard = new Cards();
        long randomCardNumber = 100000000000L + new Random().nextInt(900000000);
        newCard.setCardNumber(Long.toString(randomCardNumber));
        newCard.setMobileNumber(mobileNumber);
        newCard.setCardType(CardsConstants.CREDIT_CARD);
        newCard.setTotalLimit(CardsConstants.NEW_CARD_LIMIT);
        newCard.setAmountUsed(0);
        newCard.setAvailableAmount(CardsConstants.NEW_CARD_LIMIT);
        return newCard;
    }

    @Override
    public CardsDto fetchCard(String mobileNumber) {
        Cards cards = cardsRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Cards", "mobileNumber", mobileNumber));

        CardsMapper cardsMapper = new CardsMapper();
        return cardsMapper.mapperToDto(cards, new CardsDto());

    }

    @Override
    public boolean updateCard(CardsDto cardsDto) {

        CardsMapper cardsMapper = new CardsMapper();
        Cards card = cardsMapper.mapperToCards(cardsDto, new Cards());
        Cards foundCard = cardsRepository.findByCardNumber(card.getCardNumber()).orElseThrow(
                () -> new ResourceNotFoundException("Cards", "Card Number", card.getCardNumber())
        );
        if (foundCard != null) {
            foundCard = cardsMapper.mapperToCards(cardsDto, foundCard);
            cardsRepository.save(foundCard);
            return true;
        } else {
            return false;
        }


    }

    @Override
    public boolean deleteCard(String mobileNumber) {
        Cards cards = cardsRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Cards", "mobileNumber", mobileNumber));
        if (cards != null) {
            cardsRepository.delete(cards);
            return true;
        } else {
            return false;
        }


    }
}
