package com.gestion.stock.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestion.stock.Services.IArticleService;
import com.gestion.stock.Services.ICategorieService;
import com.gestion.stock.entities.Article;
import com.gestion.stock.entities.Categorie;



@RestController
@RequestMapping("/article")
public class ArticleController {

	  @Autowired
      private IArticleService IarticleService;
	  
	  @Autowired
      private ICategorieService IcategorieService;
	  
	   @PostMapping("/{id}/save")
		public void addJob(@PathVariable int id ,@RequestBody Article article) {
		   Categorie Findcategorie = IcategorieService.trouver(id);
		   article.setIdCat(Findcategorie);
		   IarticleService.save(article);
		}

}
