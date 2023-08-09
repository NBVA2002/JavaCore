package com.javabe.webshop.model;

public class FilterProduct {
    private int priceLT;
    private int priceGT;

    public int getPriceLT() {
        return priceLT;
    }

    public void setPriceLT(int priceLT) {
        this.priceLT = priceLT;
    }

    public int getPriceGT() {
        return priceGT;
    }

    public void setPriceGT(int priceGT) {
        this.priceGT = priceGT;
    }
}
