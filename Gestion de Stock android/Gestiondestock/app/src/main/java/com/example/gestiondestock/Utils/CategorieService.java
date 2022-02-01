package com.example.gestiondestock.Utils;

import com.example.gestiondestock.Model.Article;
import com.example.gestiondestock.Model.Categorie;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface CategorieService {

    @GET("findall")
    Call<List<Categorie>> getCategorie();

    @POST("save")
    Call<Article> createArticle(@Body Article article);
}
