package com.karane.changeservice.services.dtos;

public class CoinsDto {
    private int _1_cent;
    private int _5_cent;
    private int _10_cent;
    private int _25_cent;
    private int totalCoins;

    private int bill;

    public int get_1_cent() {
        return _1_cent;
    }

    public void set_1_cent(int _1_cent) {
        this._1_cent = _1_cent;
    }

    public int get_5_cent() {
        return _5_cent;
    }

    public void set_5_cent(int _5_cent) {
        this._5_cent = _5_cent;
    }

    public int get_10_cent() {
        return _10_cent;
    }

    public void set_10_cent(int _10_cent) {
        this._10_cent = _10_cent;
    }

    public int get_25_cent() {
        return _25_cent;
    }

    public void set_25_cent(int _25_cent) {
        this._25_cent = _25_cent;
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
