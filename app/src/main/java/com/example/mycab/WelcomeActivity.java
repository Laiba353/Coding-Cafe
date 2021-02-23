package com.example.mycab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class WelcomeActivity extends AppCompatActivity {
    Button welcomeDriverButton;
    Button welcomeCustomerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        welcomeCustomerButton=findViewById(R.id.welcome_customer_btn);
        welcomeDriverButton=findViewById(R.id.welcome_driver_btn);
        welcomeDriverButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(WelcomeActivity.this, DriverLoginRegisterActivity.class);
                startActivity(intent);
            }
        });
        welcomeCustomerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(WelcomeActivity.this,CustomerLoginRegisterActivity.class);
                startActivity(intent);
            }
        });

    }
}