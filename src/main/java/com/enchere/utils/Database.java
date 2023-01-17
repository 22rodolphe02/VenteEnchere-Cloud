package com.enchere.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    private static Connection connection;
    private static final String host = "localhost";

    private static final String database = "enchere";
    private static final int port = 5432;
    private static final String user = "postgres";
    private static final String pass = "eric1545";
    private static String url = "jdbc:postgresql://%s:%d/%s";

    public Database()
    {
        url = String.format(url, host, port, database);
        connect();
    }

    private static void connect()
    {
        try
        {
            Class.forName("org.postgresql.Driver");
            url = String.format(url, host, port, database);
            connection = DriverManager.getConnection(url, user, pass);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()){
            connect();
        }
        return connection;
    }
}
