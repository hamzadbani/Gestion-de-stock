package com.example.gestiondestock;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gestiondestock.Model.Article;
import com.example.gestiondestock.Model.Categorie;
import com.example.gestiondestock.Utils.CategorieService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity  implements AdapterView.OnItemSelectedListener {
    Integer[] categoriesSpinner={1,2};
    Integer catSelc;
    Spinner spin;
    TextView designation;
    TextView nombre;
    ListView articles;
    TextView moyenneText;
    Double Moyenne = Double.valueOf(0);
    Integer idSelected;
    List<Article> articlesData;
    String[] ListElements = new String[] { };
    final List<String> ListElementsArrayList = new ArrayList<>(Arrays.asList(ListElements));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        designation = (TextView) findViewById(R.id.designation);
        nombre = (TextView) findViewById(R.id.nombre);
        moyenneText = (TextView) findViewById(R.id.moyenne);
        articles = (ListView) findViewById(R.id.listArticles);
        spin = (Spinner) findViewById(R.id.spinner);
        //spin.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);


        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,categoriesSpinner);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(aa);


        Button btnsearch = (Button) findViewById(R.id.search);
        btnsearch.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                 Moyenne =0.0;
                 designation.setText("");
                 ListElementsArrayList.clear();
                 catSelc = (Integer) spin.getSelectedItem();
                 listCategories(catSelc);
            }
        });

        Button btnArticle= (Button) findViewById(R.id.ajouter);
        btnArticle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openArticleActivity();
            }
        });
    }

    public void openArticleActivity(){

        String categorie = designation.getText().toString();
        Integer id = idSelected;
        Intent intent = new Intent(this, ArticleActivity.class);
        intent.putExtra("designation",categorie);
        intent.putExtra("id",id);
        startActivity(intent);
    }

    public void listCategories(Integer catSelc){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://192.168.1.109:8080/categorie/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            CategorieService categorieService = retrofit.create(CategorieService.class);

            Call<List<Categorie>> call = categorieService.getCategorie();

        call.enqueue(new Callback<List<Categorie>>() {
            @Override
            public void onResponse(Call<List<Categorie>> call, Response<List<Categorie>> response) {
                if(!response.isSuccessful()) {
                    return;
                }
                List<Categorie> categories = response.body();
                for (Categorie categorie : categories){

                    if (categorie.getId() == catSelc){
                        idSelected = categorie.getId();
                        String content="";
                        content += categorie.getDesignation();
                        designation.append(content);
                        articlesData = categorie.getArticles();
                        final ArrayAdapter<String> adapter = new ArrayAdapter<>
                                (MainActivity.this, android.R.layout.simple_list_item_1, ListElementsArrayList);
                        ListElementsArrayList.add("Id" +"                "+ "Libelle" +"                "+ "Prix unitaire");
                        for (Article article : articlesData){
                            ListElementsArrayList.add(article.getId()+"                "+article.getLibelle() +"                "+ article.getPrixUnitaire() );
                            articles.setAdapter(adapter);
                            Moyenne += article.getPrixUnitaire();
                        }
                       // nombre.setText(""+ articles.getAdapter().getCount());
                        Integer nombreElement =  ListElementsArrayList.size()-1;
                        nombre.setText("" + nombreElement);
                        adapter.notifyDataSetChanged();

                        Moyenne = Moyenne / nombreElement;
                        moyenneText.setText(Moyenne.toString());
                    }


                }

            }

            @Override
            public void onFailure(Call<List<Categorie>> call, Throwable t) {
                Log.e("Error:",t.getMessage());
                designation.setText(t.getMessage());
            }
        });
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Toast.makeText(getApplicationContext(), categoriesSpinner[i], Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
// TODO Auto-generated method stub

    }
}