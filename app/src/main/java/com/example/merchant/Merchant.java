package com.example.merchant;

public class Merchant {
    public String companyName;
    public String contactNo;
    public String email;
    public String profilePicture;

    public Merchant() {

    }

    public Merchant(String companyName, String contactNo, String email, String profilePicture) {
        this.companyName = companyName;
        this.contactNo = contactNo;
        this.email = email;
        this.profilePicture = profilePicture;
    }
}
