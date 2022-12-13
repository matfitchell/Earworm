package com.earworm.backendearworm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

  
    public CommandLineRunner initDatabase(UserRepository repository) {

        return args -> {
            // Get the Google Cloud connection
            Connection conn = GCloudConnector.getInstance().connection;
            // Query the Google Cloud database for the users
            String query = "SELECT * FROM users";
            Statement statement = conn.createStatement();

            ResultSet rs = statement.executeQuery(query);

            // Loop through each user found and get their information.
            while (rs.next()) {
                String currentUser = rs.getString("firstName" + "," + "lastName");
                int id = rs.getInt("userID");
                
                int userId = rs.getInt("userID");
                String userName = rs.getString("userName");
                String password = rs.getString("password");
                String email = rs.getString("email");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                String musicTaste = rs.getString("musicTaste");
                java.sql.Date DOB = rs.getDate("DOB");
                String gender = rs.getString("gender");
                BigDecimal longitude = rs.getBigDecimal("longitude");
                BigDecimal latitude = rs.getBigDecimal("latitude");
                

                // Store the product's information in the product repository in the local H2 database
                log.info("Preloading " + repository.save(
                        new User(userId,
                                userName,
                                password,
                                email,
                                firstName,
                                lastName,
                                musicTaste,
                                DOB,
                                gender,
                                longitude,
                                latitude,
                                User.UserType.values()[type])));
            }
        };
    }
}