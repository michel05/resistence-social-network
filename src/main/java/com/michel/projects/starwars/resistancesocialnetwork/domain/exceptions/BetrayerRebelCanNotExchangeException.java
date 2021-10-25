package com.michel.projects.starwars.resistancesocialnetwork.domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import static com.michel.projects.starwars.resistancesocialnetwork.domain.exceptions.Messages.BETRAYER_CAN_NOT_EXCHANGE_ITEMS;

public class BetrayerRebelCanNotExchangeException extends ResponseStatusException {

    public BetrayerRebelCanNotExchangeException() {
        super(HttpStatus.NOT_ACCEPTABLE, BETRAYER_CAN_NOT_EXCHANGE_ITEMS);
    }
}
