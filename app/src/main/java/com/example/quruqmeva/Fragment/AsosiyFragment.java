package com.example.quruqmeva.Fragment;

import android.content.Context;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.example.quruqmeva.Activity.KlientQarz;
import com.example.quruqmeva.Activity.KunlikOlinganYuklar;
import com.example.quruqmeva.Activity.KunlikSotilganYukalar;
import com.example.quruqmeva.Activity.Ombor;
import com.example.quruqmeva.Activity.YukchiHaqi;
import com.example.quruqmeva.Modellar.Klient;
import com.example.quruqmeva.Modellar.SotilganYuklar;
import com.example.quruqmeva.Modellar.Yukchi;
import com.example.quruqmeva.R;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;


public class AsosiyFragment extends Fragment implements Serializable, View.OnClickListener {

    public AsosiyFragment() {
        // Required empty public constructor
    }
    Button sotuv;
    Button olingan;
    Button ombor;
    Button haq;
    Button qarz;
    List<Klient> list;
    List<Klient> list1;
    List<Yukchi> list3;
    List<SotilganYuklar> list2;
    List<SotilganYuklar> list4;
    SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root= inflater.inflate(R.layout.fragment_asosiy, container, false);
        AndroidNetworking.initialize(getContext());
        sotuv=root.findViewById(R.id.sotilgan);
        sotuv.setOnClickListener(this);
        olingan=root.findViewById(R.id.olingan);
        olingan.setOnClickListener(this);
        ombor=root.findViewById(R.id.ombor);
        ombor.setOnClickListener(this);
        haq=root.findViewById(R.id.yukchihaqqi);
        haq.setOnClickListener(this);
        qarz=root.findViewById(R.id.klientqarzi);
        qarz.setOnClickListener(this);
        list=new ArrayList<Klient>();
        list1=new ArrayList<Klient>();
        list2=new ArrayList<SotilganYuklar>();
        list4=new ArrayList<SotilganYuklar>();
        list3=new ArrayList<Yukchi>();
        mSwipeRefreshLayout = root.findViewById(R.id.refresh);



        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Your code here
                Loaddata1();

                // To keep animation for 4 seconds
                new Handler().postDelayed(new Runnable() {
                    @Override public void run() {
                        // Stop animation (This will be after 3 seconds)
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                }, 3000); // Delay in millis
            }
        });
        mSwipeRefreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);


        return root;
    }

    public void Loaddata1(){

        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, "http://128.199.96.180/api/First/all_data/", null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                JSONArray jsonArray = null;


                    jsonArray = response.getJSONArray("Klientlar");

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                    int id = jsonObject.getInt("id");
                    String nomi = jsonObject.getString("nomi");
                    String telefon = jsonObject.getString("telefon");

                    JSONObject turi = jsonObject.getJSONObject("turi");
                    int turi_id = turi.getInt("id");
                    String turi_nomi = turi.getString("nomi");

                    //System.out.println(id+nomi+turi_nomi+telefon);
                    Klient klient = new Klient(id, nomi, telefon, turi_id, turi_nomi);
                    //System.out.println(klient.getNomi());
                    list.add(klient);
                }

                SharedPreferences sharedPreferences = getContext().getSharedPreferences("save", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                Gson gson = new Gson();
                String json = gson.toJson(list);
                editor.putString("save", json);
                editor.apply();

                JSONArray jsonArray1 = null;

                jsonArray1 = response.getJSONArray("Mahsulotlar");
                for (int i = 0; i < jsonArray1.length(); i++) {
                    JSONObject jsonObject = jsonArray1.getJSONObject(i);

                    int id = jsonObject.getInt("id");
                    String nomi = jsonObject.getString("nomi");
                    String narxi = jsonObject.getString("narxi");

                    JSONObject turi = jsonObject.getJSONObject("turi");
                    int turi_id = turi.getInt("id");
                    String turi_nomi = turi.getString("nomi");

                    //System.out.println(id+nomi+turi_nomi+telefon);
                    Klient klient = new Klient(id, nomi, narxi, turi_id, turi_nomi);
                    //System.out.println(klient.getNomi());
                    list1.add(klient);
                }

                SharedPreferences sharedPreferences1 = getContext().getSharedPreferences("save1", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor1 = sharedPreferences1.edit();
                Gson gson1 = new Gson();
                String json1 = gson1.toJson(list1);
                editor1.putString("save1", json1);
                editor1.apply();

                    JSONArray jsonArray3 = null;

                    jsonArray3 = response.getJSONArray("Yukchilar");
                    for (int i = 0; i < jsonArray3.length(); i++) {
                        JSONObject jsonObject = jsonArray3.getJSONObject(i);

                        int id = jsonObject.getInt("id");
                        String nomi=jsonObject.getString("nomi");
                        String tel=jsonObject.getString("telefon");

                        Yukchi sotilganYuklar = new Yukchi(id,nomi,tel);
                        //System.out.println(klient.getNomi());
                        list3.add(sotilganYuklar);
                    }

                    SharedPreferences sharedPreferences3 = getContext().getSharedPreferences("save3", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor3 = sharedPreferences3.edit();
                    Gson gson3 = new Gson();
                    String json3 = gson3.toJson(list3);
                    editor3.putString("save3", json3);
                    editor3.apply();

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }



            }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });


        RequestQueue queue= Volley.newRequestQueue(getContext());
        queue.add(jsonObjectRequest);
    }


    @Override
    public void onClick(View v) {
        int id=v.getId();
        if (id==R.id.sotilgan){
            Intent intent=new Intent(getContext(), KunlikSotilganYukalar.class);
            startActivity(intent);
        }
        else if (id==R.id.olingan){
            Intent intent=new Intent(getContext(), KunlikOlinganYuklar.class);
            startActivity(intent);
        }
        else if (id==R.id.ombor){
            Intent intent=new Intent(getContext(), Ombor.class);
            startActivity(intent);
        }else if (id==R.id.yukchihaqqi){
            Intent intent=new Intent(getContext(), YukchiHaqi.class);
            startActivity(intent);
        }else if (id==R.id.klientqarzi){
            Intent intent=new Intent(getContext(), KlientQarz.class);
            startActivity(intent);
        }
    }
}