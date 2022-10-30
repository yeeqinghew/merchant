package com.example.merchant;

public class Merchant {
    public String companyName;
    public String contactNo;
    public String email;
    public String profilePicture;
    public String cardNumber;
    public String expiryDate;
    public String ccv;

    public Merchant() {

    }

    public Merchant(String companyName, String contactNo, String email, String profilePicture, String cardNumber, String expiryDate, String ccv) {
        this.companyName = companyName;
        this.contactNo = contactNo;
        this.email = email;
        this.profilePicture = profilePicture;
        this.cardNumber = cardNumber;
        this.expiryDate = expiryDate;
        this.ccv = ccv;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getContactNo() {
        return contactNo;
    }

    public String getEmail() {
        return email;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public String getCcv() {
        return ccv;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public void setCcv(String ccv) {
        this.ccv = ccv;
    }
}
