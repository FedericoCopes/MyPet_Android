package com.example.navbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

public class MainActivity extends AppCompatActivity {

    TextView TxtnomeAnimal, TxtrazzaAnimale, TxtcoloreAnimale, TxtSessoAnimale, TxtdataNasictaAnimale, TxtpesoAnimale, TxtTipologiaAnimale;
    ChipNavigationBar chipNavigationBar;
    String nomeUtente;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chipNavigationBar = findViewById(R.id.bottom_nav_bar);
        chipNavigationBar.setItemSelected(R.id.nav_home,true);


        //getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            nomeUtente = extras.getString("nomeutente");
        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction  fragm = fragmentManager.beginTransaction();
        Bundle bundle = new Bundle();
        bundle.putString("nomeutente", nomeUtente);
        HomeFragment home = new HomeFragment();
        home.setArguments(bundle);
        fragm.replace(R.id.fragment_container, home).commit();
        chipNavigationBar.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int i) {
                Fragment fragment = null;
                switch (i){
                    case R.id.nav_menu:
                        FragmentManager menuFragmet = getSupportFragmentManager();
                        FragmentTransaction fragmenu = menuFragmet.beginTransaction();
                        Bundle bundlemenu = new Bundle();
                        bundlemenu.putString("nomeutente", nomeUtente);
                        MenuFragment menu = new MenuFragment();
                        menu.setArguments(bundlemenu);
                        fragmenu.replace(R.id.fragment_container, menu).commit();
                        break;
                    case R.id.nav_home:;
                        FragmentManager fragmentManager = getSupportFragmentManager();
                        FragmentTransaction  fragm = fragmentManager.beginTransaction();
                        Bundle bundle = new Bundle();
                        bundle.putString("nomeutente", nomeUtente);
                        HomeFragment home = new HomeFragment();
                        home.setArguments(bundle);
                        fragm.replace(R.id.fragment_container, home).commit();
                        break;
                    case R.id.nav_map:
                        MapFragment mapFragment = new MapFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,mapFragment).commit();
                        break;
                }

            }
        });
    }

}