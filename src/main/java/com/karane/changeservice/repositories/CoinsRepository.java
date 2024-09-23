package com.karane.changeservice.repositories;

import com.karane.changeservice.repositories.entities.CoinsEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;

@Repository
public class CoinsRepository {
    private static final Logger logger = LoggerFactory.getLogger(CoinsRepository.class);

    private static final int DEFAULT_COINS_QUANTITY = 100;

    private CoinsEntity coins;

    @Autowired
    public CoinsRepository(Environment env) {
        coins = new CoinsEntity();
        coins.setOneCent(env.getProperty("COIN_ONE_CENT", Integer.class, DEFAULT_COINS_QUANTITY));
        coins.setFiveCents(env.getProperty("COIN_FIVE_CENTS", Integer.class, DEFAULT_COINS_QUANTITY));
        coins.setTenCents(env.getProperty("COIN_TEN_CENTS", Integer.class, DEFAULT_COINS_QUANTITY));
        coins.setTwentyFiveCents(env.getProperty("COIN_TWENTY_FIVE_CENTS", Integer.class, DEFAULT_COINS_QUANTITY));

        coins.setTotalCoins(coins.getOneCent() + coins.getFiveCents() + coins.getTenCents() + coins.getTwentyFiveCents());

        logger.info("Started as:" + coins);
    }

    public CoinsEntity getCoins()
    {
        CoinsEntity coinsEntity = new CoinsEntity();
        coinsEntity.setOneCent(coins.getOneCent());
        coinsEntity.setFiveCents(coins.getFiveCents());
        coinsEntity.setTenCents(coins.getTenCents());
        coinsEntity.setTwentyFiveCents(coins.getTwentyFiveCents());
        coinsEntity.setTotalCoins(coins.getTotalCoins());

        return coinsEntity;
    }

    public void updateCoins(CoinsEntity coinsEntity) {
        coins.setOneCent(coinsEntity.getOneCent());
        coins.setFiveCents(coinsEntity.getFiveCents());
        coins.setTenCents(coinsEntity.getTenCents());
        coins.setTwentyFiveCents(coinsEntity.getTwentyFiveCents());

        coins.setTotalCoins(coins.getOneCent() + coins.getFiveCents() + coins.getTenCents() + coins.getTwentyFiveCents());

        logger.info("Updated to:" + coins);
    }
}
