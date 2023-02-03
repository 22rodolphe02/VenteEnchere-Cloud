package com.enchere.controllers;

import com.enchere.postgres.models.MouvementSolde;
import com.enchere.postgres.repos.MouvementsoldeRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/projetEnchere/mouvement")
@CrossOrigin("*")
public class MouvementSoldeController {
    private final MouvementsoldeRepository mouvementRepos;

    public MouvementSoldeController(MouvementsoldeRepository mouvementRepos){
        this.mouvementRepos = mouvementRepos;
    }

    @GetMapping("")
    public MouvementSoldeResponse list(){
        return new MouvementSoldeResponse(mouvementRepos.findAll());
    }
}

class MouvementSoldeResponse{
    private List<MouvementSolde> mouvements;

    public MouvementSoldeResponse(List<MouvementSolde> mouvements) {
        this.mouvements = mouvements;
    }

    public List<MouvementSolde> getMouvements() {
        return mouvements;
    }

    public void setMouvements(List<MouvementSolde> mouvements) {
        this.mouvements = mouvements;
    }
}
