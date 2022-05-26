package com.example.workintergrated;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONException;
import org.json.JSONObject;

public class BuildingPay extends AppCompatActivity implements PaymentResultListener {
    Button online;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_building_pay);
        Checkout.preload(getApplicationContext());


        online = findViewById(R.id.buildButtonOnline);


        //amount and round to 100
        String sAmount = "35000";
        final int amount = Math.round(Float.parseFloat(sAmount)*100);

        online.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //initialize razorpay checkout
                Checkout checkout = new Checkout();
                //set key id
                checkout.setKeyID("rzp_test_vKz9ansVOXS8DF");
                JSONObject object = new JSONObject();
                try {
                    object.put("name", "Muru Solution");
                    object.put("description", "Construction Work/Building");
                    object.put("theme.color", "#0093DD");
                    object.put("currency", "ZAR");
                    object.put("amount",amount);
                    object.put("prefill.contact", "");
                    object.put("prefill.email", "");

                    //open checkout activity
                    checkout.open(BuildingPay.this, object);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void onPaymentSuccess(String s) {
        //alert dialog
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.setTitle("Payment ID");
        builder.setMessage(s);
        builder.show();
    }

    @Override
    public void onPaymentError(int i, String s) {
        Toast.makeText(getApplicationContext(),s,Toast.LENGTH_SHORT).show();
    }
}