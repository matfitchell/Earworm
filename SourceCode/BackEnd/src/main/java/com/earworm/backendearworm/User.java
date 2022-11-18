package com.earworm.backendearworm;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import javax.persistence.Entity;

@Entity
public class User implements UserDetails{

    private String displayName = " ";
    private String username = " ";
    private String email = " ";
    private String password = " ";
    private String bio = " ";
    private int age = 0;
    private int zipCode = 0;
    private int phone = 0;
    
    //Class Constructer
    public User(String displayName, String username, String email, String password, int age, int zipCode, int phone) {
        this.displayName = displayName;
        this.username = username;
        this.email = email;
        this.password = password;
        this.age = age;
        this.zipCode = zipCode;
        this.phone = phone;
    }

    //Default Constructer
    public User{
    }

    public User(String username, String email, String password){
        this.username = username;
        this.email = email;
        this.password = password;
    }
    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
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
}
