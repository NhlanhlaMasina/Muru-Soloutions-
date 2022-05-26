package com.example.workintergrated;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private EditText name, mail, password;
    private Button register, back;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        setUIViews();

        //get instance of the Authentication
        firebaseAuth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(this);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                if(validate())
                {
                    //upload data to database
                    String userMail = mail.getText().toString().trim();
                    String userPass = password.getText().toString().trim();

                    progressDialog.setMessage("PLEASE WAIT!!!");
                    progressDialog.show();

                    firebaseAuth.createUserWithEmailAndPassword(userMail, userPass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                            {
                                progressDialog.dismiss();
                                Toast.makeText(MainActivity.this,"Registration Successful",Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                                finish();

                            }else
                            {
                                progressDialog.dismiss();
                                Toast.makeText(MainActivity.this,"Registration Failed",Toast.LENGTH_SHORT).show();


                            }
                        }
                    });
                }

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));

            }
        });

    }

    private void setUIViews()
    {
        name = (EditText) findViewById(R.id.etUserName);
        mail = (EditText) findViewById(R.id.etUserEmail);
        password = (EditText) findViewById(R.id.etUserPassword);
        register = (Button) findViewById(R.id.buttonRegister);
        back = (Button) findViewById(R.id.buttonBack);
    }

    private Boolean validate()
{



    Boolean result = false;

    String name1 = name.getText().toString();
    String pass = password.getText().toString();
    String email = mail.getText().toString();

    if (name1.isEmpty() || pass.isEmpty() || email.isEmpty())
    {
        Toast.makeText(this, "Pleasse enter all the details", Toast.LENGTH_SHORT).show();
    }
    else
    {
        result = true;
    }
    return result;

}
}