package com.example.workintergrated;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    private EditText email, password;
    private Button login, newRegPage;
    private TextView info;
    private int counter = 5;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        email = (EditText) findViewById(R.id.etLoginMail);
        password = (EditText) findViewById(R.id.etPassword);
        login = (Button) findViewById(R.id.buttonLogIn);
        newRegPage = (Button) findViewById(R.id.buttonRegPage);
        info = (TextView) findViewById(R.id.txtAttempts);

        firebaseAuth = FirebaseAuth.getInstance();

        //check if user already logged in or not
        FirebaseUser user = firebaseAuth.getCurrentUser();

        info.setText("NUMBER OF ATTEMPTS REMAINING: 5");

        progressDialog = new ProgressDialog(this);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mail1 = email.getText().toString();
                String pass1 = password.getText().toString();

                if(mail1.isEmpty() || pass1.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Please enter Required field!", Toast.LENGTH_SHORT).show();

                }
                else
                    {
                    validate(email.getText().toString(), password.getText().toString());

                }

            }
        });

        newRegPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
            }
        });
    }

    private void validate(String userMail, String userPass)
    {
        progressDialog.setMessage("PLEASE WAIT!!!");
        progressDialog.show();


        firebaseAuth.signInWithEmailAndPassword(userMail, userPass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful())
                {
                    progressDialog.dismiss();
                    Toast.makeText(LoginActivity.this, "LOGIN SUCCESSFUL!!!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginActivity.this, HomeActivity.class));


                }else
                {
                    Toast.makeText(LoginActivity.this, "LOGIN FAILED!!!", Toast.LENGTH_SHORT).show();

                    progressDialog.dismiss();
                    counter--;
                    info.setText("Number of attempts remaining: "+ counter);
                    if(counter == 0)
                    {
                        login.setEnabled(false);
                    }
                }
            }
        });
    }
}