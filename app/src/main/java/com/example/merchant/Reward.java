package com.example.merchant;

public class Reward {
    public String description;
    public String expirydate;
    public String mImageUrl;
    public String point;
    public String qrUrl;
    public String title;
    public String tou;

    public Reward() {

    }

    public Reward(String description, String expirydate, String mImageUrl, String point, String qrUrl, String title, String tou) {
        this.description = description;
        this.expirydate = expirydate;
        this.mImageUrl = mImageUrl;
        this.point = point;
        this.qrUrl = qrUrl;
        this.title = title;
        this.tou = tou;
    }

    public String getDescription() {
        return description;
    }

    public String getExpirydate() {
        return expirydate;
    }

    public String getmImageUrl() {
        return mImageUrl;
    }

    public String getPoint() {
        return point;
    }

    public String getQrUrl() {
        return qrUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getTou() {
        return tou;
    }
}
