package com.gestion.stock.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestion.stock.entities.Article;
import com.gestion.stock.repository.ArticleRepository;

@Service
public class ArticleService implements IArticleService {

	@Autowired
	ArticleRepository articleRepository;


	@Override
	public void save(Article article) {
		articleRepository.save(article);
		
	}

}
