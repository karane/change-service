package com.karane.changeservice.controllers.responses;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.StringJoiner;

public class ChangeResponse {
    private int totalCoins;
    private int bill;
    private int oneCent;
    private int fiveCents;
    private int tenCents;
    private int twentyFiveCents;

    public ChangeResponse() {
    }

    public ChangeResponse(int totalCoins, int bill, int oneCent, int fiveCents, int tenCents, int twentyFiveCents) {
        this.totalCoins = totalCoins;
        this.bill = bill;
        this.oneCent = oneCent;
        this.fiveCents = fiveCents;
        this.tenCents = tenCents;
        this.twentyFiveCents = twentyFiveCents;
    }

    public int getTotalCoins() {
        return totalCoins;
    }

    public void setTotalCoins(int totalCoins) {
        this.totalCoins = totalCoins;
    }

    public int getBill() {
        return bill;
    }

    public void setBill(int bill) {
        this.bill = bill;
    }

    public int getOneCent() {
        return oneCent;
    }

    public void setOneCent(int oneCent) {
        this.oneCent = oneCent;
    }

    public int getFiveCents() {
        return fiveCents;
    }

    public void setFiveCents(int fiveCents) {
        this.fiveCents = fiveCents;
    }

    public int getTenCents() {
        return tenCents;
    }

    public void setTenCents(int tenCents) {
        this.tenCents = tenCents;
    }

    public int getTwentyFiveCents() {
        return twentyFiveCents;
    }

    public void setTwentyFiveCents(int twentyFiveCents) {
        this.twentyFiveCents = twentyFiveCents;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ChangeResponse.class.getSimpleName() + "[", "]")
                .add("totalCoins=" + totalCoins)
                .add("bill=" + bill)
                .add("oneCent=" + oneCent)
                .add("fiveCents=" + fiveCents)
                .add("tenCents=" + tenCents)
                .add("twentyFiveCents=" + twentyFiveCents)
                .toString();
    }

    public String toJsonString() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
