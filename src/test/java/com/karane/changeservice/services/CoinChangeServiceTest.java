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
        when(environment.getProperty(eq("coins._1_cent"), eq(Integer.class), any(Integer.class))).thenReturn(one_cent);
        when(environment.getProperty(eq("coins._5_cent"), eq(Integer.class), any(Integer.class))).thenReturn(five_cent);
        when(environment.getProperty(eq("coins._10_cent"), eq(Integer.class), any(Integer.class))).thenReturn(ten_cent);
        when(environment.getProperty(eq("coins._25_cent"), eq(Integer.class), any(Integer.class))).thenReturn(twenty_five_cent);

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
        assertEquals(0, result.get_1_cent());
        assertEquals(0, result.get_5_cent());
        assertEquals(0, result.get_10_cent());
        assertEquals(4, result.get_25_cent());
    }

    @Test
    void oneChangeTestMaximizedCoins() {
        CoinChangeService coinChangeService = buildCoinChangeService(100, 100, 100, 100);

        ChangeDto result = coinChangeService.coinChange(1, true);

        assertEquals(1, result.getBill());
        assertEquals(100, result.getTotalCoins());
        assertEquals(100, result.get_1_cent());
        assertEquals(0, result.get_5_cent());
        assertEquals(0, result.get_10_cent());
        assertEquals(0, result.get_25_cent());
    }

    @Test
    void oneChangeTestZero() {
        CoinChangeService coinChangeService = buildCoinChangeService(0, 0, 0, 0);

        ChangeDto result = coinChangeService.coinChange(1, false);

        assertEquals(1, result.getBill());
        assertEquals(0, result.getTotalCoins());
        assertEquals(0, result.get_1_cent());
        assertEquals(0, result.get_5_cent());
        assertEquals(0, result.get_10_cent());
        assertEquals(0, result.get_25_cent());
    }

    @Test
    void oneChangeTestTwice() {
        CoinChangeService coinChangeService = buildCoinChangeService(100, 1, 1, 1 );

        ChangeDto result = coinChangeService.coinChange(1, true);

        assertEquals(1, result.getBill());
        assertEquals(100, result.getTotalCoins());
        assertEquals(100, result.get_1_cent());
        assertEquals(0, result.get_5_cent());
        assertEquals(0, result.get_10_cent());
        assertEquals(0, result.get_25_cent());

        result = coinChangeService.coinChange(1, true);

        assertEquals(1, result.getBill());
        assertEquals(0, result.getTotalCoins());
        assertEquals(0, result.get_1_cent());
        assertEquals(0, result.get_5_cent());
        assertEquals(0, result.get_10_cent());
        assertEquals(0, result.get_25_cent());
    }

}