package com.example.mycab;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class CustomerLoginRegisterActivity extends AppCompatActivity {
private Button customerLoginBtn;
private  Button customerRegisterBtn;
private TextView customerRegisterLink;
private TextView cutomerStatus;
    private TextView emailCustomer, passwordCustomer;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_login_register);
        cutomerStatus=findViewById(R.id.customer_status);
        customerLoginBtn=findViewById(R.id.cutomer_login_btn);
        customerRegisterBtn=findViewById(R.id.register_customer_btn);
        customerRegisterLink=findViewById(R.id.register_customer_link);
        emailCustomer=findViewById(R.id.email_customer);
        passwordCustomer=findViewById(R.id.password_customer);
        mAuth=FirebaseAuth.getInstance();
        customerRegisterBtn.setVisibility(View.INVISIBLE);
        customerRegisterBtn.setEnabled(false);
        customerRegisterLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customerLoginBtn.setVisibility(View.INVISIBLE);
                customerRegisterLink.setVisibility(View.INVISIBLE);
                cutomerStatus.setText("Register Customer");
                customerRegisterBtn.setVisibility(View.VISIBLE);
                customerRegisterBtn.setEnabled(true);


            }
        });
        customerLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email= emailCustomer.getText().toString();
                String password= passwordCustomer.getText().toString();
                LoginDriver(email,password);
            }

            private void LoginDriver(String email, String password) {
                if(email.equals("")|| password.equals(""))
                {
                    Toast.makeText(CustomerLoginRegisterActivity.this, "Please Fill All Fields", Toast.LENGTH_SHORT).show();
                }
                else {
                    mAuth.signInWithEmailAndPassword(email,password)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful())
                                    {
                                        Toast.makeText(CustomerLoginRegisterActivity.this, "Customer LogIn Successfully", Toast.LENGTH_SHORT).show();
                                    }
                                    else {
                                        Toast.makeText(CustomerLoginRegisterActivity.this, "Wrong Email or Password", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                }

            }
        });
        customerRegisterBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                String email= emailCustomer.getText().toString();
                String password= passwordCustomer.getText().toString();
                RegisterCustomer(email,password);

            }

            private void RegisterCustomer(String email, String password)
            {
                if(email.equals("")|| password.equals(""))
                {
                    Toast.makeText(CustomerLoginRegisterActivity.this, "Please Fill All Fields", Toast.LENGTH_SHORT).show();
                }
                else {
                    mAuth.createUserWithEmailAndPassword(email,password)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful())
                                    {
                                        Toast.makeText(CustomerLoginRegisterActivity.this, "Customer Is Registered Now", Toast.LENGTH_SHORT).show();
                                    }
                                    else {
                                        Toast.makeText(CustomerLoginRegisterActivity.this, "Customer registration failed", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                }
            }


        });
    }
}