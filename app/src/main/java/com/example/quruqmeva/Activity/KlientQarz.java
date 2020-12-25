package com.example.quruqmeva.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.quruqmeva.Adapter.HaqqiAdapter;
import com.example.quruqmeva.Kerakli;
import com.example.quruqmeva.Modellar.mahsulotOmbor;
import com.example.quruqmeva.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class KlientQarz extends AppCompatActivity {

    TextView textView;
    RecyclerView recyclerView;
    SearchView editText;
    int summa;
    HaqqiAdapter myAdapter;
    List<mahsulotOmbor> headlineModeList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ombor);
        textView=findViewById(R.id.textView2);
        editText=findViewById(R.id.editTextSearch2);
        editText.setIconifiedByDefault(false);
        editText.setFocusable(true);
        editText.setIconified(true);
        editText.requestFocusFromTouch();
        recyclerView=findViewById(R.id.catigory_search_rec);
        headlineModeList=new ArrayList<>();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL,false));
        myAdapter=new HaqqiAdapter(getApplicationContext(),headlineModeList);
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
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, "http://128.199.96.180/api/First/qarzdorlik/", null, new Response.Listener<JSONObject>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("klientlar");
                    for (int i=0;i<jsonArray.length();i++){
                        JSONObject jsonObject=jsonArray.getJSONObject(i);
                        String nomi= jsonObject.getString("nomi");
                        String telefon= jsonObject.getString("telefon");
                        int qarz=jsonObject.getInt("qarz");
                        summa+=qarz;
                        mahsulotOmbor headlineModel= new mahsulotOmbor(nomi,telefon,qarz,0);
                        headlineModeList.add(headlineModel);
                    }
                    Kerakli kerakli=new Kerakli();
                    textView.setText("Sizdan jami qarizlar - "+kerakli.summa(String.valueOf(summa))+" so'm");
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