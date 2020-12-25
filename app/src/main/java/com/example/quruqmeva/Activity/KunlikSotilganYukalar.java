package com.example.quruqmeva.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.quruqmeva.Adapter.KunlikSotilgan;
import com.example.quruqmeva.Kerakli;
import com.example.quruqmeva.Modellar.Klient;
import com.example.quruqmeva.Modellar.SotilganYuklar;
import com.example.quruqmeva.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class KunlikSotilganYukalar extends AppCompatActivity {

    int summa1;
    TextView textView;
    KunlikSotilgan adapter;

    List<SotilganYuklar> list4;

    RecyclerView recyclerView;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kunlik_sotilgan_yukalar);
        textView=findViewById(R.id.textView);

        list4=new ArrayList<SotilganYuklar>();

        recyclerView=findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL,false));

        try {
            load();
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
    public void load() throws JSONException {
        Date c = Calendar.getInstance().getTime();
        System.out.println("Current time => " + c);

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        String formattedDate = df.format(c);
        System.out.println(formattedDate);
        AndroidNetworking.post("http://128.199.96.180/api/First/qidirish/")
                .addBodyParameter("vaqt1", formattedDate)
                .addBodyParameter("vaqt2", formattedDate)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onResponse(JSONObject response) {
                        JSONArray jsonArray1 = null;
                        try {
                            jsonArray1 = response.getJSONArray("d2");
                            for (int i = 0; i < jsonArray1.length(); i++) {
                                JSONObject jsonObject = jsonArray1.getJSONObject(i);
                                int id = jsonObject.getInt("id");
                                String sana=jsonObject.getString("sana");
                                String miqdor=jsonObject.getString("miqdor");
                                int narx=jsonObject.getInt("narx");
                                int summa=jsonObject.getInt("summa");

                                JSONObject klient = jsonObject.getJSONObject("klient");
                                int klient_id=klient.getInt("id");
                                String klient_nomi=klient.getString("nomi");
                                String klient_tel=klient.getString("telefon");

                                JSONObject klient_turi = klient.getJSONObject("turi");
                                String klinet_tur_nomi=klient_turi.getString("nomi");

                                JSONObject mahsulot = jsonObject.getJSONObject("mahsulot");
                                String mahsulot_nomi=mahsulot.getString("nomi");

                                JSONObject mahsulot_turi = mahsulot.getJSONObject("turi");
                                String mahsulot_tur_nomi=mahsulot_turi.getString("nomi");

                                SotilganYuklar sotilganYuklar = new SotilganYuklar(id,klient_nomi,klient_tel,mahsulot_nomi,mahsulot_tur_nomi, sana,miqdor,String.valueOf(narx),String.valueOf(summa));
                                list4.add(sotilganYuklar);
                            }
                            adapter=new KunlikSotilgan(getApplicationContext(),list4);
                            recyclerView.setAdapter(adapter);
                            adapter.notifyDataSetChanged();



                            Kerakli kerakli=new Kerakli();
                            textView.setText("Kunlik olingan yuklar- "+kerakli.summa(String.valueOf(summa1))+" so'm");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }

                    @Override
                    public void onError(ANError anError) {
                        System.out.println(anError.getErrorBody());
                    }
                });


    }

}