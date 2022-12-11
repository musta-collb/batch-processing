package com.example.batchprocessing.repositories;

import com.example.batchprocessing.entities.Compte;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompteRepository extends JpaRepository<Compte, Long> {
}