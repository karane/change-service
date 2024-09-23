package com.karane.changeservice.services;

import com.karane.changeservice.algorithms.CoinChangeAlgorithm;
import com.karane.changeservice.services.dtos.ChangeDto;
import com.karane.changeservice.services.dtos.CoinsDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CoinChangeAlgorithmTest {

    final private CoinChangeAlgorithm coinChangeAlgorithm = new CoinChangeAlgorithm();

    @Test
    void oneDollarBillTestWithNoCoins() {
        CoinsDto coinsDto = new CoinsDto();
        coinsDto.setOneCent(0);
        coinsDto.setFiveCents(0);
        coinsDto.setTenCents(0);
        coinsDto.setTwentyFiveCents(0);

        ChangeDto changeDto = coinChangeAlgorithm.coinChangeDP(1, coinsDto, false);
        assertEquals(0, changeDto.getTotalCoins());
        assertEquals(0, changeDto.getOneCent());
        assertEquals(0, changeDto.getFiveCents());
        assertEquals(0, changeDto.getTenCents());
        assertEquals(0, changeDto.getTwentyFiveCents());
    }

    @Test
    void oneDollarBillTest() {
        CoinsDto coinsDto = new CoinsDto();
        coinsDto.setOneCent(100);
        coinsDto.setFiveCents(100);
        coinsDto.setTenCents(100);
        coinsDto.setTwentyFiveCents(100);

        ChangeDto changeDto = coinChangeAlgorithm.coinChangeDP(1, coinsDto, false);
        assertEquals(4, changeDto.getTotalCoins());
        assertEquals(0, changeDto.getOneCent());
        assertEquals(0, changeDto.getFiveCents());
        assertEquals(0, changeDto.getTenCents());
        assertEquals(4, changeDto.getTwentyFiveCents());
    }

    @Test
    void baseMinCoinChangeTest() {
        CoinsDto coinsDto = new CoinsDto();
        coinsDto.setOneCent(10);
        coinsDto.setFiveCents(10);
        coinsDto.setTenCents(10);
        coinsDto.setTwentyFiveCents(10);

        ChangeDto changeDto = coinChangeAlgorithm.coinChangeDP(1, coinsDto, false, 1);
        assertEquals(1, changeDto.getTotalCoins());
        assertEquals(1, changeDto.getOneCent());
        assertEquals(0, changeDto.getFiveCents());
        assertEquals(0, changeDto.getTenCents());
        assertEquals(0, changeDto.getTwentyFiveCents());

        changeDto = coinChangeAlgorithm.coinChangeDP(5, coinsDto, false, 1);
        assertEquals(1, changeDto.getTotalCoins());
        assertEquals(0, changeDto.getOneCent());
        assertEquals(1, changeDto.getFiveCents());
        assertEquals(0, changeDto.getTenCents());
        assertEquals(0, changeDto.getTwentyFiveCents());

        changeDto = coinChangeAlgorithm.coinChangeDP(10, coinsDto, false, 1);
        assertEquals(1, changeDto.getTotalCoins());
        assertEquals(0, changeDto.getOneCent());
        assertEquals(0, changeDto.getFiveCents());
        assertEquals(1, changeDto.getTenCents());
        assertEquals(0, changeDto.getTwentyFiveCents());

        changeDto = coinChangeAlgorithm.coinChangeDP(25, coinsDto, false, 1);
        assertEquals(1, changeDto.getTotalCoins());
        assertEquals(0, changeDto.getOneCent());
        assertEquals(0, changeDto.getFiveCents());
        assertEquals(0, changeDto.getTenCents());
        assertEquals(1, changeDto.getTwentyFiveCents());


        changeDto = coinChangeAlgorithm.coinChangeDP(12, coinsDto, false, 1);
        assertEquals(3, changeDto.getTotalCoins());
        assertEquals(2, changeDto.getOneCent());
        assertEquals(0, changeDto.getFiveCents());
        assertEquals(1, changeDto.getTenCents());
        assertEquals(0, changeDto.getTwentyFiveCents());

        changeDto = coinChangeAlgorithm.coinChangeDP(46, coinsDto, false, 1);
        assertEquals(4, changeDto.getTotalCoins());
        assertEquals(1, changeDto.getOneCent());
        assertEquals(0, changeDto.getFiveCents());
        assertEquals(2, changeDto.getTenCents());
        assertEquals(1, changeDto.getTwentyFiveCents());
    }

    @Test
    void baseMinCoinChangeNotEnoughCoinsTest() {
        CoinsDto coinsDto = new CoinsDto();
        coinsDto.setOneCent(0);
        coinsDto.setFiveCents(1);
        coinsDto.setTenCents(1);
        coinsDto.setTwentyFiveCents(1);

        ChangeDto changeDto = coinChangeAlgorithm.coinChangeDP(6, coinsDto, false, 1);
        assertEquals(0, changeDto.getTotalCoins());
        assertEquals(0, changeDto.getOneCent());
        assertEquals(0, changeDto.getFiveCents());
        assertEquals(0, changeDto.getTenCents());
        assertEquals(0, changeDto.getTwentyFiveCents());
    }

    @Test
    void baseMaxCoinChangeTest() {
        CoinsDto coinsDto = new CoinsDto();
        coinsDto.setOneCent(10);
        coinsDto.setFiveCents(10);
        coinsDto.setTenCents(10);
        coinsDto.setTwentyFiveCents(10);

        ChangeDto changeDto = coinChangeAlgorithm.coinChangeDP(5, coinsDto, true, 1);
        assertEquals(5, changeDto.getTotalCoins());
        assertEquals(5, changeDto.getOneCent());
        assertEquals(0, changeDto.getFiveCents());
        assertEquals(0, changeDto.getTenCents());
        assertEquals(0, changeDto.getTwentyFiveCents());

        changeDto = coinChangeAlgorithm.coinChangeDP(10, coinsDto, true, 1);
        assertEquals(10, changeDto.getTotalCoins());
        assertEquals(10, changeDto.getOneCent());
        assertEquals(0, changeDto.getFiveCents());
        assertEquals(0, changeDto.getTenCents());
        assertEquals(0, changeDto.getTwentyFiveCents());

        changeDto = coinChangeAlgorithm.coinChangeDP(12, coinsDto, true, 1);
        assertEquals(8, changeDto.getTotalCoins());
        assertEquals(7, changeDto.getOneCent());
        assertEquals(1, changeDto.getFiveCents());
        assertEquals(0, changeDto.getTenCents());
        assertEquals(0, changeDto.getTwentyFiveCents());

    }

    @Test
    void baseMaxCoinChangeNotEnoughCoinsTest() {
        CoinsDto coinsDto = new CoinsDto();
        coinsDto.setOneCent(0);
        coinsDto.setFiveCents(1);
        coinsDto.setTenCents(1);
        coinsDto.setTwentyFiveCents(1);

        ChangeDto changeDto = coinChangeAlgorithm.coinChangeDP(6, coinsDto, true, 1);
        assertEquals(0, changeDto.getTotalCoins());
        assertEquals(0, changeDto.getOneCent());
        assertEquals(0, changeDto.getFiveCents());
        assertEquals(0, changeDto.getTenCents());
        assertEquals(0, changeDto.getTwentyFiveCents());
    }

    @Test
    void oneTwoDollarBillTest() {
        CoinsDto coinsDto = new CoinsDto();
        coinsDto.setOneCent(100);
        coinsDto.setFiveCents(100);
        coinsDto.setTenCents(100);
        coinsDto.setTwentyFiveCents(100);

        ChangeDto changeDto = coinChangeAlgorithm.coinChangeDP(2, coinsDto, false);
        assertEquals(8, changeDto.getTotalCoins());
        assertEquals(0, changeDto.getOneCent());
        assertEquals(0, changeDto.getFiveCents());
        assertEquals(0, changeDto.getTenCents());
        assertEquals(8, changeDto.getTwentyFiveCents());
    }


    @Test
    void oneFiveDollarBillTest() {
        CoinsDto coinsDto = new CoinsDto();
        coinsDto.setOneCent(100);
        coinsDto.setFiveCents(100);
        coinsDto.setTenCents(100);
        coinsDto.setTwentyFiveCents(100);

        ChangeDto changeDto = coinChangeAlgorithm.coinChangeDP(5, coinsDto, false);
        assertEquals(20, changeDto.getTotalCoins());
        assertEquals(0, changeDto.getOneCent());
        assertEquals(0, changeDto.getFiveCents());
        assertEquals(0, changeDto.getTenCents());
        assertEquals(20, changeDto.getTwentyFiveCents());
    }

    @Test
    void oneDollarBillNo25CentsTest() {
        CoinsDto coinsDto = new CoinsDto();
        coinsDto.setOneCent(100);
        coinsDto.setFiveCents(100);
        coinsDto.setTenCents(100);
        coinsDto.setTwentyFiveCents(0);

        ChangeDto changeDto = coinChangeAlgorithm.coinChangeDP(1, coinsDto, false);
        assertEquals(10, changeDto.getTotalCoins());
        assertEquals(0, changeDto.getOneCent());
        assertEquals(0, changeDto.getFiveCents());
        assertEquals(10, changeDto.getTenCents());
        assertEquals(0, changeDto.getTwentyFiveCents());
    }

    @Test
    void oneDollarBillJustOneCents() {
        CoinsDto coinsDto = new CoinsDto();
        coinsDto.setOneCent(100);
        coinsDto.setFiveCents(0);
        coinsDto.setTenCents(0);
        coinsDto.setTwentyFiveCents(0);

        ChangeDto changeDto = coinChangeAlgorithm.coinChangeDP(1, coinsDto, false);
        assertEquals(100, changeDto.getTotalCoins());
        assertEquals(100, changeDto.getOneCent());
        assertEquals(0, changeDto.getFiveCents());
        assertEquals(0, changeDto.getTenCents());
        assertEquals(0, changeDto.getTwentyFiveCents());
    }


    @Test
    void oneDollarBillJustFiveCents() {
        CoinsDto coinsDto = new CoinsDto();
        coinsDto.setOneCent(0);
        coinsDto.setFiveCents(100);
        coinsDto.setTenCents(0);
        coinsDto.setTwentyFiveCents(0);

        ChangeDto changeDto = coinChangeAlgorithm.coinChangeDP(1, coinsDto, false);
        assertEquals(20, changeDto.getTotalCoins());
        assertEquals(0, changeDto.getOneCent());
        assertEquals(20, changeDto.getFiveCents());
        assertEquals(0, changeDto.getTenCents());
        assertEquals(0, changeDto.getTwentyFiveCents());
    }


    @Test
    void oneDollarBillTestMaximizeCoins() {
        CoinsDto coinsDto = new CoinsDto();
        coinsDto.setOneCent(100);
        coinsDto.setFiveCents(100);
        coinsDto.setTenCents(100);
        coinsDto.setTwentyFiveCents(100);

        ChangeDto changeDto = coinChangeAlgorithm.coinChangeDP(1, coinsDto, true);
        assertEquals(100, changeDto.getTotalCoins());
        assertEquals(100, changeDto.getOneCent());
        assertEquals(0, changeDto.getFiveCents());
        assertEquals(0, changeDto.getTenCents());
        assertEquals(0, changeDto.getTwentyFiveCents());
    }

    @Test
    void twoDollarBillTestMaximizeCoinsFiveCentOnly() {
        CoinsDto coinsDto = new CoinsDto();
        coinsDto.setOneCent(0);
        coinsDto.setFiveCents(100);
        coinsDto.setTenCents(0);
        coinsDto.setTwentyFiveCents(0);

        ChangeDto changeDto = coinChangeAlgorithm.coinChangeDP(1, coinsDto, true);
        assertEquals(20, changeDto.getTotalCoins());
        assertEquals(0, changeDto.getOneCent());
        assertEquals(20, changeDto.getFiveCents());
        assertEquals(0, changeDto.getTenCents());
        assertEquals(0, changeDto.getTwentyFiveCents());
    }

    @Test
    void oneDollarBillTestMaximizeCoinsTenCentOnly() {
        CoinsDto coinsDto = new CoinsDto();
        coinsDto.setOneCent(0);
        coinsDto.setFiveCents(0);
        coinsDto.setTenCents(100);
        coinsDto.setTwentyFiveCents(100);

        ChangeDto changeDto = coinChangeAlgorithm.coinChangeDP(1, coinsDto, true);
        assertEquals(10, changeDto.getTotalCoins());
        assertEquals(0, changeDto.getOneCent());
        assertEquals(0, changeDto.getFiveCents());
        assertEquals(10, changeDto.getTenCents());
        assertEquals(0, changeDto.getTwentyFiveCents());
    }

    @Test
    void hundredDollarBillTestMaximize() {
        CoinsDto coinsDto = new CoinsDto();
        coinsDto.setOneCent(1000);
        coinsDto.setFiveCents(1000);
        coinsDto.setTenCents(1000);
        coinsDto.setTwentyFiveCents(1000);

        ChangeDto changeDto = coinChangeAlgorithm.coinChangeDP(100, coinsDto, true);
        assertEquals(2400, changeDto.getTotalCoins());
        assertEquals(1000, changeDto.getOneCent());
        assertEquals(1000, changeDto.getFiveCents());
        assertEquals(400, changeDto.getTenCents());
        assertEquals(0, changeDto.getTwentyFiveCents());
    }

    @Test
    void hundredDollarBillTest() {
        CoinsDto coinsDto = new CoinsDto();
        coinsDto.setOneCent(1000);
        coinsDto.setFiveCents(1000);
        coinsDto.setTenCents(1000);
        coinsDto.setTwentyFiveCents(1000);

        ChangeDto changeDto = coinChangeAlgorithm.coinChangeDP(100, coinsDto, false);
        assertEquals(400, changeDto.getTotalCoins());
        assertEquals(0, changeDto.getOneCent());
        assertEquals(0, changeDto.getFiveCents());
        assertEquals(0, changeDto.getTenCents());
        assertEquals(400, changeDto.getTwentyFiveCents());
    }

    @Test
    void noSolutionHundredDollarBillTestMaximize() {
        CoinsDto coinsDto = new CoinsDto();
        coinsDto.setOneCent(0);
        coinsDto.setFiveCents(0);
        coinsDto.setTenCents(0);
        coinsDto.setTwentyFiveCents(0);

        ChangeDto changeDto = coinChangeAlgorithm.coinChangeDP(100, coinsDto, false);
        assertEquals(0, changeDto.getTotalCoins());
        assertEquals(0, changeDto.getOneCent());
        assertEquals(0, changeDto.getFiveCents());
        assertEquals(0, changeDto.getTenCents());
        assertEquals(0, changeDto.getTwentyFiveCents());
    }

}