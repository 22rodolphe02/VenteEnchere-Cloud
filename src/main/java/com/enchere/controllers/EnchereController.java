package com.enchere.controllers;


import com.enchere.postgres.models.ClientDao;
import com.enchere.postgres.models.Enchere;
import com.enchere.postgres.models.Offre;
import com.enchere.postgres.models.RechercheAvance;
import com.enchere.utils.Database;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("api/projetEnchere/enchere")
public class EnchereController {

    //    build create enchere REST API
   /* @GetMapping("/recordAcheteur")
    public ClientDao recordAcheteur() throws Exception {
        try {
            return  Enchere.recordAcheteur();
        }
        catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }*/

    //    build create enchere REST API
    @GetMapping("/recordEnchere")
    public Enchere recordEnchere() throws Exception {
        try {
            return Enchere.recordEnchere();
        }
        catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    //    build create enchere REST API
    @PostMapping("/rechercheAvancer")
    public List<Enchere> rechercheAvancer(@RequestBody RechercheAvance rechercheAvance) throws Exception {
        try {
            return rechercheAvance.rechercheAvancer();
        }
        catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @GetMapping("/getEnchereParClient/{idClient}")
    public List<Enchere> getEnchereParClient(@PathVariable int idClient) throws Exception {
        try {
            return (List<Enchere>) Enchere.getEnchereParClient(idClient);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @GetMapping("/leursEnchere/{monIdClient}")
    public List<Enchere> leursEnchere(@PathVariable int monIdClient) throws Exception {
        try {
            List<Enchere> encheres = Enchere.leursEnchereEnAttente(monIdClient);
            encheres.addAll(Enchere.leursEnchereEnCours(monIdClient));
            encheres.addAll(Enchere.leursEnchereTermine(monIdClient));
            encheres.sort((o1, o2) -> {
                return (o1.getDateDebut().getTime() + o1.getDuree().getTime()) > (o2.getDateDebut().getTime() + o2.getDuree().getTime()) ? -1 : 0;
            });
            return encheres;
        }
        catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @GetMapping("/mesEnchere/{monIdClient}")
    public List<Enchere> mesEnchere(@PathVariable int monIdClient) throws Exception {
        try {
            List<Enchere> encheres = Enchere.mesEnchereEnAttente(monIdClient);
            encheres.addAll(Enchere.mesEnchereEnCours(monIdClient));
            encheres.addAll(Enchere.mesEnchereTermine(monIdClient));
            encheres.sort((o1, o2) -> {
                return (o1.getDateDebut().getTime() + o1.getDuree().getTime()) > (o2.getDateDebut().getTime() + o2.getDuree().getTime()) ? -1 : 0;
            });
            return encheres;
        }
        catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @GetMapping("/leursEnchereEnAttente/{monIdClient}")
    public List<Enchere> leursEnchereEnAttente(@PathVariable int monIdClient) throws Exception {
        try {
            return Enchere.leursEnchereEnAttente(monIdClient);
        }
        catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @GetMapping("/mesEnchereEnAttente/{monIdClient}")
    public List<Enchere> mesEnchereEnAttente(@PathVariable int monIdClient) throws Exception {
        try {
            return Enchere.mesEnchereEnAttente(monIdClient);
        }
        catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @GetMapping("/toutesLesEnchereEnAttente")
    public List<Enchere> toutesLesEnchereEnAttente() throws Exception {
        try {
            return Enchere.enchereEnAttente();
        }
        catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @GetMapping("/leursEnchereTermine/{monIdClient}")
    public List<Enchere> leursEnchereTermine(@PathVariable int monIdClient) throws Exception {
        try {
            return Enchere.leursEnchereTermine(monIdClient);
        }
        catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @GetMapping("/mesEnchereTermine/{monIdClient}")
    public List<Enchere> mesEnchereTermine(@PathVariable int monIdClient) throws Exception {
        try {
            return Enchere.mesEnchereTermine(monIdClient);
        }
        catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @GetMapping("/toutesLesEnchereTermine")
    public List<Enchere> toutesLesEnchereTermine() throws Exception {
        try {
            return Enchere.enchereFini();
        }
        catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @GetMapping("/fiche/{idEnchere}")
    public ResponseEntity<Enchere> fiche(@PathVariable int idEnchere) throws Exception {
        try {
            Enchere enchere = Enchere.fiche(idEnchere);
            return ResponseEntity.ok(enchere);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @GetMapping("/leursEnchereEnCours/{monIdClient}")
    public List<Enchere> leursEnchereEnCours(@PathVariable int monIdClient) throws Exception {
        try {
            return Enchere.leursEnchereEnCours(monIdClient);
        }
        catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @GetMapping("/mesEnchereEnCours/{monIdClient}")
    public List<Enchere> mesEnchereEnCours(@PathVariable int monIdClient) throws Exception {
        try {
            return Enchere.mesEnchereEnCours(monIdClient);
        }
        catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @GetMapping("/toutesLesEnchereEnCours")
    public List<Enchere> toutesLesEnchereEnCours() throws Exception {
        try {
            return Enchere.toutesLesEnchereEnCours();
        }
        catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @GetMapping("/toutesLesEnchere")
    public List<Enchere> toutesLesEnchere() throws Exception {
        try {
            return Enchere.toutesLesEnchere();
        }
        catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }




    //     build update offre REST API
    @PostMapping("/encherir")
    public ResponseEntity<Offre> encherir(@RequestBody Offre offreSave) throws Exception {
        try {
            offreSave.save(Database.getConnection());
            return ResponseEntity.ok(offreSave);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    //     build update enchere REST API
    @PostMapping("/save")
    public ResponseEntity<Enchere> insertEnchere(@RequestBody Enchere enchereSave) throws Exception {
        try {
            enchereSave.save(Database.getConnection());
            return ResponseEntity.ok(enchereSave);
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
