package com.gestion.stock.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "categorie")
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Categorie {

	   @Id
	   @GeneratedValue(strategy = GenerationType.IDENTITY)
	   private int id;
	   
	   private String designation;
	   
	   @OneToMany(mappedBy = "idCat")
	  // @JsonIgnoreProperties("idCat")
	   private List<Article> articles;

	public Categorie(int id, String designation, List<Article> articles) {
		super();
		this.id = id;
		this.designation = designation;
		this.articles = articles;
	}

	public Categorie() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public List<Article> getArticles() {
		return articles;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}

	@Override
	public String toString() {
		return "Categorie [id=" + id + ", designation=" + designation + ", articles=" + articles + "]";
	}
	   
	   
}
