package com.gestion.stock.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestion.stock.Services.ICategorieService;
import com.gestion.stock.entities.Categorie;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/categorie")
public class CategorieController {

	  @Autowired
      private ICategorieService IcategorieService;
	  
		@GetMapping("/findall")
		public List<Categorie> fetchAll(){
			return IcategorieService.findAll();
		}
		@GetMapping("/trouver/{id}")
		public Categorie trouver(@PathVariable int id){
		//	List<Categorie> categories =IcategorieService.findAll();
		//	Categorie categorieReturned = null;
		//	for(Categorie categorie : categories){
		//		if(categorie.getId() == id) {
		//			categorieReturned = categorie;
		//		}
		//	}
		//	return categorieReturned;
			return IcategorieService.trouver(id);
		}
}
