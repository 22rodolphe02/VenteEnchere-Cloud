package com.enchere;

import com.enchere.postgres.models.Enchere;
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
import java.util.List;
import java.util.Properties;


public class Main {
    public static void main(String[] args) throws Exception {
        List<Enchere> l = Enchere.enchereEnCours();
        for (Enchere enchere : l) {
            System.out.println(enchere.getId());
        }
    }
}
