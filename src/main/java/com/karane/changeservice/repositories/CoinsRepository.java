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
        coins.set_1_cent(env.getProperty("COIN_ONE_CENT", Integer.class, DEFAULT_COINS_QUANTITY));
        coins.set_5_cent(env.getProperty("COIN_FIVE_CENTS", Integer.class, DEFAULT_COINS_QUANTITY));
        coins.set_10_cent(env.getProperty("COIN_TEN_CENTS", Integer.class, DEFAULT_COINS_QUANTITY));
        coins.set_25_cent(env.getProperty("COIN_TWENTY_FIVE_CENTS", Integer.class, DEFAULT_COINS_QUANTITY));

        coins.setTotalCoins(coins.get_1_cent() + coins.get_5_cent() + coins.get_10_cent() + coins.get_25_cent());

        logger.info("Started as:" + coins);
    }

    public CoinsEntity getCoins()
    {
        CoinsEntity coinsEntity = new CoinsEntity();
        coinsEntity.set_1_cent(coins.get_1_cent());
        coinsEntity.set_5_cent(coins.get_5_cent());
        coinsEntity.set_10_cent(coins.get_10_cent());
        coinsEntity.set_25_cent(coins.get_25_cent());
        coinsEntity.setTotalCoins(coins.getTotalCoins());

        return coinsEntity;
    }

    public void updateCoins(CoinsEntity coinsEntity) {
        coins.set_1_cent(coinsEntity.get_1_cent());
        coins.set_5_cent(coinsEntity.get_5_cent());
        coins.set_10_cent(coinsEntity.get_10_cent());
        coins.set_25_cent(coinsEntity.get_25_cent());

        coins.setTotalCoins(coins.get_1_cent() + coins.get_5_cent() + coins.get_10_cent() + coins.get_25_cent());

        logger.info("Updated to:" + coins);
    }
}
