package com.karane.changeservice.controllers.mappers;

import com.karane.changeservice.controllers.responses.ChangeResponse;
import com.karane.changeservice.services.dtos.ChangeDto;
import org.springframework.stereotype.Component;

@Component
public class ChangeResponseMapper {

    public ChangeResponse toChangeResponse(ChangeDto changeDto) {

        ChangeResponse  changeResponse = new ChangeResponse();

        changeResponse.set_1_cent(changeDto.getOneCent());
        changeResponse.set_5_cent(changeDto.getFiveCents());
        changeResponse.set_10_cent(changeDto.getTenCents());
        changeResponse.set_25_cent(changeDto.getTwentyFiveCents());
        changeResponse.setTotalCoins(changeDto.getTotalCoins());

        return changeResponse;
    }
}
