package com.earworm.backendearworm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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

            // Loop through each product found and get its information.
            while (rs.next()) {
                String currentUser = rs.getString("name");
                int id = rs.getInt("user_id");
                
                int userId = rs.getInt("user_id");
                int zipcode = rs.getInt("zipCode");
                String bio = rs.getString("bio");
                int type = rs.getInt("type");
                // Store the product's information in the product repository in the local H2 database
                log.info("Preloading " + repository.save(
                        new User(id,
                                zipcode,
                                userName,
                                bio,
                                User.UserType.values()[type])));
            }
        };
    }
}