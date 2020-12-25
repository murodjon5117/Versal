package com.example.quruqmeva.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.chivorn.smartmaterialspinner.SmartMaterialSpinner;
import com.example.quruqmeva.Adapter.KunlikSotilgan;
import com.example.quruqmeva.Adapter.SavdoAdapter;
import com.example.quruqmeva.Modellar.Klient;
import com.example.quruqmeva.Modellar.Savdo;
import com.example.quruqmeva.Modellar.Yukchi;
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
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class YukSotish extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    SavdoAdapter adapter;
    RecyclerView recyclerView;
    EditText miqdor,narx;
    Button tasdiq;
    Button tasdiqlash;
    boolean tek=false;
    int summa1=0;
    int check=1;
    boolean tek1=false;
    int mahsulot_id,klinet_id;
    String mahsulot_nomi,klinet_nomi;
    private SmartMaterialSpinner spProvince1;
    private SmartMaterialSpinner spProvince;
    List<Yukchi> arrayItems = null;
    private List<String> provinceList;
    List<Klient> arrayItems1 = null;
    List<Savdo> list;
    TextView textView;
    private List<String> provinceList1;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yuk_sotish);
        textView=findViewById(R.id.title1);
        textView.setText("Yuk olish");
        tasdiqlash=findViewById(R.id.tasdiqlash);
        tasdiqlash.setOnClickListener(this);
        final CheckedTextView checkedTextView1 = findViewById(R.id.simpleCheckedTextView);
        checkedTextView1.setChecked(true);
        checkedTextView1.setCheckMarkDrawable(R.drawable.ic_baseline_check_circle);

        checkedTextView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkedTextView1.toggle();
                if(checkedTextView1.isChecked()){
                    check=1;
                    checkedTextView1.setCheckMarkDrawable(R.drawable.ic_baseline_check_circle);
                }
                else {
                    check=0;
                    checkedTextView1.setCheckMarkDrawable(null);
                }
            }
        });
        recyclerView=findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL,false   ));

        AndroidNetworking.initialize(getApplicationContext());
        list=new ArrayList<Savdo>();
        miqdor=findViewById(R.id.miqdor);
        miqdor.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                /*String l= String.valueOf(narx.getText());
                int j= Integer.parseInt(l.replace(",",""));

                int summa=j*Integer.parseInt(String.valueOf(miqdor.getText()));*/

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (miqdor.isFocused() && isSet(miqdor) && isSet(narx)) {
                    updateMultiply();
                }
                          }

            @SuppressLint("SetTextI18n")
            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        tasdiq=findViewById(R.id.tasdiq);
        tasdiq.setOnClickListener(this);
        narx=findViewById(R.id.narx);
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


        spProvince = findViewById(R.id.spinner1);
        spProvince.setHint("Yukchini tanlash");
        spProvince1 = findViewById(R.id.spinner3);

        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("save3", Context.MODE_PRIVATE);
        String serializedObject = sharedPreferences.getString("save3", null);
        if (serializedObject != null) {
            Gson gson = new Gson();
            Type type = new TypeToken<List<Yukchi>>(){}.getType();
            arrayItems = gson.fromJson(serializedObject, type);
        }

        SharedPreferences sharedPreferences1 = getApplicationContext().getSharedPreferences("save1", Context.MODE_PRIVATE);
        String serializedObject1 = sharedPreferences1.getString("save1", null);
        if (serializedObject1 != null) {
            Gson gson1 = new Gson();
            Type type1 = new TypeToken<List<Klient>>(){}.getType();
            arrayItems1 = gson1.fromJson(serializedObject1, type1);
        }
        provinceList = new ArrayList<>();

        for (int i = 0; i < arrayItems.size(); i++) {
            provinceList.add(arrayItems.get(i).getId()+". "+arrayItems.get(i).getNomi());
        }
        spProvince.setOnItemSelectedListener(this);
        spProvince.setItem(provinceList);


        provinceList1 = new ArrayList<>();

        for (int i = 0; i < arrayItems1.size(); i++) {
            provinceList1.add(arrayItems1.get(i).getId()+". "+arrayItems1.get(i).getNomi()+"( "+arrayItems1.get(i).getTur_nomi()+" ) ");
        }
        spProvince1.setOnItemSelectedListener(this);
        spProvince1.setItem(provinceList1);

    }


    @SuppressLint("SetTextI18n")
    private void updateMultiply(){
        int weight = Integer.parseInt(narx.getText().toString().replaceAll(",",""));
        int rate = Integer.parseInt(miqdor.getText().toString());
        String originalString = String.valueOf(weight * rate).toString();

        Long longval;
        if (originalString.contains(",")) {
            originalString = originalString.replaceAll(",", "");
        }
        longval = Long.parseLong(originalString);

        DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance(Locale.US);
        formatter.applyPattern("#,###,###,###");
        String formattedString = formatter.format(longval);

        tasdiq.setText(formattedString+" so'm");

    }

    @SuppressLint("SetTextI18n")
    private boolean isSet(EditText editText){
        tasdiq.setText("Qo'shish");
        return !editText.getText().toString().matches("");
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        SmartMaterialSpinner spin = (SmartMaterialSpinner)parent;
        SmartMaterialSpinner spin2 = (SmartMaterialSpinner)parent;
        if(spin.getId() == R.id.spinner1)
        {

            tek1=true;
            klinet_id=position;
            System.out.println(klinet_id);

        }

        else if (spin2.getId()==R.id.spinner3){
            tek=true;
            narx.setText(arrayItems1.get(position).getTel());
            mahsulot_id=position;

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

    @SuppressLint("SetTextI18n")
    public void Test() throws JSONException {


            JSONArray payloadItems = new JSONArray();

            String m= String.valueOf(narx.getText());
            int s= Integer.parseInt(m.replaceAll(",",""));

            int summa=s*Integer.parseInt(String.valueOf(miqdor.getText()));
            JSONObject js = new JSONObject();
            try {
                js.put("y_id",arrayItems.get(klinet_id).getId() );
                js.put("m_id", arrayItems1.get(mahsulot_id).getId());
                js.put("narx", s);
                js.put("miqdor", Integer.parseInt(String.valueOf(miqdor.getText())));
                js.put("ispay", check);
                js.put("tolov", summa);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            payloadItems.put(js);

            list.add(0,new Savdo(js.getInt("y_id"),js.getInt("m_id"),js.getInt("narx"),js.getDouble("miqdor"),js.getInt("tolov"),js.getInt("ispay"),arrayItems.get(klinet_id).getNomi(),arrayItems1.get(mahsulot_id).getNomi()));

            adapter=new SavdoAdapter(getApplicationContext(), list, new SavdoAdapter.MyViewHolder.OnLongClick() {
                @Override
                public void OnItemLongClick(Savdo model, int position) {
                    for (int i=0;i<list.size();i++){
                        summa1=summa1+list.get(i).getTolov();
                        System.out.println(list.get(i).getTolov());
                    }
                    String originalString = String.valueOf(summa1).toString();
                    Long longval;
                    if (originalString.contains(",")) {
                        originalString = originalString.replaceAll(",", "");
                    }
                    longval = Long.parseLong(originalString);
                    DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance(Locale.US);
                    formatter.applyPattern("#,###,###,###");
                    String formattedString = formatter.format(longval);
                    tasdiqlash.setText(formattedString+" so'm");
                    summa1=0;
                }
            });
            recyclerView.setAdapter(adapter);
            adapter.notifyItemInserted(0);
        for (int i=0;i<list.size();i++){
            summa1=summa1+list.get(i).getTolov();
            System.out.println(list.get(i).getTolov());
        }
        String originalString = String.valueOf(summa1).toString();
        Long longval;
        if (originalString.contains(",")) {
            originalString = originalString.replaceAll(",", "");
        }
        longval = Long.parseLong(originalString);

        DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance(Locale.US);
        formatter.applyPattern("#,###,###,###");
        String formattedString = formatter.format(longval);
        tasdiqlash.setText(formattedString+" so'm");
        summa1=0;
        spProvince1.setSelection(-1);
        tek=false;
        narx.setText("Narxi(so'm)");
        miqdor.setText("");

    }
    @Override
    public void onClick(View v) {
        int id=v.getId();
        if (id==R.id.tasdiq){
            if (tek&&tek1&&miqdor.getText().length()>0){
                try {
                    Test();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            else {
                Toast.makeText(this, "Yukchi yoki Mahsulotni tanlang!", Toast.LENGTH_SHORT).show();
            }
        }
        else if (id==R.id.tasdiqlash){
            if (tek1&&list.size()>0){
                Tasdiqlash();
            }
        }
    }

    public void Tasdiqlash(){
        JSONArray jsonArray = new JSONArray();

        for (int i=0; i<list.size();i++){
            JSONObject js = new JSONObject();
            try {
                js.put("y_id",list.get(i).getYukchi_id());
                js.put("m_id", list.get(i).getMahsulot_id());
                js.put("narx", list.get(i).getNarx());
                js.put("miqdor", list.get(i).getMiqdor());
                js.put("ispay", check);
                js.put("tolov", list.get(i).getTolov());

            } catch (JSONException e) {
                e.printStackTrace();
            }
            jsonArray.put(js);
        }

        final String k=jsonArray.toString();
        System.out.println(k);

        StringRequest request=new StringRequest(Request.Method.POST,  "http://128.199.96.180/api/First/saveombor1/", new Response.Listener<String>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(String response) {
                Toast.makeText(YukSotish.this, "Yuk olish muvaffaqiyatli amalga oshirildi", Toast.LENGTH_SHORT).show();
                list.clear();
                tasdiqlash.setText("Tasdiqlash");
                adapter=new SavdoAdapter(getApplicationContext(),list);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                spProvince.setSelection(-1);
                tek1=false;

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error.toString());
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<String, String>();
                params.put("all", k);
                return params;
            }
        };

        RequestQueue queue= Volley.newRequestQueue(getApplicationContext());
        queue.add(request);


    }

}