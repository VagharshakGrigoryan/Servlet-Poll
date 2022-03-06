package dao.config;

import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {

    private static final String DRIVER_CLASS_NAME = "org.h2.Driver";
    private static final String URL = "jdbc:h2:D:\\servletPoll\\src\\main\\java\\db\\poll";
    private static final String PASSWORD = "root";
    private static final String USERNAME = "root";

    private final Connection connection;
    private static ConnectionFactory connectionFactory;

    @SneakyThrows
    private ConnectionFactory(){
        Class.forName(DRIVER_CLASS_NAME);
        this.connection =  DriverManager.getConnection(URL,USERNAME,PASSWORD);
    }

    public static ConnectionFactory getInstance(){
        synchronized (ConnectionFactory.class){
            if(connectionFactory == null){
                connectionFactory = new ConnectionFactory();
            }
        }
        return connectionFactory;
    }

    public Connection getConnection() {
        return connection;
    }
}