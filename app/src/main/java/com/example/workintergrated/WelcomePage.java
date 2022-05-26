package com.example.workintergrated;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.Navigation;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Gallery;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class WelcomePage extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_activity_main);

        firebaseAuth = FirebaseAuth.getInstance();

        navigationSelect();
    }

    private void navigationSelect() {
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if (item.getItemId() == R.id.nav_gallery) {
                    startActivity(new Intent(WelcomePage.this, galleryAct.class));
                    finish();
                }
                if (item.getItemId() == R.id.nav_contactUs) {
                    startActivity(new Intent(WelcomePage.this, contactUs.class));
                    finish();
                }
                if (item.getItemId() == R.id.nav_question) {
                    startActivity(new Intent(WelcomePage.this, Enquire.class));
                    finish();
                }
                if(item.getItemId() == R.id.nav_payOnline)
                {
                    startActivity(new Intent(WelcomePage.this, PaymentAct.class));
                    finish();
                }
                if(item.getItemId()== R.id.nav_logout)
                {
                    firebaseAuth.signOut();
                    Toast.makeText(WelcomePage.this, "Logged Out", Toast.LENGTH_SHORT).show();

                    finish();
                    startActivity(new Intent(WelcomePage.this, LoginActivity.class));
                }
                DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }
}