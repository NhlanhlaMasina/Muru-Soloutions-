package com.example.workintergrated;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class PaymentAct extends AppCompatActivity {
    ImageView reno, paint, buildCon;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_activity_main5);
        navigationSelect();

        firebaseAuth = FirebaseAuth.getInstance();

        reno = findViewById(R.id.renovationPrice);
        paint = findViewById(R.id.paintingPrice);
        buildCon = findViewById(R.id.buildingPrice);

        reno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PaymentAct.this, RenovationPay.class));
            }
        });

        paint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PaymentAct.this, PaintPay.class));
            }
        });

        buildCon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PaymentAct.this, BuildingPay.class));
            }
        });


    }

    private void navigationSelect() {
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if (item.getItemId() == R.id.nav_home) {
                    startActivity(new Intent(PaymentAct.this, WelcomePage.class));
                    finish();
                }
                if (item.getItemId() == R.id.nav_gallery) {
                    startActivity(new Intent(PaymentAct.this, galleryAct.class));
                    finish();
                }

                if (item.getItemId() == R.id.nav_question) {
                    startActivity(new Intent(PaymentAct.this, Enquire.class));
                    finish();
                }
                if(item.getItemId() == R.id.nav_contactUs)
                {
                    startActivity(new Intent(PaymentAct.this, contactUs.class));
                    finish();
                }
                if(item.getItemId()== R.id.nav_logout)
                {
                    firebaseAuth.signOut();
                    Toast.makeText(PaymentAct.this, "Logged Out", Toast.LENGTH_SHORT).show();

                    finish();
                    startActivity(new Intent(PaymentAct.this, LoginActivity.class));
                }
                DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });

    }

}