package com.michel.projects.starwars.resistancesocialnetwork.domain.service;

import org.springframework.stereotype.Service;

@Service
public class BetrayerValidation {

    public boolean validateIfBetrayer(Integer totalMarks) {
        return totalMarks >= 3;
    }
}
