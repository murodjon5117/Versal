package com.example.quruqmeva;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.quruqmeva.Fragment.AsosiyFragment;
import com.example.quruqmeva.Fragment.AzolarFragment;
import com.example.quruqmeva.Fragment.HisobotFragment;
import com.example.quruqmeva.Fragment.JarayonFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private long    backPressedTime;
    private Toast backToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNav=findViewById(R.id.bottomNavView);

        bottomNav.setOnNavigationItemSelectedListener(navListner);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,new AsosiyFragment()).commit();

    }
    @Override
    public void onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            backToast.cancel();
            finish();
            return;
        }else {
            backToast = Toast.makeText(getBaseContext(), "Chiqish uchun ikki marta bosing", Toast.LENGTH_LONG);
            backToast.show();
        }
        backPressedTime = System.currentTimeMillis();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListner=new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment=null;
            switch (item.getItemId()){
                case R.id.homeFragment:
                    selectedFragment=new AsosiyFragment();
                    break;
                case R.id.searchFragment:
                    selectedFragment=new JarayonFragment();
                    break;
                /*case R.id.speakerFragment:
                    selectedFragment=new AzolarFragment();
                    break;*/
                case R.id.accountFragment:
                    selectedFragment=new HisobotFragment();
                    break;

            }
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,
                    selectedFragment).commit();
            return true;
        }
    };
}