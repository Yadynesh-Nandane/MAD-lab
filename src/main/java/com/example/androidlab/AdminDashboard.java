package com.example.androidlab;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class AdminDashboard extends AppCompatActivity {
    Button adminlogout, viewemp;
    //ListView listview;
    DBHelper DB;
    TextView data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

        adminlogout = (Button) findViewById(R.id.adminlogout);
        viewemp = (Button) findViewById(R.id.viewemp);
        //listview = (ListView) findViewById(R.id.ListView);
        data = (TextView) findViewById(R.id.data);
        DB = new DBHelper(this);

        viewemp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res=DB.getdata();
                if(res.getCount()==0){
                    Toast.makeText(AdminDashboard.this, "NO Record exist", Toast.LENGTH_SHORT).show();
                    return;
                }
                //ArrayList<String> theList = new ArrayList<>();
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                    //theList.add(res.getString(1));
                    buffer.append("ID:"+res.getString(0)+"    ");
                    buffer.append("Name: "+res.getString(1)+"\n\n");
                }
                /*ListAdapter listAdapter = new ArrayAdapter<>(AdminDashboard.this,android.R.layout.simple_list_item_1,theList);
                listview.setAdapter(listAdapter);*/
                /*StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("ID:"+res.getString(0)+"\n");
                    buffer.append("Name: "+res.getString(1)+"\n\n");
                }*/
                /*AlertDialog.Builder builder = new AlertDialog.Builder(AdminDashboard.this);
                builder.setCancelable(true);
                builder.setTitle("Employees not signed in");
                builder.setMessage(buffer.toString());
                builder.show();*/
                data.setText(buffer.toString());
                data.setVisibility(View.VISIBLE);
            }
        });

        adminlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(AdminDashboard.this, "Logged Out Successfully", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(AdminDashboard.this,Admin.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
    }
}