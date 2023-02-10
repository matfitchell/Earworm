package com.earworm;

import java.sql.*;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
//import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.util.backoff.BackOffExecution;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

import com.earworm.backendearworm.GCloudConnector;

@SpringBootApplication
@RestController

public class BackdendEarwormApplication {

	public static void main(String[] args) {

		// SpringBoot Launch
		SpringApplication.run(BackdendEarwormApplication.class, args);

		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				try {
					Connection conn = GCloudConnector.getInstance().connection;
					if (conn != null) {
						conn.close();
						System.out.println("Database Connection Terminated");
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
	}
}
