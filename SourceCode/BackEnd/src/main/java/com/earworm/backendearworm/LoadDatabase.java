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

  
    public CommandLineRunner initDatabase(userRepository repository) {

        return args -> {
            // Get the Google Cloud connection
            Connection conn = GCloudConnector.getInstance().connection;
            // Query the Google Cloud database for the products
            String query = "SELECT * FROM products";
            Statement statement = conn.createStatement();

            ResultSet rs = statement.executeQuery(query);

            // Loop through each product found and get its information.
            while (rs.next()) {
                String currentUser = rs.getString("name");
                int id = rs.getInt("product_id");
                int productId = rs.getInt("product_id");
                int price = rs.getInt("price");
                String description = rs.getString("description");
                int type = rs.getInt("type");
                String img = rs.getString("img");
                // Store the product's information in the product repository in the local H2 database
                /*log.info("Preloading " + repository.save(
                        new Product(id,
                                price,
                                productName,
                                description,
                                img,
                                Product.ProductType.values()[type])));
                */
            }
        };
    }
}