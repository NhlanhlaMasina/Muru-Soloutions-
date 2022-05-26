package com.example.workintergrated;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class contactUs extends AppCompatActivity {
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_activity_main3);

        firebaseAuth = FirebaseAuth.getInstance();
        navigationSelect();
    }
    private void navigationSelect() {
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if (item.getItemId() == R.id.nav_home) {
                    startActivity(new Intent(contactUs.this, WelcomePage.class));
                    finish();
                }
                if (item.getItemId() == R.id.nav_gallery) {
                    startActivity(new Intent(contactUs.this, galleryAct.class));
                    finish();
                }

                if (item.getItemId() == R.id.nav_question) {
                    startActivity(new Intent(contactUs.this, Enquire.class));
                    finish();
                }
                if(item.getItemId() == R.id.nav_payOnline)
                {
                    startActivity(new Intent(contactUs.this, PaymentAct.class));
                    finish();
                }
                if(item.getItemId()== R.id.nav_logout)
                {
                    firebaseAuth.signOut();
                    finish();
                    startActivity(new Intent(contactUs.this, LoginActivity.class));
                }
                DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });

    }

}