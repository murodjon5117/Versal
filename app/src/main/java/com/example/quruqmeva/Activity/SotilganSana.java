package com.example.quruqmeva.Activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quruqmeva.Adapter.KunlikSotilgan;
import com.example.quruqmeva.Kerakli;
import com.example.quruqmeva.Modellar.SotilganYuklar;
import com.example.quruqmeva.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class SotilganSana extends AppCompatActivity {

    List<SotilganYuklar> arrayItems2 = null;

    int summa;
    KunlikSotilgan adapter;

    TextView textView;
    RecyclerView recyclerView;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kunlik_sotilgan_yukalar);

        arrayItems2=new ArrayList<SotilganYuklar>();


        textView=findViewById(R.id.textView);
        recyclerView=findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL,false));

        arrayItems2 = (ArrayList<SotilganYuklar>) getIntent().getSerializableExtra("mylist");
        String tek= (String) getIntent().getSerializableExtra("tek");
        for (int i = 0; i < arrayItems2.size(); i++) {
            System.out.println((arrayItems2.get(i).getId()+". "+arrayItems2.get(i).getKlient_nomi()+"( "+arrayItems2.get(i).getSumma()+" ) "));

            summa+=Integer.parseInt(arrayItems2.get(i).getSumma());
        }
        Kerakli kerakli=new Kerakli();
        assert tek != null;
        if (tek.equals("olingan")){
            textView.setText("Kunlik olingan yuklar- "+kerakli.summa(String.valueOf(summa))+" so'm");
        }
        else if (tek.equals("sotilgan")){
            textView.setText("Kublik sotilgan yuklar- "+kerakli.summa(String.valueOf(summa))+" so'm");
        }
        adapter=new KunlikSotilgan(getApplicationContext(),arrayItems2);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

}