package SourceCode.BackEnd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import javax.sql.DataSource;
public class AWSConnector {

    private static final String INSTANCE_CONNECTION_NAME = System.getenv("DBINSTANCENAME");
    private static final String INSTANCE_UNIX_SOCKET = System.getenv("DBINSTANCEUNIXSOCKET");
    private static final String DB_USER = System.getenv("DBUSER");
    private static final String DB_PASS = System.getenv("DB_PASS");
    private static final String DB_NAME = System.getenv("DBNAME");

    private AWSConnector(Connection conn){
        connection = conn;
    }

    public Connection connection;


    public static AWSConnector getInstance() throws SQLException{
        if(instance != null){
            if(instance.connection.isClosed()){

                instance.connection = getConnection();
            }
            //instance exists, thus return
            return instance;
        }
        // no instance exists, create one
        instance = new AWSConnector(getConnection());
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
        // COnfig Object to specify connection specs
        HikariConfig config = new HikariConfig();

        //  set url format
        config.setJdbcUrl(String.format("jdbc:mysql:///%s", DB_NAME));
        config.setUsername(DB_USER);
        config.setPassword(DB_PASS);
        config.addDataSourceProperty(propertyName:"socketFactory", value: //aws specific url goes here"");
        config.addDataSourceProperty(propertyName:"cloudSqlInstance", INSTANCE_CONNECTION_NAME);

        //Using a Unix socket if possible

        if(INSTANCE_UNIX_SOCKET != null){
            config.addDataSourceProperty(propertyName: "unixSocketPath", INSTANCE_UNIX_SOCKET);
        }

        config.setMaximumPoolSize(maxPoolSize:1)
        return new HikariDataSource(config);
    }
}
