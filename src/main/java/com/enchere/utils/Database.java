package com.enchere.utils;

import org.springframework.boot.jdbc.DataSourceBuilder;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    private static Connection connection;
    private static final String host = "containers-us-west-19.railway.app";

    private static final String database = "railway";
    private static final int port = 6336;
    private static final String user = "postgres";
    private static final String pass = "jYeEtXwfTysoGJBtzHDg";
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
