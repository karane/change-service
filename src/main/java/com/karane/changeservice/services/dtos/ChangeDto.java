package com.karane.changeservice.services.dtos;

import java.util.StringJoiner;

public class ChangeDto {
    private int totalCoins;
    private int bill;
    private int oneCent;
    private int fiveCents;
    private int tenCents;
    private int twentyFiveCents;

    public ChangeDto() {
    }
    public ChangeDto(int totalCoins, int bill, int _1_cent, int _5_cent, int _10_cent, int _25_cent) {
        this.totalCoins = totalCoins;
        this.bill = bill;
        this.oneCent = _1_cent;
        this.fiveCents = _5_cent;
        this.tenCents = _10_cent;
        this.twentyFiveCents = _25_cent;
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
        return new StringJoiner(", ", ChangeDto.class.getSimpleName() + "[", "]")
                .add("totalCoins=" + totalCoins)
                .add("bill=" + bill)
                .add("oneCent=" + oneCent)
                .add("fiveCents=" + fiveCents)
                .add("tenCents=" + tenCents)
                .add("twentyFiveCents=" + twentyFiveCents)
                .toString();
    }
}
