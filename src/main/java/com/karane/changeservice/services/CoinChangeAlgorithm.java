package com.karane.changeservice.services;

import com.karane.changeservice.services.dtos.ChangeDto;
import com.karane.changeservice.services.dtos.CoinsDto;

import java.util.*;

public class CoinChangeAlgorithm {

    private static final int ONE_CENT_ITEM = 1;
    public static final int FIVE_CENTS_ITEM = 5;
    public static final int TEN_CENTS_ITEM = 10;
    public static final int TWENTY_FIVE_CENTS_ITEM = 25;
    public static final int MULTIPLIER_FACTOR = 100;


    private int [] buildCoinSupply(CoinsDto coinsDto) {
        int[] coinSupply = new int[4];
        Arrays.fill(coinSupply, 0);
        coinSupply[0] = coinsDto.get_1_cent();
        coinSupply[1] = coinsDto.get_5_cent();
        coinSupply[2] = coinsDto.get_10_cent();
        coinSupply[3] = coinsDto.get_25_cent();

        return coinSupply;
    }

    private ChangeDto countSelectedCoins(List<Integer> selectedCoins) {
        ChangeDto changeDto = new ChangeDto();

        for(int coin: selectedCoins) {
            if(coin == ONE_CENT_ITEM) {
                changeDto.set_1_cent(changeDto.get_1_cent() + 1);
            } else if(coin == FIVE_CENTS_ITEM) {
                changeDto.set_5_cent(changeDto.get_5_cent() + 1);
            } else if(coin == TEN_CENTS_ITEM) {
                changeDto.set_10_cent(changeDto.get_10_cent() + 1);
            } else if(coin == TWENTY_FIVE_CENTS_ITEM) {
                changeDto.set_25_cent(changeDto.get_25_cent() + 1);
            }
        }

        return changeDto;
    }

    class CoinsResult {
        public int coinsQuantity;
        public List<Integer> selectedCoins;
        public int[] coinSupply;

        public CoinsResult(int coinsQuantity, List<Integer> selectedCoins, int[] coinSupply) {
            this.coinsQuantity = coinsQuantity;
            this.selectedCoins = selectedCoins;
            this.coinSupply = coinSupply;
        }

        public int getSumCoins() {
            return selectedCoins.stream().mapToInt(Integer::intValue).sum();
        }
    }

    Hashtable<Integer, CoinsResult> memo;

    public ChangeDto coinChangeMemo(int amount, CoinsDto coinsDto, boolean maximizeCoins) {
        return coinChangeMemo(amount, coinsDto, maximizeCoins, MULTIPLIER_FACTOR);
    }
    public ChangeDto coinChangeMemo(int amount, CoinsDto coinsDto, boolean maximizeCoins, int factor) {
        int [] coins = {ONE_CENT_ITEM, FIVE_CENTS_ITEM, TEN_CENTS_ITEM, TWENTY_FIVE_CENTS_ITEM};
        int[] coinSupply = buildCoinSupply(coinsDto);

        int convertedAmount = amount * factor;

        memo = new Hashtable<>();
        CoinsResult changeCoinsResult;
        if (maximizeCoins) {
            changeCoinsResult = maxCoinsMemo(coins, coinSupply, convertedAmount);
        } else {
            changeCoinsResult = minCoinsMemo(coins, coinSupply, convertedAmount);
        }

        ChangeDto changeDto = countSelectedCoins(changeCoinsResult.selectedCoins);
        changeDto.setTotalCoins(changeCoinsResult.coinsQuantity);

        return changeDto;
    }
    private CoinsResult minCoinsMemo(int [] coins, int[] coinSupply, int amount)
    {
        // base case
        if (amount == 0) {
            int [] coinSupplyCopy = Arrays.copyOf(coinSupply, coinSupply.length);
            return new CoinsResult(0, new ArrayList<Integer>(), coinSupplyCopy);
        }

        if (memo.containsKey(amount)) {
            return memo.get(amount);
        }

        // Initialize result
        int res = Integer.MAX_VALUE;

        // Try every coin that has smaller value than V
        List<Integer> selectedCoins = new ArrayList<Integer>();
        int [] minCoinSupply = Arrays.copyOf(coinSupply, coinSupply.length);
        for (int i=0; i< coins.length; i++)
        {
            if (coins[i] <= amount)
            {
                int[] coinSupplyCopy = Arrays.copyOf(minCoinSupply, minCoinSupply.length);
                CoinsResult subRes = minCoinsMemo(coins, coinSupplyCopy, amount - coins[i]);

                if (subRes.coinsQuantity != Integer.MAX_VALUE
                        && (subRes.coinSupply[i] > 0)
                        && subRes.coinsQuantity < res) {
                    res = subRes.coinsQuantity + 1;

                    selectedCoins = new ArrayList<>(subRes.selectedCoins);
                    selectedCoins.add(coins[i]);

                    minCoinSupply = Arrays.copyOf(subRes.coinSupply, subRes.coinSupply.length);
                    minCoinSupply[i]--;
                }
            }
        }

        CoinsResult coinsResult = new CoinsResult(res, selectedCoins, minCoinSupply);

        if (coinsResult.getSumCoins() != amount) {
            int[] coinSupplyCopy = Arrays.copyOf(minCoinSupply, minCoinSupply.length);
            coinsResult = new CoinsResult(0, new ArrayList<Integer>(), coinSupplyCopy);
        }

        memo.put(amount, coinsResult);
        return coinsResult;
    }

    private CoinsResult maxCoinsMemo(int [] coins, int [] coinSupply, int amount)
    {
        // base case
        if (amount == 0) {
            int[] coinSupplyCopy = Arrays.copyOf(coinSupply, coinSupply.length);
            return new CoinsResult(0, new ArrayList<Integer>(), coinSupplyCopy);
        }

        if (memo.containsKey(amount)) {
            return memo.get(amount);
        }

        // Initialize result
        int res = 0;

        // Try every coin that has smaller value than V
        List<Integer> selectedCoins = new ArrayList<Integer>();
        int [] maxCoinSupply = Arrays.copyOf(coinSupply, coinSupply.length);
        for (int i=0; i<coins.length; i++)
        {
            if (coins[i] <= amount)
            {
                int[] coinSupplyCopy = Arrays.copyOf(maxCoinSupply, maxCoinSupply.length);
                CoinsResult subRes = maxCoinsMemo(coins, coinSupplyCopy, amount - coins[i]);

                if (subRes.coinsQuantity != Integer.MAX_VALUE
                        && (subRes.coinSupply[i] > 0)
                        && subRes.coinsQuantity + 1 > res) {
                    res = subRes.coinsQuantity + 1;

                    selectedCoins = new ArrayList<>(subRes.selectedCoins);
                    selectedCoins.add(coins[i]);

                    maxCoinSupply = Arrays.copyOf(subRes.coinSupply, subRes.coinSupply.length);
                    maxCoinSupply[i]--;
                }
            }
        }

        CoinsResult coinsResult = new CoinsResult(res, selectedCoins, maxCoinSupply);
        if (coinsResult.getSumCoins() != amount) {
            int[] coinSupplyCopy = Arrays.copyOf(maxCoinSupply, maxCoinSupply.length);
            coinsResult = new CoinsResult(0, new ArrayList<Integer>(), coinSupplyCopy);

        }

        memo.put(amount, coinsResult);
        return coinsResult;
    }

}

