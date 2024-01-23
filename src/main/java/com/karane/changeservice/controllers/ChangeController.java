package com.karane.changeservice.controllers;

import com.karane.changeservice.controllers.mappers.ChangeResponseMapper;
import com.karane.changeservice.controllers.responses.ChangeResponse;
import com.karane.changeservice.controllers.validators.BillValidator;
import com.karane.changeservice.services.CoinChangeService;
import com.karane.changeservice.services.dtos.ChangeDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

@RestController
@RequestMapping("/change")
public class ChangeController {
    static final private String rangeErrorMessage = "Bill must be one of these values: 1, 2, 5, 10, 20, 50, 100";

    @Autowired
    private CoinChangeService coinChangeService;

    @Autowired
    private ChangeResponseMapper changeResponseMapper;

    @Autowired
    private BillValidator billValidator;

    @GetMapping("/{bill}")
    @ResponseBody
    public ResponseEntity<?> getChange(@PathVariable int bill,
                                     @RequestParam(name = "max", defaultValue = "false") boolean maxCoins) {

        if (billValidator.isNotValid(bill)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(rangeErrorMessage);
        }

        ChangeDto changeDto = coinChangeService.coinChange(bill, maxCoins);
        ChangeResponse changeResponse = changeResponseMapper.toChangeResponse(changeDto);

        if (changeResponse.getTotalCoins() == 0){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No coins available for bill: " + bill);
        }

//        return new ChangeResponse(0, bill, 0, 0, 0, 0);
        return ResponseEntity.ok(changeResponse);
    }
}
