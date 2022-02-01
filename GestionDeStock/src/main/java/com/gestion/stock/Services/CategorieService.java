package com.gestion.stock.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.gestion.stock.entities.Categorie;
import com.gestion.stock.repository.CategorieRepository;

@Service
public class CategorieService implements ICategorieService {

	@Autowired
	CategorieRepository categorieRepository;
	
	@Override
	public List<Categorie> findAll() {
		// TODO Auto-generated method stub
		return categorieRepository.findAll();
	}


	public Categorie trouver( int id){
		List<Categorie> categories =findAll();
		Categorie categorieReturned = null;
		for(Categorie categorie : categories){
			if(categorie.getId() == id) {
				categorieReturned = categorie;
			}
		}
		return categorieReturned;
	}
}
