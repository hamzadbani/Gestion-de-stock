package com.example.gestiondestock;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.gestiondestock.Model.Article;
import com.example.gestiondestock.Model.Categorie;
import com.example.gestiondestock.Utils.CategorieService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ArticleActivity extends AppCompatActivity {
    TextView categorieValeur;
    EditText libelleValeur;
    EditText prixU;
    Double prixUnitaire;
    Integer id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);

        categorieValeur = findViewById(R.id.categorie);
        libelleValeur = findViewById(R.id.libelle);
        prixU = findViewById(R.id.prix);




        String categorie = getIntent().getStringExtra("designation");
        id = getIntent().getIntExtra("id",0);
        categorieValeur.setText(categorie);

        Button btnretour = (Button) findViewById(R.id.retour);
        btnretour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMainActivity();
              }
           });

        Button btnajouter = (Button) findViewById(R.id.ajouter);
        btnajouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createArticle();
            }
        });
        }

        public void openMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        }

        private void createArticle(){
            Article article = new Article();
            article.setLibelle(libelleValeur.getText().toString());
            prixUnitaire = Double.parseDouble(prixU.getText().toString());
            article.setPrixUnitaire(prixUnitaire);

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://192.168.1.109:8080/article/"+id+"/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            CategorieService categorieService = retrofit.create(CategorieService.class);

            Call<Article> call = categorieService.createArticle(article);

            call.enqueue(new Callback<Article>() {
                @Override
                public void onResponse(Call<Article> call, Response<Article> response) {
                    if(!response.isSuccessful()) {
                        return;
                    }

                }

                @Override
                public void onFailure(Call<Article> call, Throwable t) {
                    Log.e("Error:",t.getMessage());
                }
            });

            categorieValeur.setText("");
            libelleValeur.setText("");
            prixU.setText("");
        }

}