
package com.earworm.backendearworm;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class Driver {

   
    public static void main(String... args) {
        List<User> Users = readUsersFromCSV("C:\\Users\\alopezromero\\Dev\\Earworm\\images\\dummydata.csv");

        // let's print all the person read from CSV file
        for (User b : Users) {
            
            // console display for testing only 
            System.out.println("UserID: " + b.getID());
            System.out.println("Usename: " + b.getUsername());
            System.out.println("Name: " + b.getLastName() + ", " + b.getFirstName());
            System.out.println("Music Tase" + b.getMusicTaste());
            System.out.println("Age: " + b.getAge());
            System.out.println("Preferred Distance: " + b.getDistance());
            System.out.println();
        }
    }

    private static List<User> readUsersFromCSV(String fileName) {
        List<User> Users = new ArrayList<>();
        Path pathToFile = Paths.get(fileName);

        // create an instance of BufferedReader
        // using try with resource, Java 7 feature to close resources
        try (BufferedReader br = Files.newBufferedReader(pathToFile,
                StandardCharsets.US_ASCII)) {

            // read the first line from the text file
            String line = br.readLine();

            // loop until all lines are read
            while (line != null) {

                // use string.split to load a string array with the values from
                // each line of
                // the file, using a comma as the delimiter
                String[] attributes = line.split(",");

                //System.out.println(attributes);

                User user = createUser(attributes);

                // adding user into ArrayList
                Users.add(user);

                // read next line before looping
                // if end of file reached, line would be null
                line = br.readLine();
            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return Users;
    }

    private static User createUser(String[] metadata) {
        
        int ID = Integer.parseInt(metadata[0]);
        //String userID = metadata[1];
        String userName = metadata[2];
        String password = metadata[3];
        String email = metadata[4];
        String firstName = metadata[5];
        String lastName = metadata[6];
        String musicTaste = metadata[7];
        //int DOB = Integer.parseInt(metadata[8]);
        String gender = metadata[9];
        int Distance = Integer.parseInt(metadata[10]);
        String phoneNumber = metadata[11];

        int age = 21;

        // create and return book of this metadata
        return new User(ID , userName, password, email, firstName, lastName, musicTaste, gender,  Distance, phoneNumber, age);
    }

}

class User{

    private long ID;
    //private String userID;
    private String username = " ";
    private String password = " ";
    private String email = " ";
    private String musicTaste = "";
    private String firstName = " ";
    private String lastName = " ";
    private String gender = " ";
    private String bio = " ";
    private String phone = "0";
    //private int DOB;
    private int age;
    private int Distance;
    
    //public User(int ID , String userName, String password, String email, String firstName, String lastName, String musicTaste, 
      //  int  DOB ,String gender, int Distance, int phoneNumber, int age) {
    public User(int ID , String userName, String password, String email, String firstName, String lastName, String musicTaste 
            ,String gender, int Distance, String phoneNumber, int age) {
        this.ID = ID;        
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = userName;
        this.email = email;
        this.password = password;
        //this.DOB = DOB;
        this.gender = gender; 
        this.phone = phoneNumber;
        this.musicTaste = musicTaste;
        this.age = age;
        this.Distance = Distance;
    }
     // default constructor
    public User() {
    }

    public int getAge(){
        return age;
    }

    public void setAge(int age){
        this.age = age;
    }
    /* 
    public int getDOB(){
        return DOB;
    }
    public void setDOB(int DOB){
        this.DOB = DOB;
    }
    */
    public String getMusicTaste() {
        return musicTaste;
    }

    public void setMusicTaste(String musicTaste) {
        this.musicTaste = musicTaste;
    }

    
    public long getID() {
        return ID;
    }
    
    public void setID(int ID) {
        this.ID = ID;
    }

    public long getDistance() {
        return Distance;
    }
    
    public void setDistance(int Distance) {
        this.Distance = Distance;
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

    public void setGender(String gender){
        this.gender = gender;
    }


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}


  




        