package com.example.quruqmeva.Fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;

import com.example.quruqmeva.Activity.Hisobot;
import com.example.quruqmeva.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;


public class HisobotFragment extends Fragment {


    public HisobotFragment() {
        // Required empty public constructor
    }
    CalendarView calendarView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root=inflater.inflate(R.layout.fragment_hisobot, container, false);
        calendarView=root.findViewById(R.id.calendarView);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month,
                                            int dayOfMonth) {
                Intent k = new Intent(getContext(), Hisobot.class);
                k.putExtra("yil", year);
                k.putExtra("oy", month+1);
                k.putExtra("kun", dayOfMonth);
                startActivity(k);
            }
        });

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getContext());
        Gson gson = new Gson();
        String json = prefs.getString("key", null);
        Type type = new TypeToken<ArrayList<String>>() {}.getType();
        ArrayList<String> response=gson.fromJson(json, type);
        System.out.println(response);

        return root;
    }
}