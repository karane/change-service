package com.karane.changeservice.services;

import com.karane.changeservice.services.dtos.ChangeDto;
import com.karane.changeservice.services.dtos.CoinsDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CoinChangeAlgorithmTest {

    final private CoinChangeAlgorithm coinChangeAlgorithm = new CoinChangeAlgorithm();

    @Test
    void oneDollarBillTestWithNoCoins() {
        CoinsDto coinsDto = new CoinsDto();
        coinsDto.set_1_cent(0);
        coinsDto.set_5_cent(0);
        coinsDto.set_10_cent(0);
        coinsDto.set_25_cent(0);

        ChangeDto changeDto = coinChangeAlgorithm.coinChangeDP(1, coinsDto, false);
        assertEquals(0, changeDto.getTotalCoins());
        assertEquals(0, changeDto.get_1_cent());
        assertEquals(0, changeDto.get_5_cent());
        assertEquals(0, changeDto.get_10_cent());
        assertEquals(0, changeDto.get_25_cent());
    }

    @Test
    void oneDollarBillTest() {
        CoinsDto coinsDto = new CoinsDto();
        coinsDto.set_1_cent(100);
        coinsDto.set_5_cent(100);
        coinsDto.set_10_cent(100);
        coinsDto.set_25_cent(100);

        ChangeDto changeDto = coinChangeAlgorithm.coinChangeDP(1, coinsDto, false);
        assertEquals(4, changeDto.getTotalCoins());
        assertEquals(0, changeDto.get_1_cent());
        assertEquals(0, changeDto.get_5_cent());
        assertEquals(0, changeDto.get_10_cent());
        assertEquals(4, changeDto.get_25_cent());
    }

    @Test
    void baseMinCoinChangeTest() {
        CoinsDto coinsDto = new CoinsDto();
        coinsDto.set_1_cent(10);
        coinsDto.set_5_cent(10);
        coinsDto.set_10_cent(10);
        coinsDto.set_25_cent(10);

        ChangeDto changeDto = coinChangeAlgorithm.coinChangeDP(1, coinsDto, false, 1);
        assertEquals(1, changeDto.getTotalCoins());
        assertEquals(1, changeDto.get_1_cent());
        assertEquals(0, changeDto.get_5_cent());
        assertEquals(0, changeDto.get_10_cent());
        assertEquals(0, changeDto.get_25_cent());

        changeDto = coinChangeAlgorithm.coinChangeDP(5, coinsDto, false, 1);
        assertEquals(1, changeDto.getTotalCoins());
        assertEquals(0, changeDto.get_1_cent());
        assertEquals(1, changeDto.get_5_cent());
        assertEquals(0, changeDto.get_10_cent());
        assertEquals(0, changeDto.get_25_cent());

        changeDto = coinChangeAlgorithm.coinChangeDP(10, coinsDto, false, 1);
        assertEquals(1, changeDto.getTotalCoins());
        assertEquals(0, changeDto.get_1_cent());
        assertEquals(0, changeDto.get_5_cent());
        assertEquals(1, changeDto.get_10_cent());
        assertEquals(0, changeDto.get_25_cent());

        changeDto = coinChangeAlgorithm.coinChangeDP(25, coinsDto, false, 1);
        assertEquals(1, changeDto.getTotalCoins());
        assertEquals(0, changeDto.get_1_cent());
        assertEquals(0, changeDto.get_5_cent());
        assertEquals(0, changeDto.get_10_cent());
        assertEquals(1, changeDto.get_25_cent());


        changeDto = coinChangeAlgorithm.coinChangeDP(12, coinsDto, false, 1);
        assertEquals(3, changeDto.getTotalCoins());
        assertEquals(2, changeDto.get_1_cent());
        assertEquals(0, changeDto.get_5_cent());
        assertEquals(1, changeDto.get_10_cent());
        assertEquals(0, changeDto.get_25_cent());

        changeDto = coinChangeAlgorithm.coinChangeDP(46, coinsDto, false, 1);
        assertEquals(4, changeDto.getTotalCoins());
        assertEquals(1, changeDto.get_1_cent());
        assertEquals(0, changeDto.get_5_cent());
        assertEquals(2, changeDto.get_10_cent());
        assertEquals(1, changeDto.get_25_cent());
    }

    @Test
    void baseMinCoinChangeNotEnoughCoinsTest() {
        CoinsDto coinsDto = new CoinsDto();
        coinsDto.set_1_cent(0);
        coinsDto.set_5_cent(1);
        coinsDto.set_10_cent(1);
        coinsDto.set_25_cent(1);

        ChangeDto changeDto = coinChangeAlgorithm.coinChangeDP(6, coinsDto, false, 1);
        assertEquals(0, changeDto.getTotalCoins());
        assertEquals(0, changeDto.get_1_cent());
        assertEquals(0, changeDto.get_5_cent());
        assertEquals(0, changeDto.get_10_cent());
        assertEquals(0, changeDto.get_25_cent());
    }

    @Test
    void baseMaxCoinChangeTest() {
        CoinsDto coinsDto = new CoinsDto();
        coinsDto.set_1_cent(10);
        coinsDto.set_5_cent(10);
        coinsDto.set_10_cent(10);
        coinsDto.set_25_cent(10);

        ChangeDto changeDto = coinChangeAlgorithm.coinChangeDP(5, coinsDto, true, 1);
        assertEquals(5, changeDto.getTotalCoins());
        assertEquals(5, changeDto.get_1_cent());
        assertEquals(0, changeDto.get_5_cent());
        assertEquals(0, changeDto.get_10_cent());
        assertEquals(0, changeDto.get_25_cent());

        changeDto = coinChangeAlgorithm.coinChangeDP(10, coinsDto, true, 1);
        assertEquals(10, changeDto.getTotalCoins());
        assertEquals(10, changeDto.get_1_cent());
        assertEquals(0, changeDto.get_5_cent());
        assertEquals(0, changeDto.get_10_cent());
        assertEquals(0, changeDto.get_25_cent());

        changeDto = coinChangeAlgorithm.coinChangeDP(12, coinsDto, true, 1);
        assertEquals(8, changeDto.getTotalCoins());
        assertEquals(7, changeDto.get_1_cent());
        assertEquals(1, changeDto.get_5_cent());
        assertEquals(0, changeDto.get_10_cent());
        assertEquals(0, changeDto.get_25_cent());

    }

    @Test
    void baseMaxCoinChangeNotEnoughCoinsTest() {
        CoinsDto coinsDto = new CoinsDto();
        coinsDto.set_1_cent(0);
        coinsDto.set_5_cent(1);
        coinsDto.set_10_cent(1);
        coinsDto.set_25_cent(1);

        ChangeDto changeDto = coinChangeAlgorithm.coinChangeDP(6, coinsDto, true, 1);
        assertEquals(0, changeDto.getTotalCoins());
        assertEquals(0, changeDto.get_1_cent());
        assertEquals(0, changeDto.get_5_cent());
        assertEquals(0, changeDto.get_10_cent());
        assertEquals(0, changeDto.get_25_cent());
    }

    @Test
    void oneTwoDollarBillTest() {
        CoinsDto coinsDto = new CoinsDto();
        coinsDto.set_1_cent(100);
        coinsDto.set_5_cent(100);
        coinsDto.set_10_cent(100);
        coinsDto.set_25_cent(100);

        ChangeDto changeDto = coinChangeAlgorithm.coinChangeDP(2, coinsDto, false);
        assertEquals(8, changeDto.getTotalCoins());
        assertEquals(0, changeDto.get_1_cent());
        assertEquals(0, changeDto.get_5_cent());
        assertEquals(0, changeDto.get_10_cent());
        assertEquals(8, changeDto.get_25_cent());
    }


    @Test
    void oneFiveDollarBillTest() {
        CoinsDto coinsDto = new CoinsDto();
        coinsDto.set_1_cent(100);
        coinsDto.set_5_cent(100);
        coinsDto.set_10_cent(100);
        coinsDto.set_25_cent(100);

        ChangeDto changeDto = coinChangeAlgorithm.coinChangeDP(5, coinsDto, false);
        assertEquals(20, changeDto.getTotalCoins());
        assertEquals(0, changeDto.get_1_cent());
        assertEquals(0, changeDto.get_5_cent());
        assertEquals(0, changeDto.get_10_cent());
        assertEquals(20, changeDto.get_25_cent());
    }

    @Test
    void oneDollarBillNo25CentsTest() {
        CoinsDto coinsDto = new CoinsDto();
        coinsDto.set_1_cent(100);
        coinsDto.set_5_cent(100);
        coinsDto.set_10_cent(100);
        coinsDto.set_25_cent(0);

        ChangeDto changeDto = coinChangeAlgorithm.coinChangeDP(1, coinsDto, false);
        assertEquals(10, changeDto.getTotalCoins());
        assertEquals(0, changeDto.get_1_cent());
        assertEquals(0, changeDto.get_5_cent());
        assertEquals(10, changeDto.get_10_cent());
        assertEquals(0, changeDto.get_25_cent());
    }

    @Test
    void oneDollarBillJustOneCents() {
        CoinsDto coinsDto = new CoinsDto();
        coinsDto.set_1_cent(100);
        coinsDto.set_5_cent(0);
        coinsDto.set_10_cent(0);
        coinsDto.set_25_cent(0);

        ChangeDto changeDto = coinChangeAlgorithm.coinChangeDP(1, coinsDto, false);
        assertEquals(100, changeDto.getTotalCoins());
        assertEquals(100, changeDto.get_1_cent());
        assertEquals(0, changeDto.get_5_cent());
        assertEquals(0, changeDto.get_10_cent());
        assertEquals(0, changeDto.get_25_cent());
    }


    @Test
    void oneDollarBillJustFiveCents() {
        CoinsDto coinsDto = new CoinsDto();
        coinsDto.set_1_cent(0);
        coinsDto.set_5_cent(100);
        coinsDto.set_10_cent(0);
        coinsDto.set_25_cent(0);

        ChangeDto changeDto = coinChangeAlgorithm.coinChangeDP(1, coinsDto, false);
        assertEquals(20, changeDto.getTotalCoins());
        assertEquals(0, changeDto.get_1_cent());
        assertEquals(20, changeDto.get_5_cent());
        assertEquals(0, changeDto.get_10_cent());
        assertEquals(0, changeDto.get_25_cent());
    }


    @Test
    void oneDollarBillTestMaximizeCoins() {
        CoinsDto coinsDto = new CoinsDto();
        coinsDto.set_1_cent(100);
        coinsDto.set_5_cent(100);
        coinsDto.set_10_cent(100);
        coinsDto.set_25_cent(100);

        ChangeDto changeDto = coinChangeAlgorithm.coinChangeDP(1, coinsDto, true);
        assertEquals(100, changeDto.getTotalCoins());
        assertEquals(100, changeDto.get_1_cent());
        assertEquals(0, changeDto.get_5_cent());
        assertEquals(0, changeDto.get_10_cent());
        assertEquals(0, changeDto.get_25_cent());
    }

    @Test
    void twoDollarBillTestMaximizeCoinsFiveCentOnly() {
        CoinsDto coinsDto = new CoinsDto();
        coinsDto.set_1_cent(0);
        coinsDto.set_5_cent(100);
        coinsDto.set_10_cent(0);
        coinsDto.set_25_cent(0);

        ChangeDto changeDto = coinChangeAlgorithm.coinChangeDP(1, coinsDto, true);
        assertEquals(20, changeDto.getTotalCoins());
        assertEquals(0, changeDto.get_1_cent());
        assertEquals(20, changeDto.get_5_cent());
        assertEquals(0, changeDto.get_10_cent());
        assertEquals(0, changeDto.get_25_cent());
    }

    @Test
    void oneDollarBillTestMaximizeCoinsTenCentOnly() {
        CoinsDto coinsDto = new CoinsDto();
        coinsDto.set_1_cent(0);
        coinsDto.set_5_cent(0);
        coinsDto.set_10_cent(100);
        coinsDto.set_25_cent(100);

        ChangeDto changeDto = coinChangeAlgorithm.coinChangeDP(1, coinsDto, true);
        assertEquals(10, changeDto.getTotalCoins());
        assertEquals(0, changeDto.get_1_cent());
        assertEquals(0, changeDto.get_5_cent());
        assertEquals(10, changeDto.get_10_cent());
        assertEquals(0, changeDto.get_25_cent());
    }

    @Test
    void hundredDollarBillTestMaximize() {
        CoinsDto coinsDto = new CoinsDto();
        coinsDto.set_1_cent(1000);
        coinsDto.set_5_cent(1000);
        coinsDto.set_10_cent(1000);
        coinsDto.set_25_cent(1000);

        ChangeDto changeDto = coinChangeAlgorithm.coinChangeDP(100, coinsDto, true);
        assertEquals(2400, changeDto.getTotalCoins());
        assertEquals(1000, changeDto.get_1_cent());
        assertEquals(1000, changeDto.get_5_cent());
        assertEquals(400, changeDto.get_10_cent());
        assertEquals(0, changeDto.get_25_cent());
    }

    @Test
    void hundredDollarBillTest() {
        CoinsDto coinsDto = new CoinsDto();
        coinsDto.set_1_cent(1000);
        coinsDto.set_5_cent(1000);
        coinsDto.set_10_cent(1000);
        coinsDto.set_25_cent(1000);

        ChangeDto changeDto = coinChangeAlgorithm.coinChangeDP(100, coinsDto, false);
        assertEquals(400, changeDto.getTotalCoins());
        assertEquals(0, changeDto.get_1_cent());
        assertEquals(0, changeDto.get_5_cent());
        assertEquals(0, changeDto.get_10_cent());
        assertEquals(400, changeDto.get_25_cent());
    }

    @Test
    void noSolutionHundredDollarBillTestMaximize() {
        CoinsDto coinsDto = new CoinsDto();
        coinsDto.set_1_cent(0);
        coinsDto.set_5_cent(0);
        coinsDto.set_10_cent(0);
        coinsDto.set_25_cent(0);

        ChangeDto changeDto = coinChangeAlgorithm.coinChangeDP(100, coinsDto, false);
        assertEquals(0, changeDto.getTotalCoins());
        assertEquals(0, changeDto.get_1_cent());
        assertEquals(0, changeDto.get_5_cent());
        assertEquals(0, changeDto.get_10_cent());
        assertEquals(0, changeDto.get_25_cent());
    }

}