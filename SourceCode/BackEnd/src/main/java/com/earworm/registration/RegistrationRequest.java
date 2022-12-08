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

    public RegistrationRequest(String displayName, String email, String password, String username, String bio,
            int zipCode, int phone, LocalDate dob) {
        this.displayName = displayName;
        this.email = email;
        this.password = password;
        this.username = username;
        this.bio = bio;
        this.zipCode = zipCode;
        this.phone = phone;
        this.dob = dob;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((displayName == null) ? 0 : displayName.hashCode());
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((password == null) ? 0 : password.hashCode());
        result = prime * result + ((username == null) ? 0 : username.hashCode());
        result = prime * result + ((bio == null) ? 0 : bio.hashCode());
        result = prime * result + zipCode;
        result = prime * result + phone;
        result = prime * result + ((dob == null) ? 0 : dob.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        RegistrationRequest other = (RegistrationRequest) obj;
        if (displayName == null) {
            if (other.displayName != null)
                return false;
        } else if (!displayName.equals(other.displayName))
            return false;
        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.email))
            return false;
        if (password == null) {
            if (other.password != null)
                return false;
        } else if (!password.equals(other.password))
            return false;
        if (username == null) {
            if (other.username != null)
                return false;
        } else if (!username.equals(other.username))
            return false;
        if (bio == null) {
            if (other.bio != null)
                return false;
        } else if (!bio.equals(other.bio))
            return false;
        if (zipCode != other.zipCode)
            return false;
        if (phone != other.phone)
            return false;
        if (dob == null) {
            if (other.dob != null)
                return false;
        } else if (!dob.equals(other.dob))
            return false;
        return true;
    }

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
