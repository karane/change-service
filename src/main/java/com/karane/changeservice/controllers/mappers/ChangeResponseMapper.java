package com.karane.changeservice.controllers.mappers;

import com.karane.changeservice.controllers.responses.ChangeResponse;
import com.karane.changeservice.services.dtos.ChangeDto;
import org.springframework.stereotype.Component;

@Component
public class ChangeResponseMapper {

    public ChangeResponse toChangeResponse(ChangeDto changeDto) {

        ChangeResponse  changeResponse = new ChangeResponse();

        changeResponse.set_1_cent(changeDto.get_1_cent());
        changeResponse.set_5_cent(changeDto.get_5_cent());
        changeResponse.set_10_cent(changeDto.get_10_cent());
        changeResponse.set_25_cent(changeDto.get_25_cent());
        changeResponse.setTotalCoins(changeDto.getTotalCoins());

        return changeResponse;
    }
}
