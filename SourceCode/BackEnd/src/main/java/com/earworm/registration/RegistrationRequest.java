package com.earworm.registration;

import java.time.LocalDate;

public class RegistrationRequest {
    private String displayName;
    private String email;
    private String password;
    private String username;
    private String bio;
    private int zipCode;
    private int phone;
    private LocalDate dob;

    // private String displayName = " ";
    // private String username = " ";
    // private String email = " ";
    // private String password = " ";
    // private String bio = " ";
    // private int zipCode = 0;
    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    @Override
    public String toString() {
        return "RegistrationRequest [displayName=" + displayName + ", email=" + email + ", phone="
                + phone + ", username=" + username + ", zipCode=" + zipCode + ", bio= " + bio + "]";
    }

}
