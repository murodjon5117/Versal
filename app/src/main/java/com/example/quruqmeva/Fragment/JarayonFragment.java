package com.example.quruqmeva.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Toast;

import com.chivorn.smartmaterialspinner.SmartMaterialSpinner;
import com.example.quruqmeva.Activity.PulBerish;
import com.example.quruqmeva.Activity.YukOlish;
import com.example.quruqmeva.Activity.YukSotish;
import com.example.quruqmeva.MainActivity;
import com.example.quruqmeva.R;

import java.util.ArrayList;
import java.util.List;

public class JarayonFragment extends Fragment implements View.OnClickListener {

    public JarayonFragment() {

    }

    Button sotish,olish,berish,pulolish;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root= inflater.inflate(R.layout.fragment_jarayon, container, false);
       sotish=root.findViewById(R.id.btn_sotish);
       sotish.setOnClickListener(this);
       olish=root.findViewById(R.id.btn_olish);
       olish.setOnClickListener(this);
       berish=root.findViewById(R.id.pulberish);
       berish.setOnClickListener(this);
       pulolish=root.findViewById(R.id.pulolish);
       pulolish.setOnClickListener(this);

        return root;
    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        if (id==R.id.btn_sotish){
            Intent intent=new Intent(getContext(), YukOlish.class);
            startActivity(intent);
        }
        else if (id==R.id.btn_olish){
            Intent intent=new Intent(getContext(), YukSotish.class);
            startActivity(intent);
        }else if (id==R.id.pulberish){
            Intent intent=new Intent(getContext(), PulBerish.class);
            intent.putExtra("list", "berish");
            intent.putExtra("url", "http://128.199.96.180/api/First/pul_berish/");
            startActivity(intent);
        }else if (id==R.id.pulolish){
            Intent intent=new Intent(getContext(), PulBerish.class);
            intent.putExtra("list", "olish");
            intent.putExtra("url", "http://128.199.96.180/api/First/pul_olish/");
            startActivity(intent);
        }
    }
}