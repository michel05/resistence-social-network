package com.michel.projects.starwars.resistancesocialnetwork.domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import static com.michel.projects.starwars.resistancesocialnetwork.domain.exceptions.Messages.REBEL_NOT_FOUND;

public class RebelHasNoItemToExchangeException extends ResponseStatusException {

    public RebelHasNoItemToExchangeException() {
        super(HttpStatus.NOT_FOUND, REBEL_NOT_FOUND);
    }
}
