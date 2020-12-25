package com.example.quruqmeva.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.quruqmeva.Adapter.OmborAdapter;
import com.example.quruqmeva.Adapter.QarizdorlikAdapter;
import com.example.quruqmeva.Modellar.Qarizdorlik;
import com.example.quruqmeva.Modellar.SotilganYuklar;
import com.example.quruqmeva.Modellar.mahsulotOmbor;
import com.example.quruqmeva.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class QarzdorlikActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    SearchView editText;
    TextView textView;
    QarizdorlikAdapter myAdapter;
    List<Qarizdorlik> headlineModeList;
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
        headlineModeList = (ArrayList<Qarizdorlik>) getIntent().getSerializableExtra("mylist");
        String tek= (String) getIntent().getSerializableExtra("tek");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL,false));
        myAdapter=new QarizdorlikAdapter(getApplicationContext(),headlineModeList);
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
        recyclerView.setAdapter(myAdapter);
        myAdapter.notifyDataSetChanged();

    }
}