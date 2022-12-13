

package com.earworm.backendearworm;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import com.earworm.backendearworm.User;



public class Driver {

   
    public static void main(String... args) {
        List<User> Users = readUsersFromCSV("books.txt");

        // let's print all the person read from CSV file
        for (User b : Users) {
            System.out.println(b);
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

                User user = createUser(attributes);

                // adding book into ArrayList
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
        String lastName = metadata[1];
        String firstName = metadata[2];
        String password = metadata[3];
        String email = metadata[4];
        String userName = metadata[5];
        String musicTaste = metadata[6];
        int DOB = Integer.parseInt(metadata[7]);
        String gender = metadata[8];
        int Distance = Integer.parseInt(metadata[9]);
        int phoneNumber = Integer.parseInt(metadata[10]);

        // create and return book of this metadata
        return new User(ID , userName, password, email, firstName, lastName, musicTaste,  DOB , gender,  Distance, phoneNumber);
    }

}

  




        