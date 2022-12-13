package com.earworm.backendearworm;

import java.sql.*;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.backoff.BackOffExecution;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@SpringBootApplication
@RestController

public class BackdendEarwormApplication {

	public static void main(String[] args) {

		// SpringBoot Launch
		SpringApplication.run(BackdendEarwormApplication.class, args);

<<<<<<< Updated upstream
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
=======
		Runtime.getRuntime().addShutdownHook(new Thread(){
@Override
public void run(){
		try {
		Connection conn = GCloudConnector.getInstance().connection;
		if (conn != null){
		conn.close();
		System.out.println("Database Connection Terminated");
			}
		}
		catch (SQLException e) {
		e.printStackTrace();
		}
				}
			}
		);
		}
>>>>>>> Stashed changes
	}
}
