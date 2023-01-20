package com.enchere.postgres.models;

import com.enchere.org.gen.dao.annotations.Colonne;
import com.enchere.org.gen.dao.annotations.Table;
import com.enchere.org.gen.dao.utils.GeneriqueDAO;
import com.enchere.utils.Database;

import java.util.List;

@Table(name = "client")
public class ClientDao extends GeneriqueDAO {
    @Colonne
    private Integer id;
    @Colonne
    private String nom;
    @Colonne
    private String prenom;
    @Colonne
    private String email;
    @Colonne
    private String mdp;

    public ClientDao() {
    }

    public ClientDao(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }
    public int getIdClient() throws Exception{
        List<Client> client = (List<Client>) this.list(Database.getConnection());
        int id =client.get(0).getId();
        return id;
    }
}
