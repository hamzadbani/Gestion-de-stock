package com.gestion.stock.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestion.stock.entities.Article;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Integer> {

}
