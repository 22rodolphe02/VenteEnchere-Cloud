package com.enchere.postgres.models;

import com.enchere.org.gen.dao.annotations.Colonne;
import com.enchere.org.gen.dao.annotations.Table;
import com.enchere.org.gen.dao.utils.GeneriqueDAO;

@Table
public class Admin extends GeneriqueDAO {
    @Colonne
    private Integer id;
    @Colonne
    private String email;
    @Colonne
    private String mdp;
    @Colonne
    private Double solde;


    public Admin() {
    }

    public Admin(Integer id) {
        this.id = id;
    }

    public Admin(String email, String mdp) {
        this.email = email;
        this.mdp = mdp;
    }

    public Admin(Double solde) {
        this.solde = solde;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Double getSolde() {
        return solde;
    }

    public void setSolde(Double solde) {
        this.solde = solde;
    }
}
