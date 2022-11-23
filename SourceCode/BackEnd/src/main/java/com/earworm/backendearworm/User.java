package com.earworm.backendearworm;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import javax.persistence.Entity;
import java.util.Objects;
//******************************************
import org.springframework.security.core.SpringSecurityCoreVersion;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import java.util.Set;
import java.util.Collection;
import java.util.Collections;
//******************************************


@Entity
@Table
public class User implements UserDetails{

    private final String displayName = " ";
    private final String username = " ";
    private final String email = " ";
    private final String password = " ";
    private String bio = " ";
    private final int age = 0;
    private final int zipCode = 0;
    private final int phone = 0;

    //******************************************
    private final boolean enabled;
    private final boolean credentialsNonExpired;
    private final boolean accountNonExpired;
	private final boolean accountNonLocked;
    private final Set<GrantedAuthority> authorities;
    //******************************************
    
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
    public User(){
    }

    public User(String username, String email, String password){
        this.username = username;
        this.email = email;
        this.password = password;
    }
//    @Override
//    public boolean equals(Object o){
//        if(this == o) return true;
//        if(!(o instanceof User)) return false
//        User user = (user) o;
//        return Objects.equals(this.username, user.username) && (this.email, user.email) && (this.phone, user.phone) && (this.password, user.password);
//    }

    @java.lang.Override
    public java.lang.String toString() {
        return "User{" +
                "displayName='" + displayName + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", bio='" + bio + '\'' +
                ", age=" + age +
                ", zipCode=" + zipCode +
                ", phone=" + phone +
                '}';
    }

    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        User user = (User) object;
        return age == user.age && zipCode == user.zipCode && phone == user.phone && displayName.equals(user.displayName) && username.equals(user.username) && email.equals(user.email) && password.equals(user.password);
    }

    public int hashCode() {
        return Objects.hash(super.hashCode(), displayName, username, email, password, bio, age, zipCode, phone);
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
