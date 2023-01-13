package com.enchere;

import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.bson.BsonDocument;
import org.bson.BsonInt64;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class Main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mongodb.jdbc.MongoDriver");
        String url = "jdbc:mongodb://containers-us-west-125.railway.app:7555/";
        Properties properties = new Properties();
//        properties.put("database", "enchere");
        properties.put("user", "mongo");
        properties.put("password", "ClIdcQNJCHBXLx6Td3nJ");
        Connection connection = DriverManager.getConnection("jdbc:mongodb://containers-us-west-125.railway.app:7555/", properties);
        System.out.println(connection);
    }
}
