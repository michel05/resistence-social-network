package com.michel.projects.starwars.resistancesocialnetwork.domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import static com.michel.projects.starwars.resistancesocialnetwork.domain.exceptions.Messages.ITEMS_HAVE_NO_SAME_POINT_QUANTITY;

public class ItemsHaveNoSamePointQuantityException extends ResponseStatusException {

    public ItemsHaveNoSamePointQuantityException() {
        super(HttpStatus.NOT_ACCEPTABLE, ITEMS_HAVE_NO_SAME_POINT_QUANTITY);
    }

}
