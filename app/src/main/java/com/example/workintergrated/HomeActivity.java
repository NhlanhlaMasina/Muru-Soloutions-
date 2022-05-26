package com.example.workintergrated;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getSupportActionBar().hide();
        //requestWindowFeature(Window.FEATURE_NO_TITLE);



        Launcher launcher = new Launcher(); //object for Launcher theread
        launcher.start();

    }
    private class Launcher extends Thread
    {
        public void run()
        {
            try{
                sleep(5000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            startActivity(new Intent(HomeActivity.this, WelcomePage.class));
            HomeActivity.this.finish();
        }
    }
}