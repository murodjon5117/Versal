package com.example.quruqmeva.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Base64;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.quruqmeva.Adapter.OmborAdapter;
import com.example.quruqmeva.Modellar.mahsulotOmbor;
import com.example.quruqmeva.R;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Ombor extends AppCompatActivity {

    RecyclerView recyclerView;
    SearchView editText;
    TextView textView;
    OmborAdapter myAdapter;
    List<mahsulotOmbor> headlineModeList;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ombor);
        textView=findViewById(R.id.textView2);
        textView.setText("Ombor");
        editText=findViewById(R.id.editTextSearch2);
        editText.setIconifiedByDefault(false);
        editText.setFocusable(true);
        editText.setIconified(true);
        editText.requestFocusFromTouch();
        recyclerView=findViewById(R.id.catigory_search_rec);
        headlineModeList=new ArrayList<>();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL,false));
        myAdapter=new OmborAdapter(getApplicationContext(),headlineModeList);
        editText.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                myAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                myAdapter.getFilter().filter(newText);
                return false;
            }
        });
        Load();

    }
    public void Load(){
        JsonArrayRequest jsonObjectRequest=new JsonArrayRequest(Request.Method.GET, "http://128.199.96.180/api/First/ombor/", null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                try {
                    for (int i=0;i<response.length();i++){
                        JSONObject jsonObject=response.getJSONObject(i);
                        double miqdori=jsonObject.getDouble("miqdor");
                        JSONObject mahsulo=jsonObject.getJSONObject("mahsulot");
                        String mahsulot=mahsulo.getString("nomi");
                        int narxi=mahsulo.getInt("narxi");
                        JSONObject turi=mahsulo.getJSONObject("turi");
                        String turi_nomi=turi.getString("nomi");

                        mahsulotOmbor headlineModel= new mahsulotOmbor(mahsulot,turi_nomi,narxi,miqdori);
                        headlineModeList.add(headlineModel);
                    }
                    recyclerView.setAdapter(myAdapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                myAdapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error.toString());
            }

        });
        RequestQueue queue= Volley.newRequestQueue(getApplicationContext());
        queue.add(jsonObjectRequest);
    }
}