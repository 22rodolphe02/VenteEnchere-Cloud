package com.enchere.utils;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;

public class Utils {


    public static Time toTime(String valeur) {
        return Time.valueOf(valeur.split("\\.")[0]);
    }

    public static Timestamp toTimestamp(String valeur) {
        System.out.println("aaaaaaaaaaaaaaaaaaa = " + castTimeStamp(valeur));
//        return Timestamp.valueOf(castTimeStamp(valeur));
        return Timestamp.valueOf(valeur);
    }

    public static String castTimeStamp(String s) {
        s = s.replace("T", " ");
        s = s + ":00";
        return s;
    }

    public static java.util.Date toDate(String valeur) throws ParseException {
        try {
            valeur = valeur.replace("-", "/");
            String[] splitValeur = valeur.split("/");
            if (splitValeur[0].length()==4) {
                valeur = splitValeur[2] + splitValeur[1] + splitValeur[0];
                System.out.println("valeur = " + valeur);
            }
            valeur = valeur.replace("/", "");
            return (java.util.Date) new SimpleDateFormat("ddMMyyyy").parse(valeur);
        }
        catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public static Date toDateSql(String valeur) throws ParseException {
        try {
            java.util.Date parsed = toDate(valeur);
            return new Date(parsed.getTime());
        }
        catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
