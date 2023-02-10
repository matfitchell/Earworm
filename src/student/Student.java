

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
//import java.time.LocalDate;
//import java.time.Period;
import java.util.Objects;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

// import org.springframework.security.core.GrantedAuthority;
// import org.springframework.security.core.authority.SimpleGrantedAuthority;
// import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
//import jakarta.persistence.Transient;

@Table(name = "student")
@Entity
public class Student implements UserDetails {
    @Id
    // @SequenceGenerator(name = "student_sequence", sequenceName =
    // "student_sequence", allocationSize = 1)
    @GeneratedValue /* (strategy = GenerationType.SEQUENCE, generator = "student_sequence") */
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
    // private LocalDate dob;
    private Integer age;
    @Enumerated(EnumType.STRING)
    private StudentRole studentRole = StudentRole.USER;
    private String musicTaste;
    private Boolean locked;
    private Boolean enabled;

    // Class Constructer
    public Student(int ID, String userName, String password, String email, String firstName, String lastName,
            String musicTaste, String gender, int Distance, int phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = userName;
        this.email = email;
        this.password = password;
        // this.dob = dob;
        this.gender = gender;
        this.phone = phoneNumber;
    }

    // Default Constructer
    public Student() {
    }

    public Student(String username, String email, Integer age, String password, int phone, StudentRole studentRole) {
        this.username = username;
        this.email = email;
        this.age = age;
        // this.dob = dob;
        this.phone = phone;
        this.password = password;
        this.studentRole = studentRole;
    }
    // @Override
    // public boolean equals(Object o){
    // if(this == o) return true;
    // if(!(o instanceof User)) return false
    // User user = (user) o;
    // return Objects.equals(this.username, user.username) && (this.email,
    // user.email) && (this.phone, user.phone) && (this.password, user.password);
    // }

    // @Override
    // public String toString() {
    // return "User{" +
    // "firstName='" + firstName + '\'' +
    // ", lastName='" + lastName + '\'' +
    // ", username='" + username + '\'' +
    // ", email='" + email + '\'' +
    // ", bio='" + bio + '\'' +
    // ", age=" + getAge() +
    // ", longitude=" + longitude +
    // ", latitude=" + latitude +
    // ", phone=" + phone +
    // '}';
    // }

    public boolean equals(Object object) {
        if (this == object)
            return true;
        if (object == null || getClass() != object.getClass())
            return false;
        if (!super.equals(object))
            return false;
        Student user = (Student) object;
        return latitude == user.latitude && longitude == user.longitude && phone == user.phone
                && lastName.equals(user.lastName)
                && username.equals(user.username) && email.equals(user.email) && password.equals(user.password);
    }

    // public void setUserRole(UserRole userRole) {
    // this.userRole = userRole;
    // }

    public String getMusicTaste() {
        return musicTaste;
    }

    public void setMusicTaste(String musicTaste) {
        this.musicTaste = musicTaste;
    }

    public int hashCode() {
        return Objects.hash(super.hashCode(), firstName, lastName, username, email, password, latitude,
                longitude, phone);
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

    public String getGender() {
        return gender;
    }

    // public void setGender() {
    // this.gender = gender;
    // }

    // public Integer getAge() {
    // return Period.between(this.dob, LocalDate.now()).getYears();
    // }

    // public void setDob(LocalDate dob) {
    // this.dob = dob;
    // }

    // public LocalDate getDob() {
    // return dob;
    // }

    public BigDecimal getlatitude() {
        return latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
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

    public void setAge(Integer age) {
        this.age = age;
    }

    // @Override
    // public Collection<? extends GrantedAuthority> getAuthorities() {
    // // TODO Auto-generated method stub
    // SimpleGrantedAuthority authority = new
    // SimpleGrantedAuthority(studentRole.name());
    // return Collections.singletonList(authority);
    // }

    // @Override
    // public boolean isAccountNonExpired() {
    // // TODO Auto-generated method stub
    // return true;
    // }

    // @Override
    // public boolean isAccountNonLocked() {
    // // TODO Auto-generated method stub
    // return !locked;
    // }

    // @Override
    // public boolean isCredentialsNonExpired() {
    // // TODO Auto-generated method stub
    // return true;
    // }

    // @Override
    // public boolean isEnabled() {
    // // TODO Auto-generated method stub
    // return enabled;
    // }

    public StudentRole getStudentRole() {
        return studentRole;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(studentRole.name()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isEnabled() {
        // TODO Auto-generated method stub
        return true;
    }
}
