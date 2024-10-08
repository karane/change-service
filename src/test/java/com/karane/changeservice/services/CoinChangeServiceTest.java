package com.karane.changeservice.services;

import com.karane.changeservice.repositories.CoinsRepository;
import com.karane.changeservice.services.dtos.ChangeDto;
import com.karane.changeservice.services.mappers.CoinsDtoMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.env.Environment;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class CoinChangeServiceTest {

    @Mock
    private Environment environment;

    private CoinChangeService buildCoinChangeService(int one_cent, int five_cent, int ten_cent, int twenty_five_cent) {
        when(environment.getProperty(eq("COIN_ONE_CENT"), eq(Integer.class), any(Integer.class))).thenReturn(one_cent);
        when(environment.getProperty(eq("COIN_FIVE_CENTS"), eq(Integer.class), any(Integer.class))).thenReturn(five_cent);
        when(environment.getProperty(eq("COIN_TEN_CENTS"), eq(Integer.class), any(Integer.class))).thenReturn(ten_cent);
        when(environment.getProperty(eq("COIN_TWENTY_FIVE_CENTS"), eq(Integer.class), any(Integer.class))).thenReturn(twenty_five_cent);

        CoinsRepository coinsRepository = new CoinsRepository(environment);
        CoinsDtoMapper coinsDtoMapper = new CoinsDtoMapper();

        return new CoinChangeService(coinsRepository, coinsDtoMapper);
    }

    @Test
    void oneChangeTest() {
        CoinChangeService coinChangeService = buildCoinChangeService(100, 100, 100, 100);

        ChangeDto result = coinChangeService.coinChange(1, false);

        assertEquals(1, result.getBill());
        assertEquals(4, result.getTotalCoins());
        assertEquals(0, result.getOneCent());
        assertEquals(0, result.getFiveCents());
        assertEquals(0, result.getTenCents());
        assertEquals(4, result.getTwentyFiveCents());
    }

    @Test
    void oneChangeTestMaximizedCoins() {
        CoinChangeService coinChangeService = buildCoinChangeService(100, 100, 100, 100);

        ChangeDto result = coinChangeService.coinChange(1, true);

        assertEquals(1, result.getBill());
        assertEquals(100, result.getTotalCoins());
        assertEquals(100, result.getOneCent());
        assertEquals(0, result.getFiveCents());
        assertEquals(0, result.getTenCents());
        assertEquals(0, result.getTwentyFiveCents());
    }

    @Test
    void oneChangeTestZero() {
        CoinChangeService coinChangeService = buildCoinChangeService(0, 0, 0, 0);

        ChangeDto result = coinChangeService.coinChange(1, false);

        assertEquals(1, result.getBill());
        assertEquals(0, result.getTotalCoins());
        assertEquals(0, result.getOneCent());
        assertEquals(0, result.getFiveCents());
        assertEquals(0, result.getTenCents());
        assertEquals(0, result.getTwentyFiveCents());
    }

    @Test
    void oneChangeTestTwice() {
        CoinChangeService coinChangeService = buildCoinChangeService(100, 1, 1, 1 );

        ChangeDto result = coinChangeService.coinChange(1, true);

        assertEquals(1, result.getBill());
        assertEquals(100, result.getTotalCoins());
        assertEquals(100, result.getOneCent());
        assertEquals(0, result.getFiveCents());
        assertEquals(0, result.getTenCents());
        assertEquals(0, result.getTwentyFiveCents());

        result = coinChangeService.coinChange(1, true);

        assertEquals(1, result.getBill());
        assertEquals(0, result.getTotalCoins());
        assertEquals(0, result.getOneCent());
        assertEquals(0, result.getFiveCents());
        assertEquals(0, result.getTenCents());
        assertEquals(0, result.getTwentyFiveCents());

        result = coinChangeService.coinChange(1, true);

        assertEquals(1, result.getBill());
        assertEquals(0, result.getTotalCoins());
        assertEquals(0, result.getOneCent());
        assertEquals(0, result.getFiveCents());
        assertEquals(0, result.getTenCents());
        assertEquals(0, result.getTwentyFiveCents());
    }

    @Test
    void oneDollarChangeOneCoinEach() {
        CoinChangeService coinChangeService = buildCoinChangeService(1, 1, 1, 1);

        ChangeDto result = coinChangeService.coinChange(1, false);

        assertEquals(1, result.getBill());
        assertEquals(0, result.getTotalCoins());
        assertEquals(0, result.getOneCent());
        assertEquals(0, result.getFiveCents());
        assertEquals(0, result.getTenCents());
        assertEquals(0, result.getTwentyFiveCents());
    }

}