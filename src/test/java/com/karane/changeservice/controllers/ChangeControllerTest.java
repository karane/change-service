package com.karane.changeservice.controllers;

import com.karane.changeservice.controllers.mappers.ChangeResponseMapper;
import com.karane.changeservice.controllers.responses.ChangeResponse;
import com.karane.changeservice.controllers.validators.BillValidator;
import com.karane.changeservice.services.CoinChangeService;
import com.karane.changeservice.services.dtos.ChangeDto;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@WebMvcTest(ChangeController.class)
@AutoConfigureMockMvc
class ChangeControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CoinChangeService coinChangeService;

    @MockBean
    private ChangeResponseMapper changeResponseMapper;

    @MockBean
    private BillValidator billValidator;

    @Test
    public void testChangeEndpointWithNoCoins() throws Exception {

        when(coinChangeService.coinChange(eq(1), anyBoolean()))
                .thenReturn(new ChangeDto(0, 1, 0, 0, 0, 0));
        when(changeResponseMapper.toChangeResponse(any(ChangeDto.class)))
                .thenReturn(new ChangeResponse(0, 1, 0, 0, 0, 0));
        when(billValidator.isValid(anyInt())).thenReturn(true);

        int bill = 1;

        ChangeResponse expectedChange = new ChangeResponse(0,1, 0,0,0,0);
        String expectedString = expectedChange.toJsonString();

        mockMvc.perform(MockMvcRequestBuilders.get("/change/{bill}", bill)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().string(
                        Matchers.containsString("No coins available for bill: ")));
    }

    // Test success case
    @Test
    public void testChangeEndpoint() throws Exception {

        ChangeResponse expectedResponse = new ChangeResponse(100, 1, 100, 0, 0, 0);
        when(coinChangeService.coinChange(eq(1), anyBoolean()))
                .thenReturn(new ChangeDto(100, 1, 100, 0, 0, 0));
        when(changeResponseMapper.toChangeResponse(any(ChangeDto.class)))
                .thenReturn(expectedResponse);
        when(billValidator.isValid(anyInt())).thenReturn(true);


        int bill = 1;

        String expectedString = expectedResponse.toJsonString();

        mockMvc.perform(MockMvcRequestBuilders.get("/change/{bill}", bill)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(expectedString));
    }

    @Test
    public void testChangeEndpointInvalidInput() throws Exception {

        ChangeResponse expectedResponse = new ChangeResponse(100, 1, 100, 0, 0, 0);
        when(coinChangeService.coinChange(eq(1), anyBoolean()))
                .thenReturn(new ChangeDto(100, 1, 100, 0, 0, 0));
        when(changeResponseMapper.toChangeResponse(any(ChangeDto.class)))
                .thenReturn(expectedResponse);
        when(billValidator.isValid(anyInt())).thenReturn(false);


        int bill = 3;

        mockMvc.perform(MockMvcRequestBuilders.get("/change/{bill}", bill)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content()
                        .string("Bill must be one of these values: 1, 2, 5, 10, 20, 50, 100")
                );
    }

}