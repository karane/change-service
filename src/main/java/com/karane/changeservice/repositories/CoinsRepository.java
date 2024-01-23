package com.karane.changeservice.repositories;

import com.karane.changeservice.repositories.entities.CoinsEntity;
import com.karane.changeservice.services.dtos.CoinsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;

@Repository
public class CoinsRepository {

    private static final int DEFAULT_COINS_QUANTITY = 100;

    private CoinsEntity coins;

    @Autowired
    public CoinsRepository(Environment env) {
        coins = new CoinsEntity();
        coins.set_1_cent(env.getProperty("coins._1_cent", Integer.class, DEFAULT_COINS_QUANTITY));
        coins.set_5_cent(env.getProperty("coins._5_cent", Integer.class, DEFAULT_COINS_QUANTITY));
        coins.set_10_cent(env.getProperty("coins._10_cent", Integer.class, DEFAULT_COINS_QUANTITY));
        coins.set_25_cent(env.getProperty("coins._25_cent", Integer.class, DEFAULT_COINS_QUANTITY));

        coins.setTotalCoins(coins.get_1_cent() + coins.get_5_cent() + coins.get_10_cent() + coins.get_25_cent());

        System.out.println("Machine name: " + env.getProperty("MACHINE_NAME"));
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
        System.out.println(coins);
    }
}
