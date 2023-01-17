package com.enchere.controllers;

import com.enchere.postgres.models.Admin;
import com.enchere.utils.Database;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("api/enchere/admin")
public class AdminController {


    //    build create admin REST API
    @GetMapping
    public List<Admin> findAll() throws Exception {
        try {
            System.out.println("ok");
            return (List<Admin>) new Admin().list(Database.getConnection());
        }
        catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    //    build create admin REST API
    @PostMapping
    public boolean login(@RequestBody Admin admin) throws Exception {
        try {
            return admin.list(Database.getConnection()).size() != 0;
        }
        catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    //    build get admin by id REST API
    @GetMapping("{id}")
    public ResponseEntity<Admin> getAdminById(@PathVariable int id) throws Exception {
        Admin admin = null;
        try {
            admin = ((List<Admin>) new Admin(id).list(Database.getConnection())).get(0);
            return ResponseEntity.ok(admin);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    //     build update admin REST API
    @PutMapping("{id}")
    public ResponseEntity<Admin> updateAdmin(@PathVariable int id, @RequestBody Admin adminDetails) throws Exception {
        try {
            adminDetails.update(Database.getConnection(), String.valueOf(id));
            return ResponseEntity.ok(adminDetails);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    //     build delete admin REST API
    @DeleteMapping("{id}")
    public ResponseEntity<Admin> deleteAdmin(@PathVariable int id) throws SQLException {
        try {
            new Admin().delete(Database.getConnection(), String.valueOf(id));
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

}
