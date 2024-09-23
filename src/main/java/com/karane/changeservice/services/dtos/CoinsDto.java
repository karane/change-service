package com.karane.changeservice.services.dtos;

public class CoinsDto {
    private int oneCent;
    private int fiveCents;
    private int tenCents;
    private int twentyFiveCents;
    private int totalCoins;

    private int bill;

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
}
