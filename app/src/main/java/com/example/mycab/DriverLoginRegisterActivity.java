package com.example.mycab;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class DriverLoginRegisterActivity extends AppCompatActivity {
    private Button driverLoginBtn;
    private  Button driverRegisterBtn;
    private TextView driverRegisterLink;
    private TextView driverStatus;
private TextView emailDriver, passwordDriver;
private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_login_empty);
        driverStatus=findViewById(R.id.driver_status);
        driverLoginBtn=findViewById(R.id.driver_login_btn);
        driverRegisterBtn=findViewById(R.id.driver_register_btn);
        driverRegisterLink=findViewById(R.id.driver_register_link);
        emailDriver=findViewById(R.id.email_driver);
        passwordDriver=findViewById(R.id.password_driver);
        mAuth=FirebaseAuth.getInstance();
        driverRegisterBtn.setVisibility(View.INVISIBLE);
        driverRegisterBtn.setEnabled(false);
        driverRegisterLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                driverLoginBtn.setVisibility(View.INVISIBLE);
                driverRegisterLink.setVisibility(View.INVISIBLE);
                driverStatus.setText("Register Customer");
                driverRegisterBtn.setVisibility(View.VISIBLE);
                driverRegisterBtn.setEnabled(true);


            }
        });
        driverLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email= emailDriver.getText().toString();
                String password= passwordDriver.getText().toString();
                LoginDriver(email,password);
            }

            private void LoginDriver(String email, String password) {
                if(email.equals("")|| password.equals(""))
                {
                    Toast.makeText(DriverLoginRegisterActivity.this, "Please Fill All Fields", Toast.LENGTH_SHORT).show();
                }
                else {
                    mAuth.signInWithEmailAndPassword(email,password)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful())
                                    {
                                        Toast.makeText(DriverLoginRegisterActivity.this, "Driver LogIn Successfully", Toast.LENGTH_SHORT).show();
                                    Intent intent=new Intent(DriverLoginRegisterActivity.this, DriversMapsActivity .class);
                                    startActivity(intent);
                                    }
                                    else {
                                        Toast.makeText(DriverLoginRegisterActivity.this, "Wrong Email or Password", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                }

            }
        });
        driverRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email= emailDriver.getText().toString();
                String password= passwordDriver.getText().toString();
                RegisterDriver(email,password);
                
            }

            private void RegisterDriver(String email, String password) {
                if(email.equals("")|| password.equals(""))
                {
                    Toast.makeText(DriverLoginRegisterActivity.this, "Please Fill All Fields", Toast.LENGTH_SHORT).show();
                }
                else {
                    mAuth.createUserWithEmailAndPassword(email,password)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful())
                                    {
                                        Toast.makeText(DriverLoginRegisterActivity.this, "Driver Is Registered Now", Toast.LENGTH_SHORT).show();
                                        Intent intent=new Intent(DriverLoginRegisterActivity.this, DriversMapsActivity.class);
                                        startActivity(intent);
                                    }
                                    else {
                                        Toast.makeText(DriverLoginRegisterActivity.this, "Driver registration failed", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                }
            }


        });
    }
}