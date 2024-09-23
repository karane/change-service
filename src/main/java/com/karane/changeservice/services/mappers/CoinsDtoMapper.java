package com.karane.changeservice.services.mappers;

import com.karane.changeservice.repositories.entities.CoinsEntity;
import com.karane.changeservice.services.dtos.CoinsDto;
import org.springframework.stereotype.Component;

@Component
public class CoinsDtoMapper {

    public CoinsDto mapFromCoinsEntity(CoinsEntity coinsEntity) {
        CoinsDto coinsDto = new CoinsDto();
        coinsDto.setOneCent(coinsEntity.getOneCent());
        coinsDto.setFiveCents(coinsEntity.getFiveCents());
        coinsDto.setTenCents(coinsEntity.getTenCents());
        coinsDto.setTwentyFiveCents(coinsEntity.getTwentyFiveCents());
        return coinsDto;
    }
}
