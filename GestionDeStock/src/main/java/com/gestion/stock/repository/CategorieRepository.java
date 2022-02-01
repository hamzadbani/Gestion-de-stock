package com.gestion.stock.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestion.stock.entities.Categorie;

@Repository
public interface CategorieRepository extends JpaRepository<Categorie, Integer> {

}
