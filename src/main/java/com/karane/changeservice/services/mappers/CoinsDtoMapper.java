package com.karane.changeservice.services.mappers;

import com.karane.changeservice.repositories.entities.CoinsEntity;
import com.karane.changeservice.services.dtos.CoinsDto;
import org.springframework.stereotype.Component;

@Component
public class CoinsDtoMapper {

    public CoinsDto mapFromCoinsEntity(CoinsEntity coinsEntity) {
        CoinsDto coinsDto = new CoinsDto();
        coinsDto.set_1_cent(coinsEntity.get_1_cent());
        coinsDto.set_5_cent(coinsEntity.get_5_cent());
        coinsDto.set_10_cent(coinsEntity.get_10_cent());
        coinsDto.set_25_cent(coinsEntity.get_25_cent());
        return coinsDto;
    }
}
