package com.example.androidlab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Admin extends AppCompatActivity {
    Button login;
    EditText txt_email,txt_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        login = (Button) findViewById(R.id.btnadminLogin);
        txt_email=(EditText)findViewById(R.id.txt_email);
        txt_password=(EditText)findViewById(R.id.txt_password);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (txt_email.getText().toString().equals("admin@infy.com") && txt_password.getText().toString().equals("admin123")) {
                    Toast.makeText(Admin.this, "Logged in Successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Admin.this, AdminDashboard.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(Admin.this, "Invalid Email or Password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}