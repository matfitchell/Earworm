package SourceCode.BackEnd;

import java.sql.*;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@SpringBootApplication
@RestController

public class Driver {


public static void main(String[] args){

    //SpringBoot Launch
    SpringApplication.run(Driver.class, args);

    Runtime.getRuntime().addShutdownHook(new Thread(){
        @Override
                public void run(){
            try {
                // need to fix this for AWS connection
                Connection conn = AWS.getInstance();
                if (conn != null){
                    conn.close();
                    System.out.println("Database Connection Terminated")
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        })
    }
}   