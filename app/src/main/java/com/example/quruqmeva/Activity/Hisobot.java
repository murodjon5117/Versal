package com.example.quruqmeva.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.quruqmeva.Modellar.Qarizdorlik;
import com.example.quruqmeva.Modellar.SotilganYuklar;
import com.example.quruqmeva.Modellar.mahsulotOmbor;
import com.example.quruqmeva.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Hisobot extends AppCompatActivity implements View.OnClickListener {

    Button sotuv,oluv,pulber,pulol;
    String date;
    List<SotilganYuklar> list4;
    List<SotilganYuklar> list2;
    List<Qarizdorlik> list5;
    List<Qarizdorlik> list6;
    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hisobot);
        textView=findViewById(R.id.textView2);
        list4=new ArrayList<SotilganYuklar>();
        list2=new ArrayList<SotilganYuklar>();
        list5=new ArrayList<Qarizdorlik>();
        list6=new ArrayList<Qarizdorlik>();

        sotuv=findViewById(R.id.sotilgan1);
        oluv=findViewById(R.id.olingan1);
        sotuv.setOnClickListener(this);
        oluv.setOnClickListener(this);
        pulber=findViewById(R.id.pulber);
        pulol=findViewById(R.id.pulol);
        pulol.setOnClickListener(this);
        pulber.setOnClickListener(this);

        Intent iin= getIntent();
        Bundle b = iin.getExtras();
        if(b!=null)
        {
            int yil =(int) b.get("yil");
            int oy =(int) b.get("oy");
            int kun =(int) b.get("kun");
            date=yil+"-"+oy+"-"+kun;
            textView.setText(date);
        }

        try {
            load();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
    public void load() throws JSONException {

        AndroidNetworking.post("http://128.199.96.180/api/First/qidirish/")
                .addBodyParameter("vaqt1", date)
                .addBodyParameter("vaqt2", date)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        JSONArray jsonArray = null;
                        try {
                            jsonArray = response.getJSONArray("d1");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                int id = jsonObject.getInt("id");
                                String sana=jsonObject.getString("sana");
                                String miqdor=jsonObject.getString("miqdor");
                                int narx=jsonObject.getInt("narx");
                                int summa=jsonObject.getInt("summa");

                                JSONObject klient = jsonObject.getJSONObject("yukchi");
                                int klient_id=klient.getInt("id");
                                String klient_nomi=klient.getString("nomi");
                                String klient_tel=klient.getString("telefon");

                                JSONObject mahsulot = jsonObject.getJSONObject("mahsulot");
                                String mahsulot_nomi=mahsulot.getString("nomi");

                                JSONObject mahsulot_turi = mahsulot.getJSONObject("turi");
                                String mahsulot_tur_nomi=mahsulot_turi.getString("nomi");

                                SotilganYuklar sotilganYuklar = new SotilganYuklar(id,klient_nomi,klient_tel,mahsulot_nomi,mahsulot_tur_nomi, sana,miqdor,String.valueOf(narx),String.valueOf(summa));
                                list4.add(sotilganYuklar);
                            }
                            System.out.println("Boldii!!!");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
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
                                        list2.add(sotilganYuklar);
                            }
                            System.out.println("Boldii11!!!");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        JSONArray jsonArray2 = null;
                        try {
                            jsonArray2 = response.getJSONArray("d4");
                            for (int i = 0; i < jsonArray2.length(); i++) {
                                JSONObject jsonObject = jsonArray2.getJSONObject(i);
                                String sana=jsonObject.getString("sana");
                                int summa=jsonObject.getInt("summa");
                                String izoh=jsonObject.getString("izoh");

                                JSONObject klient = jsonObject.getJSONObject("klient");
                                int klient_id=klient.getInt("id");
                                String klient_nomi=klient.getString("nomi");
                                String klient_tel=klient.getString("telefon");


                                Qarizdorlik sotilganYuklar = new Qarizdorlik(klient_nomi,sana,summa,klient_tel,izoh);
                                list5.add(sotilganYuklar);
                            }
                            System.out.println("Boldii11!!!");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        JSONArray jsonArray3 = null;
                        try {
                            jsonArray3 = response.getJSONArray("d3");
                            for (int i = 0; i < jsonArray3.length(); i++) {
                                JSONObject jsonObject = jsonArray3.getJSONObject(i);
                                String sana=jsonObject.getString("sana");
                                int summa=jsonObject.getInt("summa");
                                String izoh=jsonObject.getString("izoh");

                                JSONObject klient = jsonObject.getJSONObject("yukchi");
                                int klient_id=klient.getInt("id");
                                String klient_nomi=klient.getString("nomi");
                                String klient_tel=klient.getString("telefon");

                                Qarizdorlik sotilganYuklar = new Qarizdorlik(klient_nomi,sana,summa,klient_tel,izoh);
                                list6.add(sotilganYuklar);
                            }
                            System.out.println("Boldii11!!!");
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

    @Override
    public void onClick(View v) {
        int id=v.getId();
        if (id==R.id.olingan1){

            Intent intent=new Intent(this,SotilganSana.class);
            intent.putExtra("mylist", (Serializable) list4);
            intent.putExtra("tek", "olingan");
            startActivity(intent);
        }
        else if (id==R.id.sotilgan1){
            Intent intent=new Intent(this,SotilganSana.class);
            intent.putExtra("mylist", (Serializable) list2);
            intent.putExtra("tek", "sotilgan");
            startActivity(intent);
        }
         else if (id==R.id.pulber){
            Intent intent=new Intent(this,QarzdorlikActivity.class);
            intent.putExtra("mylist", (Serializable) list5);
            intent.putExtra("tek", "klintpulber");
            startActivity(intent);

        } else if (id==R.id.pulol){
            Intent intent=new Intent(this,QarzdorlikActivity.class);
            intent.putExtra("mylist", (Serializable) list6);
            intent.putExtra("tek", "klintpulber");
            startActivity(intent);
        }
    }
}