package com.gestion.stock.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "article")
public class Article {

		   @Id
		   @GeneratedValue(strategy = GenerationType.IDENTITY)
		   private int id;
		   
		   private String libelle;
		   private Double prixUnitaire;
		   
		   @ManyToOne
		   @JoinColumn(name ="idCat")
		   @JsonIgnore
		   private Categorie idCat;

		public Article() {
			super();
			// TODO Auto-generated constructor stub
		}

		public Article(int id, String libelle, Double prixUnitaire, Categorie idCat) {
			super();
			this.id = id;
			this.libelle = libelle;
			this.prixUnitaire = prixUnitaire;
			this.idCat = idCat;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getLibelle() {
			return libelle;
		}

		public void setLibelle(String libelle) {
			this.libelle = libelle;
		}

		public Double getPrixUnitaire() {
			return prixUnitaire;
		}

		public void setPrixUnitaire(Double prixUnitaire) {
			this.prixUnitaire = prixUnitaire;
		}
		
		public Categorie getIdCat() {
			return idCat;
		}

		public void setIdCat(Categorie idCat) {
			this.idCat = idCat;
		}

		@Override
		public String toString() {
			return "Article [id=" + id + ", libelle=" + libelle + ", prixUnitaire=" + prixUnitaire + ", idCat=" + idCat
					+ "]";
		}
		   
		   
}
