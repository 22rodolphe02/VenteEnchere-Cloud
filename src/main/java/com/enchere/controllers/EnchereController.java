package com.enchere.controllers;


import com.enchere.postgres.models.Enchere;
import com.enchere.utils.Database;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("api/enchere/enchere")
public class EnchereController {

    //    build create enchere REST API
    @GetMapping
    public List<Enchere> findAll() throws Exception {
        try {
            return Enchere.enchereEnCours();
        }
        catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    //    build get enchere by id REST API
    @GetMapping("{id}")
    public ResponseEntity<Enchere> fiche(@PathVariable int id) throws Exception {
        try {
            Enchere enchere = Enchere.fiche(id);
            return ResponseEntity.ok(enchere);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    //     build update enchere REST API
    @PutMapping("{id}")
    public ResponseEntity<Enchere> updateEnchere(@PathVariable int id, @RequestBody Enchere enchereDetails) throws Exception {
        try {
            enchereDetails.update(Database.getConnection(), String.valueOf(id));
            return ResponseEntity.ok(enchereDetails);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    //     build delete enchere REST API
    @DeleteMapping("{id}")
    public ResponseEntity<Enchere> deleteEnchere(@PathVariable int id) throws SQLException {
        try {
            new Enchere().delete(Database.getConnection(), String.valueOf(id));
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }


}