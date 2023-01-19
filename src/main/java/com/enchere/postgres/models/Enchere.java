package com.enchere.postgres.models;

import com.enchere.org.gen.dao.annotations.Colonne;
import com.enchere.org.gen.dao.annotations.Table;
import com.enchere.org.gen.dao.utils.GeneriqueDAO;
import com.enchere.utils.Database;
import com.enchere.utils.Utils;

import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Table
public class Enchere extends GeneriqueDAO {
    @Colonne
    private Integer id;
    @Colonne
    private String produit;
    @Colonne
    private String description;
    @Colonne
    private Double prixMin;
    @Colonne
    private Timestamp dateDebut;
    @Colonne
    private Time duree;
    @Colonne
    private Integer idCategorie;
    @Colonne
    private Integer idClient;
    @Colonne
    private Boolean fini;
    private Boolean dejaMiser;


    public static List<Enchere> rechercheAvancer(String produit, String description, String prixMinMin, String prixMinMax, String dateDebutMin, String dateDebutMax, String dureeMin, String dureeMax, String idCategorie) throws Exception {
        try {
            Enchere enchere = new Enchere();
            if (!produit.equals("")) {
                enchere.setProduit(produit);
            }
            if (!description.equals("")) {
                enchere.setDescription(description);
            }
            if (!idCategorie.equals("")) {
                enchere.setIdCategorie(Integer.parseInt(idCategorie));
            }

            List<Enchere> encheres = (List<Enchere>) enchere.list(Database.getConnection());
            int nbEnchere = encheres.size();

            if (nbEnchere!=0 && !prixMinMin.equals("")) {
                Double prixMinMinD = Double.valueOf(prixMinMin);
                for (int i = 0; i < nbEnchere; i++) {
                    if (encheres.get(i).getPrixMin() < prixMinMinD) {
                        encheres.remove(i);
                    }
                }
                nbEnchere = encheres.size();
            }

            if (nbEnchere!=0 && !prixMinMax.equals("")) {
                Double prixMinMaxD = Double.valueOf(prixMinMax);
                for (int i = 0; i < nbEnchere; i++) {
                    if (encheres.get(i).getPrixMin() > prixMinMaxD) {
                        encheres.remove(i);
                    }
                }
                nbEnchere = encheres.size();
            }

            if (nbEnchere!=0 && !dateDebutMin.equals("")) {
                java.sql.Date dateDebutMinD = Utils.toDateSql(dateDebutMin);
                for (int i = 0; i < nbEnchere; i++) {
                    if (encheres.get(i).getDateDebut().before(dateDebutMinD)) {
                        encheres.remove(i);
                    }
                }
                nbEnchere = encheres.size();
            }

            if (nbEnchere!=0 && !dateDebutMax.equals("")) {
                java.sql.Date dateDebutMaxD = Utils.toDateSql(dateDebutMax);
                for (int i = 0; i < nbEnchere; i++) {
                    if (encheres.get(i).getDateDebut().after(dateDebutMaxD)) {
                        encheres.remove(i);
                    }
                }
                nbEnchere = encheres.size();
            }

            if (nbEnchere!=0 && !dureeMin.equals("")) {
                Time dureeMinD = Utils.toTime(dureeMin);
                for (int i = 0; i < nbEnchere; i++) {
                    if (encheres.get(i).getDuree().before(dureeMinD)) {
                        encheres.remove(i);
                    }
                }
                nbEnchere = encheres.size();
            }

            if (nbEnchere!=0 && !dureeMax.equals("")) {
                Time dureeMaxD = Utils.toTime(dureeMax);
                for (int i = 0; i < nbEnchere; i++) {
                    if (encheres.get(i).getDuree().after(dureeMaxD)) {
                        encheres.remove(i);
                    }
                }
                nbEnchere = encheres.size();
            }

            return encheres;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }


    public static Enchere fiche(int idEnchere) throws Exception {
        try {
            Enchere enchere = (Enchere) new Enchere(idEnchere).list(Database.getConnection()).get(0);
            enchere.setDejaMiser();
            return enchere;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }


    public static List<Enchere> enchereEnAttente() throws Exception {
        try {
            List<Enchere> enchereEnCours = enchereEnCours();
            List<Enchere> encherePasFini = encherePasFini();
            int nbEncherePasFini = encherePasFini.size();
            int nbEnchereEnCours = enchereEnCours.size();
            for (int i = 0; i < nbEncherePasFini; i++) {
                for (Enchere enchereEnCour : enchereEnCours) {
                    if (Objects.equals(enchereEnCour.getId(), encherePasFini.get(i).getId())) {
                        encherePasFini.remove(i);
                    }
                }
            }
            return encherePasFini;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public static List<Enchere> enchereEnCours() throws Exception {
        try {
            List<Enchere> encheres = encherePasFini();
            int nbEnchere = encheres.size();
            for (int i = 0; i < nbEnchere; i++) {
                if (encheres.get(i).getDateDebut().after(new Timestamp(new Date().getTime()))) {
                    encheres.remove(i);
                }
                else {
                    encheres.get(i).setDejaMiser();
                }
            }
            return encheres;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public static List<Enchere> encherePasFini() throws Exception {
        try {
            terminerEnchere();
            return (List<Enchere>) new Enchere(false).list(Database.getConnection());
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public static List<Enchere> enchereFini() throws Exception {
        try {
            terminerEnchere();
            return (List<Enchere>) new Enchere(true).list(Database.getConnection());
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public static void terminerEnchereById(int idEnchere) throws Exception {
        try {
            String dureeRestant = getDureeRestantById(idEnchere);
            if (dureeRestant.toCharArray()[0] == '-') {
                System.out.println("Update fini enchere");
                new Enchere(true).update(Database.getConnection(), String.valueOf(idEnchere));
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public static void terminerEnchere() throws Exception {
        try {
            List<Enchere> encheres = (List<Enchere>) new Enchere(false).list(Database.getConnection());
            for (Enchere enchere: encheres) {

                terminerEnchereById(enchere.getId());
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }


    public static String getDureeRestantById(int idEnchere) throws SQLException {
        try {
            return GeneriqueDAO.execute(Database.getConnection(), "select ((dateDebut+duree) - now()) as dureeRestant from enchere where id=" + idEnchere + " order by id asc")[0][0];
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }


    public static String[] getDureeRestant() throws SQLException {
        try {
            String[][] tabDuree = GeneriqueDAO.execute(Database.getConnection(), "select ((dateDebut+duree) - now()) as dureeRestant from enchere order by id asc");
            int tailleTabDuree = tabDuree.length;
            String[] reponse = new String[tailleTabDuree];
            for (int i = 0; i < tailleTabDuree; i++) {
                reponse[i] = tabDuree[i][0];
            }
            return reponse;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }



//  CONSTRUCTEUR
    public Enchere() {
    }

    public Enchere(Integer id) {
        this.id = id;
    }

    public Enchere(Boolean fini) {
        this.setFini(fini);
    }

    public Enchere(String produit, String description, Double prixMin, Timestamp dateDebut, Time duree, Integer idCategorie, Integer idClient) {
        this.produit = produit;
        this.description = description;
        this.prixMin = prixMin;
        this.dateDebut = dateDebut;
        this.duree = duree;
        this.idCategorie = idCategorie;
        this.idClient = idClient;
    }


//  GETTERS ET SETTERS
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProduit() {
        return produit;
    }

    public void setProduit(String produit) {
        this.produit = produit;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrixMin() {
        return prixMin;
    }

    public void setPrixMin(Double prixMin) {
        this.prixMin = prixMin;
    }

    public Timestamp getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Timestamp dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Time getDuree() {
        return duree;
    }

    public void setDuree(Time duree) {
        this.duree = duree;
    }

    public Integer getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(Integer idCategorie) {
        this.idCategorie = idCategorie;
    }

    public Integer getIdClient() {
        return idClient;
    }

    public void setIdClient(Integer idClient) {
        this.idClient = idClient;
    }

    public Boolean getFini() {
        return fini;
    }

    public void setFini(Boolean fini) {
        this.fini = fini;
    }

    public Boolean getDejaMiser() {
        return dejaMiser;
    }

    public void setDejaMiser(Boolean dejaMiser) {
        this.dejaMiser = dejaMiser;
    }

    public void setDejaMiser() throws SQLException {
        try {
            this.setDejaMiser(GeneriqueDAO.execute(Database.getConnection(), "select * from offre where idEnchere=" + this.getId() + "and idClient=" + this.getIdClient()).length != 0);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}