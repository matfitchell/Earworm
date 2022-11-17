package com.earworm.backendearworm;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import javax.persistence.Entity;

@Entity
public class User implements UserDetails{

    private String displayName = " ", username = " ", email = " ", password = " ", bio = " ";
    private int age = 0, zipCode = 0, phone = 0;
    
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
    
}
