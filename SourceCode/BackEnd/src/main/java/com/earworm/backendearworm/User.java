package com.earworm.backendearworm;

import org.apache.tomcat.jni.Local;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import java.util.Objects;
//******************************************
import org.springframework.security.core.SpringSecurityCoreVersion;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import java.util.Set;
import java.time.LocalDate;
import java.time.Period;
import java.util.Collection;
import java.util.Collections;
//******************************************

@Table
@Entity
public class User implements UserDetails {
    @Id
    @SequenceGenerator(name = "user_sequence", sequenceName = "user_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
    private long id;
    private String displayName = " ";
    private String username = " ";
    private String email = " ";
    private String password = " ";
    private String bio = " ";
    private int zipCode = 0;
    private int phone = 0;
    private LocalDate dob;
    @Transient
    private int age;
    private UserRole userRole;
    // ******************************************
    private boolean enabled;
    private boolean credentialsNonExpired;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private Set<GrantedAuthority> authorities;
    // ******************************************

    // Class Constructer
    public User(String displayName, String username, String email, String password, LocalDate dob, int zipCode,
            int phone, UserRole userRole) {
        this.displayName = displayName;
        this.username = username;
        this.email = email;
        this.password = password;
        this.dob = dob;
        this.zipCode = zipCode;
        this.phone = phone;
        this.userRole = userRole;
    }

    // Default Constructer
    public User() {
    }

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }
    // @Override
    // public boolean equals(Object o){
    // if(this == o) return true;
    // if(!(o instanceof User)) return false
    // User user = (user) o;
    // return Objects.equals(this.username, user.username) && (this.email,
    // user.email) && (this.phone, user.phone) && (this.password, user.password);
    // }

    @Override
    public String toString() {
        return "User{" +
                "displayName='" + displayName + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", bio='" + bio + '\'' +
                ", age=" + getAge() +
                ", zipCode=" + zipCode +
                ", phone=" + phone +
                '}';
    }

    public boolean equals(Object object) {
        if (this == object)
            return true;
        if (object == null || getClass() != object.getClass())
            return false;
        if (!super.equals(object))
            return false;
        User user = (User) object;
        return dob == user.dob && zipCode == user.zipCode && phone == user.phone && displayName.equals(user.displayName)
                && username.equals(user.username) && email.equals(user.email) && password.equals(user.password);
    }

    public int hashCode() {
        return Objects.hash(super.hashCode(), displayName, username, email, password, bio, dob, zipCode, phone);
    }

    public long getID() {
        return id;
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
        return Period.between(dob, LocalDate.now()).getYears();
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

    public UserRole getUserRole() {
        return userRole;
    }

    // ****************************************
    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.credentialsNonExpired;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return this.authorities;
    }
    // ****************************************
}
