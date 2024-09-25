package com.karane.changeservice.controllers.mappers;

import com.karane.changeservice.controllers.responses.ChangeResponse;
import com.karane.changeservice.services.dtos.ChangeDto;
import org.springframework.stereotype.Component;

@Component
public class ChangeResponseMapper {

    public ChangeResponse toChangeResponse(ChangeDto changeDto) {

        ChangeResponse  changeResponse = new ChangeResponse();

        changeResponse.setOneCent(changeDto.getOneCent());
        changeResponse.setFiveCents(changeDto.getFiveCents());
        changeResponse.setTenCents(changeDto.getTenCents());
        changeResponse.setTwentyFiveCents(changeDto.getTwentyFiveCents());
        changeResponse.setTotalCoins(changeDto.getTotalCoins());

        return changeResponse;
    }
}
