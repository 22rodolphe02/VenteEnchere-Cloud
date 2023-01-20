package com.enchere.controllers;

import com.enchere.helper.SequenceHelper;
import com.enchere.postgres.models.Client;
import com.enchere.postgres.models.ClientDao;
import com.enchere.postgres.models.Token;
import com.enchere.postgres.models.Token_user;
import com.enchere.utils.Database;
import com.enchere.utils.Utils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.Timestamp;

@CrossOrigin("*")
@RestController
@RequestMapping("api/projetEnchere/client")
public class ClientController {
    @PostMapping
    public boolean login(@RequestBody ClientDao client) throws Exception {
        try {
            Connection con = Database.getConnection();
            int iduser = client.getId();
            if (iduser != 0){
                String token = Utils.creationToken(client.getEmail(), client.getMdp());
                System.out.println("Le token = "+token);
                boolean exist = Token.verifierExistanceTokenAndValidation(client.getEmail(), client.getMdp());
                if (exist == false){
                    System.out.println("Token non existant ou non valide !");
                    Timestamp dateexp = Utils.getDateExpiration(30);
                    Token tok = new Token();
                    tok.setToken(token);
                    tok.setDateexpiration(dateexp);
                    tok.insererToken(con);
                    int idtoken = Integer.parseInt(SequenceHelper.last_value("token", con));
                    System.out.println("idtoken crée ="+idtoken);
                    Token_user tu = new Token_user();
                    tu.setId_token(idtoken);
                    tu.setId_user(iduser);
                    tu.insererTokenUser(con);
                    con.close();
                }
                else {
                    System.out.println("Token existant ! Oueh !");
                }
                return true;
            }
            else return false;

        }
        catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
    @PostMapping
    public ResponseEntity<ClientDao> saveClient(@RequestBody ClientDao client) throws Exception {
        try {
            client.save(Database.getConnection());
            return ResponseEntity.ok(client);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
    @PutMapping
    public ResponseEntity<Token> updateTokenClient(@PathVariable int id, @RequestBody Token token) throws Exception{
        try {
            token.modifierToken(Database.getConnection(), String.valueOf(id));
            return ResponseEntity.ok(token);
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }
}
