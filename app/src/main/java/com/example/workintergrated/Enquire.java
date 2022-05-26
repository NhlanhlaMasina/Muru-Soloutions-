package com.example.workintergrated;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class Enquire<FirebaseFireStore> extends AppCompatActivity {

    EditText etname, etsurname, etnumber, etemail, etmessage;
    Button addData;
    FirebaseAuth firebaseAuth;
   // String url = "http://192.168.42.181/Client_Enquire/add_data.php";

    //DatabaseReference databaseReference;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_activity_main4);

        etname = findViewById(R.id.edName);
        etsurname = findViewById(R.id.edSurname);
        etnumber = findViewById(R.id.edNumber);
        etemail = findViewById(R.id.edEmail);
        etmessage = findViewById(R.id.edMessage);
        addData = findViewById(R.id.buttonEnquire);

        firebaseAuth = FirebaseAuth.getInstance();

       // databaseReference = FirebaseDatabase.getInstance().getReference();
        db = FirebaseFirestore.getInstance();

        addDatabase();
        navigationSelect();

    }

    private void addDatabase()
    {
        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String Name = etname.getText().toString();
                final String Surname = etsurname.getText().toString();
                final String Email = etemail.getText().toString();
                final String Number= etnumber.getText().toString();
                final String Message = etmessage.getText().toString();

                if(!TextUtils.isEmpty(Name) || !TextUtils.isEmpty(Surname) ||!TextUtils.isEmpty(Email) || !TextUtils.isEmpty(Number) || !TextUtils.isEmpty(Message)) {
                    //String id = databaseReference.push().getKey();
                    //   EnquireUser enquireUser = new EnquireUser(id, Name, Surname, Email, Number, Message);
                    //databaseReference.child(id).setValue(enquireUser);
                    CollectionReference dbUserBooking = db.collection("Enquire");
                    EnquireUser enquireUser1 = new EnquireUser(Name, Surname, Email, Number, Message);

                    dbUserBooking.add(enquireUser1).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Toast.makeText(Enquire.this, "Message Sent", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(Enquire.this, WelcomePage.class));
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(Enquire.this, "Message Sent", Toast.LENGTH_SHORT).show();

                        }
                    });


                }else{
                    Toast.makeText(Enquire.this, "Enter All Fields", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }


    private void navigationSelect() {
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if (item.getItemId() == R.id.nav_home) {
                    startActivity(new Intent(Enquire.this, WelcomePage.class));
                    finish();
                }
                if (item.getItemId() == R.id.nav_gallery) {
                    startActivity(new Intent(Enquire.this, galleryAct.class));
                    finish();
                }

                if (item.getItemId() == R.id.nav_contactUs) {
                    startActivity(new Intent(Enquire.this, contactUs.class));
                    finish();
                }
                if(item.getItemId() == R.id.nav_payOnline)
                {
                    startActivity(new Intent(Enquire.this, PaymentAct.class));
                    finish();
                }
                if(item.getItemId()== R.id.nav_logout)
                {
                        firebaseAuth.signOut();
                    Toast.makeText(Enquire.this, "Logged Out", Toast.LENGTH_SHORT).show();

                    finish();
                        startActivity(new Intent(Enquire.this, LoginActivity.class));
                }
                DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });

    }
}

