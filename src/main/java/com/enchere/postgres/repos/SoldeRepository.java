package com.enchere.postgres.repos;

import com.enchere.postgres.models.Solde;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface SoldeRepository extends JpaRepository<Solde, Integer> {
    @Transactional
    @Modifying
    @Query("update Solde s set s.valider = ?1 where s.id = ?2")
    int updateValiderById(Boolean valider, Integer id);


    List<Solde> findAllByValider(boolean isValid);
}