package com.karane.changeservice.controllers.validators;

import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class BillValidator {
    private static final Set<Integer> validBills = Set.of(1, 2, 5, 10, 20, 50, 100);
    public boolean isValid(int bill) {
        return validBills.contains(bill);
    }

    public boolean isNotValid(int bill) {
        return ! isValid(bill);
    }
}
