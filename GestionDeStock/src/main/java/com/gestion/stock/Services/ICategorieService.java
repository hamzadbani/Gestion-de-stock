package com.gestion.stock.Services;

import java.util.List;
import java.util.Optional;

import com.gestion.stock.entities.Categorie;


public interface ICategorieService {

	List<Categorie> findAll();
	Categorie trouver(int id);
}
