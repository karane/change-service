package com.karane.changeservice.algorithms;

import com.karane.changeservice.services.dtos.ChangeDto;
import com.karane.changeservice.services.dtos.CoinsDto;
import java.util.*;

public class CoinChangeAlgorithm {

    private static final int ONE_CENT_ITEM = 1;
    private static final int FIVE_CENTS_ITEM = 5;
    private static final int TEN_CENTS_ITEM = 10;
    private static final int TWENTY_FIVE_CENTS_ITEM = 25;
    private static final int MULTIPLIER_FACTOR = 100;

    private static final int ONE_CENT_INDEX = 0;
    private static final int FIVE_CENTS_INDEX = 1;
    private static final int TEN_CENTS_INDEX = 2;
    private static final int TWENTY_CENTS_INDEX = 3;


    private int [] buildCoinSupply(CoinsDto coinsDto) {
        int[] coinSupply = new int[4];
        Arrays.fill(coinSupply, 0);
        coinSupply[0] = coinsDto.getOneCent();
        coinSupply[1] = coinsDto.getFiveCents();
        coinSupply[2] = coinsDto.getTenCents();
        coinSupply[3] = coinsDto.getTwentyFiveCents();

        return coinSupply;
    }

    private ChangeDto countSelectedCoins(int[] selectedCoinsCounts) {
        ChangeDto changeDto = new ChangeDto();
        if (selectedCoinsCounts == null) {
            return changeDto;
        }

        changeDto.setOneCent(selectedCoinsCounts[ONE_CENT_INDEX]);
        changeDto.setFiveCents(selectedCoinsCounts[FIVE_CENTS_INDEX]);
        changeDto.setTenCents(selectedCoinsCounts[TEN_CENTS_INDEX]);
        changeDto.setTwentyFiveCents(selectedCoinsCounts[TWENTY_CENTS_INDEX]);

        changeDto.setTotalCoins(
                changeDto.getOneCent()
                + changeDto.getFiveCents()
                + changeDto.getTenCents()
                + changeDto.getTwentyFiveCents()
        );

        return changeDto;
    }

    public ChangeDto coinChangeDP(int amount, CoinsDto coinsDto, boolean maximizeCoins) {
        return coinChangeDP(amount, coinsDto, maximizeCoins, MULTIPLIER_FACTOR);
    }

    public ChangeDto coinChangeDP(int amount, CoinsDto coinsDto, boolean maximizeCoins, int factor) {
        int[] coins = {ONE_CENT_ITEM, FIVE_CENTS_ITEM, TEN_CENTS_ITEM, TWENTY_FIVE_CENTS_ITEM};
        int[] coinSupply = buildCoinSupply(coinsDto);

        int convertedAmount = amount * factor;

        int [] selectedCoinsCounts;
        if (maximizeCoins) {
            selectedCoinsCounts = maxCoinsDP(coins, coinSupply, convertedAmount);
        } else {
            selectedCoinsCounts = minCoinsDP(coins, coinSupply, convertedAmount);
        }

        return countSelectedCoins(selectedCoinsCounts);
    }

    private int[] minCoinsDP(int [] coins, int[] coinCounts, int amount) {
        int[] dp = new int[amount + 1];
        int[][] selectedCoins = new int[amount + 1][coins.length]; // Track selected coins
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for (int w = 1; w <= amount; w++) {
            for (int j = 0; j < coins.length; j++) {
                int coin = coins[j];
                int coinCount = coinCounts[j];

                for (int k = 1; k <= coinCount && coin * k <= w; k++) {
                    if (dp[w - coin * k] != Integer.MAX_VALUE
                            && selectedCoins[w - coin * k][j] + k <= coinCount
                            && dp[w - coin * k] + k < dp[w]) {
                        dp[w] = dp[w - coin * k] + k;
                        System.arraycopy(selectedCoins[w - coin * k], 0, selectedCoins[w], 0, coins.length);
                        selectedCoins[w][j] += k;
                    }
                }
            }
        }

        if (dp[amount] == Integer.MAX_VALUE) {
            return null;
        } else {
            return selectedCoins[amount];
        }
    }

    private int [] maxCoinsDP(int [] coins, int[] coinCounts, int amount) {
        int[] dp = new int[amount + 1];
        int[][] selectedCoins = new int[amount + 1][coins.length]; // Track selected coins
        Arrays.fill(dp, -1);
        dp[0] = 0;

        for (int w = 1; w <= amount; w++) {
            for (int coinIndex = 0; coinIndex < coins.length; coinIndex++) {
                int coin = coins[coinIndex];
                int coinCount = coinCounts[coinIndex];

                for (int k = 1; k <= coinCount && coin * k <= w; k++) {
                    if (dp[w - coin * k] != -1
                            && selectedCoins[w - coin * k][coinIndex] + k <= coinCount
                            && dp[w - coin * k] + k > dp[w]) {
                        dp[w] = dp[w - coin * k] + k;
                        System.arraycopy(selectedCoins[w - coin * k], 0, selectedCoins[w], 0, coins.length);
                        selectedCoins[w][coinIndex] += k;
                    }
                }
            }
        }

        if (dp[amount] == -1) {
            return null;
        } else {
            return selectedCoins[amount];
        }
    }
}

