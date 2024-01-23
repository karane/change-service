package com.karane.changeservice.services;

import com.karane.changeservice.repositories.CoinsRepository;
import com.karane.changeservice.repositories.entities.CoinsEntity;
import com.karane.changeservice.services.dtos.ChangeDto;
import com.karane.changeservice.services.dtos.CoinsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.karane.changeservice.services.mappers.CoinsDtoMapper;

@Service
public class CoinChangeService {

    @Autowired
    private CoinsRepository coinsRepository;

    @Autowired
    private CoinsDtoMapper coinsDtoMapper;

    final private CoinChangeAlgorithm coinChangeAlgorithm = new CoinChangeAlgorithm();

    public CoinChangeService(CoinsRepository coinsRepository, CoinsDtoMapper coinsDtoMapper) {
        this.coinsRepository = coinsRepository;
        this.coinsDtoMapper = coinsDtoMapper;
    }

    public ChangeDto coinChange(int bill, boolean maximizeCoins) {
        //check persistence layer for coins
        CoinsEntity coinsEntity = coinsRepository.getCoins();
        CoinsDto coinsDto = coinsDtoMapper.mapFromCoinsEntity(coinsEntity);

        ChangeDto changeDto = coinChangeAlgorithm.coinChangeMemo(bill, coinsDto, maximizeCoins);
        changeDto.setBill(bill);


        if (changeDto.getTotalCoins() > 0) {
            updateCoinsRepository(coinsEntity, changeDto);
        }

        return changeDto;
    }

    private void updateCoinsRepository(CoinsEntity coinsEntity, ChangeDto changeDto) {


        coinsEntity.set_1_cent(coinsEntity.get_1_cent() - changeDto.get_1_cent());
        coinsEntity.set_5_cent(coinsEntity.get_5_cent() - changeDto.get_5_cent());
        coinsEntity.set_10_cent(coinsEntity.get_10_cent() - changeDto.get_10_cent());
        coinsEntity.set_25_cent(coinsEntity.get_25_cent() - changeDto.get_25_cent());
        coinsEntity.setTotalCoins(coinsEntity.get_1_cent() + coinsEntity.get_5_cent() + coinsEntity.get_10_cent() + coinsEntity.get_25_cent());

        coinsRepository.updateCoins(coinsEntity);
    }

}
