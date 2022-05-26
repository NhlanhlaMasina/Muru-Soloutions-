package com.example.workintergrated;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class galleryAct extends AppCompatActivity {

        GridView gridView;
        String[] pictureNumber = {"1", "2", "3", "4", "5", "6", "7", "8"};
        int[] picture = {R.drawable.one, R.drawable.two, R.drawable.three, R.drawable.four, R.drawable.five, R.drawable.six, R.drawable.seven, R.drawable.eight};
        FirebaseAuth firebaseAuth;
@Override
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_activity_main2);

        gridView = findViewById(R.id.grid_view);

        firebaseAuth = FirebaseAuth.getInstance();

        MainAdapter adapter = new MainAdapter(galleryAct.this, pictureNumber, picture);
        gridView.setAdapter(adapter);
        navigationSelect();

        }

private void navigationSelect() {
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
@Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.nav_home) {
        startActivity(new Intent(galleryAct.this, WelcomePage.class));
                finish();
        }
        if (item.getItemId() == R.id.nav_gallery) {
        startActivity(new Intent(galleryAct.this, galleryAct.class));
                finish();
        }
        if (item.getItemId() == R.id.nav_contactUs) {
        startActivity(new Intent(galleryAct.this, contactUs.class));
                finish();
        }
        if (item.getItemId() == R.id.nav_question) {
        startActivity(new Intent(galleryAct.this, Enquire.class));
                finish();
        }
        if(item.getItemId() == R.id.nav_payOnline)
        {
                startActivity(new Intent(galleryAct.this, PaymentAct.class));
                finish();
        }
        if(item.getItemId()== R.id.nav_logout)
        {
                firebaseAuth.signOut();
                Toast.makeText(galleryAct.this, "Logged Out", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(galleryAct.this, LoginActivity.class));
                finish();
        }
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
        }
        });

    }
}