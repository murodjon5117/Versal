package com.example.quruqmeva.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.chivorn.smartmaterialspinner.SmartMaterialSpinner;
import com.example.quruqmeva.Kerakli;
import com.example.quruqmeva.Modellar.Klient;
import com.example.quruqmeva.Modellar.SotilganYuklar;
import com.example.quruqmeva.Modellar.Yukchi;
import com.example.quruqmeva.Modellar.mahsulotOmbor;
import com.example.quruqmeva.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class PulBerish extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    String nom;
    int id;
    TextView textView;
    private SmartMaterialSpinner spProvince;
    List<Yukchi> arrayItems = null;
    List<Klient> arrayItems1 = null;

    int klinet_id;
    Button tasdiqlash;
    EditText narx,izoh;
    String url,list;
    private List<String> provinceList;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pul_berish);
         list = (String) getIntent().getSerializableExtra("list");
         url = (String) getIntent().getSerializableExtra("url");

         textView=findViewById(R.id.textView2);

        spProvince = findViewById(R.id.spinner1);


        izoh=findViewById(R.id.izoh);

        if (list.equals("berish")){

            spProvince.setHint("Yukchini tanlash");
            textView.setText("Pul berish oynasi");

            SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("save3", Context.MODE_PRIVATE);
            String serializedObject = sharedPreferences.getString("save3", null);
            if (serializedObject != null) {
                Gson gson = new Gson();
                Type type = new TypeToken<List<Yukchi>>(){}.getType();
                arrayItems = gson.fromJson(serializedObject, type);
            }
            provinceList = new ArrayList<>();

            for (int i = 0; i < arrayItems.size(); i++) {
                provinceList.add(arrayItems.get(i).getId()+". "+arrayItems.get(i).getNomi());
            }
            spProvince.setOnItemSelectedListener(this);
            spProvince.setItem(provinceList);
        }
        else if (list.equals("olish")){
            spProvince.setHint("Klientni tanlash");
            textView.setText("Pul olish oynasi");
            SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("save", Context.MODE_PRIVATE);
            String serializedObject = sharedPreferences.getString("save", null);
            if (serializedObject != null) {
                Gson gson = new Gson();
                Type type = new TypeToken<List<Klient>>(){}.getType();
                arrayItems1 = gson.fromJson(serializedObject, type);
            }
            provinceList = new ArrayList<>();

            for (int i = 0; i < arrayItems1.size(); i++) {
                provinceList.add(arrayItems1.get(i).getId()+". "+arrayItems1.get(i).getNomi());
            }
            spProvince.setOnItemSelectedListener(this);
            spProvince.setItem(provinceList);
        }


        narx=findViewById(R.id.miqdor);
        narx.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @SuppressLint("SetTextI18n")
            @Override
            public void afterTextChanged(Editable editable) {

                narx.removeTextChangedListener(this);

                try {
                    String originalString = editable.toString();

                    Long longval;
                    if (originalString.contains(",")) {
                        originalString = originalString.replaceAll(",", "");
                    }
                    longval = Long.parseLong(originalString);

                    DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance(Locale.US);
                    formatter.applyPattern("#,###,###,###");
                    String formattedString = formatter.format(longval);

                    narx.setText(formattedString);
                    narx.setSelection(narx.getText().length());
                } catch (NumberFormatException nfe) {
                    nfe.printStackTrace();
                }

                narx.addTextChangedListener(this);
            }
        });
        tasdiqlash=findViewById(R.id.tasdiqlash);
        tasdiqlash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Tasdiqlash();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        SmartMaterialSpinner spin = (SmartMaterialSpinner)parent;
        if(spin.getId() == R.id.spinner1)
        {
            klinet_id=position;
            System.out.println(klinet_id);

        }

    }
    public void Tasdiqlash() throws JSONException {

        if (list.equals("olish")){
             nom="klient";
            id=arrayItems1.get(klinet_id).getId();
        }else if (list.equals("berish")){
             nom="yukchi";
            id=arrayItems.get(klinet_id).getId();
        }
        AndroidNetworking.post(url)
                .addBodyParameter(nom, String.valueOf(id))
                .addBodyParameter("summa", String.valueOf(Integer.parseInt(narx.getText().toString().replaceAll(",",""))))
                .addBodyParameter("izoh", String.valueOf(izoh.getText()))
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        izoh.setText("");
                        narx.setText("");
                        spProvince.setSelection(-1);
                        Toast.makeText(PulBerish.this, "Muvaffaqiyatli yakunlandi", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(ANError anError) {
                        System.out.println(anError.toString());
                    }
                });
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

    @Override
    public void onClick(View v) {

    }
}