package com.enchere.controllers;


import com.enchere.postgres.models.Solde;
import com.enchere.postgres.repos.MouvementsoldeRepository;
import com.enchere.postgres.repos.SoldeRepository;
import com.enchere.response.ResponseData;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/soldes")
@CrossOrigin("*")
public class SoldeController {
    private final SoldeRepository soldeRepos;
    private final MouvementsoldeRepository mouvementRepos;

    public SoldeController(SoldeRepository soldeRepos, MouvementsoldeRepository mouvementRepos){
        this.soldeRepos = soldeRepos;
        this.mouvementRepos = mouvementRepos;
    }

    @GetMapping("/depot-non-valid")
    public ResponseData<List<Solde>> listDepotNonValider(){
        List<Solde> soldes = soldeRepos.findByValiderAndMouvementSolde_Id(false, 1);
        if (soldes == null){
            return new ResponseData<>(false, null);
        }
        return new ResponseData<>(true, soldes);
    }

    @PostMapping("/")
    public ResponseEntity<Integer> save(Solde solde){
        soldeRepos.save(solde);
        return new ResponseEntity<>(1, HttpStatus.ACCEPTED);
    }

    @PutMapping("/")
    public int update(@RequestBody Solde solde){
        return soldeRepos.updateValiderById(solde.getValider(), solde.getId());
    }

}
