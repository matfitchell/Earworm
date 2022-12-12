package com.earworm.backendearworm;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;



public class GCloudConnector {

    private static final String INSTANCE_CONNECTION_NAME = System.getenv("INSTANCE_CONNECTION_NAME");
    private static final String INSTANCE_UNIX_SOCKET = System.getenv("DBINSTANCEUNIXSOCKET");
    private static final String DB_USER = System.getenv("DBUSER");
    private static final String DB_PASS = System.getenv("DB_PASS");
    private static final String DB_NAME = System.getenv("DBNAME");
    private static GCloudConnector instance;

    private GCloudConnector(Connection conn){
        connection = conn;
    }

    public Connection connection;


    public static GCloudConnector getInstance() throws SQLException{
        if(instance != null){
            if(instance.connection.isClosed()){

                instance.connection = getConnection();
            }
            //instance exists, thus return
            return instance;
        }
        // no instance exists, create one
        instance = new GCloudConnector(getConnection());
        return instance;
    }

    private static Connection getConnection(){
        Connection conn = null;
        try{
            conn = createConnectionPool().getConnection();
            System.out.println("Database connection established");
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            return conn;
        }
    }
    private static DataSource createConnectionPool(){
        // Config Object to specify connection specs
        HikariConfig config = new HikariConfig();

        config.setConnectionTestQuery(DB_NAME);
        config.setPoolName(DB_NAME);
        config.addDataSourceProperty("socketFactory","com.google.cloud.sql.mysql.SocketFactory");
        config.addDataSourceProperty("cloudSqlInstance", INSTANCE_CONNECTION_NAME);

        //  set url format
        
        config.setJdbcUrl(String.format("jdbc:mysql:///%s", DB_NAME));
        config.setUsername(DB_USER);
        config.setPassword(DB_PASS);
        
        //Using a Unix socket if possible

        if(INSTANCE_UNIX_SOCKET != null){
            config.addDataSourceProperty("unixSocketPath", INSTANCE_UNIX_SOCKET);
            config.setMaximumPoolSize(5);
        }
        return new HikariDataSource(config);
    }
}
