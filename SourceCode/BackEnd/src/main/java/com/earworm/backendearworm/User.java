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
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.Collection;

//******************************************

@Table
@Entity
public class User implements UserDetails {
    @Id
    @SequenceGenerator(name = "user_sequence", sequenceName = "user_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
    private long id;
    private String username = " ";
    private String password = " ";
    private String email = " ";
    private String firstName = " ";
    private String lastName = " ";
    private String gender = " ";
    private String bio = " ";
    private BigDecimal latitude = null;
    private BigDecimal longitude = null;
    private int phone = 0;
    private int dob;
    @Transient
    private int age;
    private UserRole userRole;
    private String musicTaste;
    // ******************************************
    private boolean enabled;
    private boolean credentialsNonExpired;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private Set<GrantedAuthority> authorities;
    // ******************************************

    // Class Constructer
    public User(int ID , String userName, String password, String email, String firstName, String lastName, String musicTaste, int  DOB ,String gender, int Distance, int phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = userName;
        this.email = email;
        this.password = password;
        this.dob = DOB;
        this.gender = gender; 
        this.phone = phoneNumber;
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
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", bio='" + bio + '\'' +
                ", age=" + getAge() +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
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
        return dob == user.dob && latitude == user.latitude && longitude == user.longitude && phone == user.phone && lastName.equals(user.lastName)
                && username.equals(user.username) && email.equals(user.email) && password.equals(user.password);
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public String getMusicTaste() {
        return musicTaste;
    }

    public void setMusicTaste(String musicTaste) {
        this.musicTaste = musicTaste;
    }

    public int hashCode() {
        return Objects.hash(super.hashCode(), firstName, lastName, username, email, password, bio, dob, latitude, longitude, phone);
    }

    public long getID() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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
    
    public String getGender(){
        return gender;
    }

    public void setGender(){
        this.gender = gender;
    }

    public int getAge() {
        return Period.between(dob, LocalDate.now()).getYears();
    }

    public BigDecimal getlatitude() {
        return latitude;
    }

    public BigDecimal getLongitude(){
        return longitude;
    }

    public void setLongitude(BigDecimal longitude){
        this.longitude = longitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
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
