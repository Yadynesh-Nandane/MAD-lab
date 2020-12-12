// on open selction screen

package com.example.androidlab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button emp,admin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emp = findViewById(R.id.btnemp);
        admin = findViewById(R.id.btnadmin);

        emp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // intenting to the Employee activity
                Intent intent = new Intent(MainActivity.this, Employee.class);
                startActivity(intent);
            }
        });

        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
                // intenting to the Employee activity
                Intent intent = new Intent(MainActivity.this, Admin.class);
                startActivity(intent);
            }
        });
    }
}