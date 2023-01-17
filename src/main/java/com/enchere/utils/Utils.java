package com.enchere.utils;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;

public class Utils {

    public static void main(String[] args) {
        String texte = "Bonjour tout le monde! Je viens de paris, je 13 ans";
        System.out.println(nbMotText(texte));
        System.out.println(compterMot(texte, "je"));
    }

    public static int nbMotText(String texte) {
        return texte.split(" ").length;
    }

    public static int compterMot(String texte, String motACompter) {
        String[] tabMot = texte.split(" ");
        int nbMotACompter = 0;
        for (String mot: tabMot) {
            if (mot.equalsIgnoreCase(motACompter)) {
                nbMotACompter++;
            }
        }
        return nbMotACompter;
    }

    public static int getAge(Date sqlDate) {
        LocalDate dateNaissance = sqlDate.toLocalDate();
        LocalDate aujourdhui = LocalDate.now();
        Period age = Period.between(dateNaissance, aujourdhui);
        return age.getYears();
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
