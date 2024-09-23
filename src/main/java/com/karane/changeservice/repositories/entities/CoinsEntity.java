package com.karane.changeservice.repositories.entities;

import java.util.StringJoiner;

public class CoinsEntity {
    private int totalCoins;
    private int oneCent;
    private int fiveCents;
    private int tenCents;
    private int twentyFiveCents;


    public int getTotalCoins() {
        return totalCoins;
    }

    public void setTotalCoins(int totalCoins) {
        this.totalCoins = totalCoins;
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
        return new StringJoiner(", ", CoinsEntity.class.getSimpleName() + "[", "]")
                .add("totalCoins=" + totalCoins)
                .add("oneCent=" + oneCent)
                .add("fiveCents=" + fiveCents)
                .add("tenCents=" + tenCents)
                .add("twentyFiveCents=" + twentyFiveCents)
                .toString();
    }
}
