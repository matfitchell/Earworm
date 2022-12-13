package com.earworm.backendearworm;


import java.io.Serializable;


public class UserServiceBean implements Serializable {
    
    private int ID;
    private String firstName;
    private String lastName;
    private int birthYear;
    private int distance;

    public UserServiceBean(){

    }

    public UserServiceBean(int id, String fN, String lN, int bY, int dist){
        this.ID = id;
        this.firstName = fN;
        this.lastName = lN;
        this.distance = dist;
        this.birthYear = bY;
    }

public int getID(){
    return ID;
}
public void setID(int ID){
    this.ID = ID;
}

public String getfirstName(){
    return firstName;
}

public void setfirstName(String firstName){
    this.firstName = firstName;
}

public String getlastName(){
    return lastName;
}

public void setlastName(String lastName){
    this.lastName = lastName;
}

public int getDistance(){
    return distance; 
}
public void setDistance(int distance){
    this.distance = distance;
}

public int getbirthYear(){
    return birthYear;
}
public void setbirthYear(int birthYear){
    this.birthYear = birthYear;
}

}
