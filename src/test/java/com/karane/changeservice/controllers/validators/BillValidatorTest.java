package com.karane.changeservice.controllers.validators;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class BillValidatorTest {

    @Test
    void testValidInputs() {
        BillValidator validator = new BillValidator();

        assertTrue(validator.isValid(1));
        assertTrue(validator.isValid(2));
        assertTrue(validator.isValid(5));
        assertTrue(validator.isValid(10));
        assertTrue(validator.isValid(20));
        assertTrue(validator.isValid(50));
        assertTrue(validator.isValid(100));

        assertFalse(validator.isNotValid(1));
        assertFalse(validator.isNotValid(2));
        assertFalse(validator.isNotValid(5));
        assertFalse(validator.isNotValid(10));
        assertFalse(validator.isNotValid(20));
        assertFalse(validator.isNotValid(50));
        assertFalse(validator.isNotValid(100));
    }

    @Test
    void testNotValidInputs() {
        BillValidator validator = new BillValidator();

        assertFalse(validator.isValid(-1));
        assertFalse(validator.isValid(0));
        assertFalse(validator.isValid(101));

        assertFalse(validator.isValid(3));
        assertFalse(validator.isValid(6));
        assertFalse(validator.isValid(11));
        assertFalse(validator.isValid(21));
        assertFalse(validator.isValid(51));
        assertFalse(validator.isValid(99));


        assertTrue(validator.isNotValid(-1));
        assertTrue(validator.isNotValid(0));
        assertTrue(validator.isNotValid(101));

        assertTrue(validator.isNotValid(3));
        assertTrue(validator.isNotValid(6));
        assertTrue(validator.isNotValid(11));
        assertTrue(validator.isNotValid(21));
        assertTrue(validator.isNotValid(51));
        assertTrue(validator.isNotValid(99));
    }
}