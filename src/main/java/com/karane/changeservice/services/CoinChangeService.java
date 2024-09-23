package com.karane.changeservice.services;

import com.karane.changeservice.algorithms.CoinChangeAlgorithm;
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

        ChangeDto changeDto = coinChangeAlgorithm.coinChangeDP(bill, coinsDto, maximizeCoins);
        changeDto.setBill(bill);


        if (changeDto.getTotalCoins() > 0) {
            updateCoinsRepository(coinsEntity, changeDto);
        }

        return changeDto;
    }

    private void updateCoinsRepository(CoinsEntity coinsEntity, ChangeDto changeDto) {

        coinsEntity.setOneCent(coinsEntity.getOneCent() - changeDto.getOneCent());
        coinsEntity.setFiveCents(coinsEntity.getFiveCents() - changeDto.getFiveCents());
        coinsEntity.setTenCents(coinsEntity.getTenCents() - changeDto.getTenCents());
        coinsEntity.setTwentyFiveCents(coinsEntity.getTwentyFiveCents() - changeDto.getTwentyFiveCents());
        coinsEntity.setTotalCoins(coinsEntity.getOneCent() + coinsEntity.getFiveCents() + coinsEntity.getTenCents() + coinsEntity.getTwentyFiveCents());

        coinsRepository.updateCoins(coinsEntity);
    }

}
