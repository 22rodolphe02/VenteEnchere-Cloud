package com.enchere.postgres.models;

import com.enchere.org.gen.dao.annotations.Colonne;
import com.enchere.org.gen.dao.annotations.Table;
import com.enchere.org.gen.dao.utils.GeneriqueDAO;

@Table
public class Categorie extends GeneriqueDAO {
    @Colonne
    private Integer id;
    @Colonne
    private String categorie;

    public Categorie() {
    }

    public Categorie(Integer id) {
        this.id = id;
    }

    public Categorie(String categorie) {
        this.categorie = categorie;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }
}
