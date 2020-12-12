package com.example.androidlab;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;
import java.text.SimpleDateFormat;

public class EmpDashboard extends AppCompatActivity {
    Button profile, reclog, reclogout, logout;
    TextView logintime, logouttime;
    String username;
    AlertDialog.Builder builder;
    SharedPreferences prf;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emp_dashboard);

        profile = (Button) findViewById(R.id.myprofile);
        logout = (Button) findViewById(R.id.logout);
        reclog = (Button) findViewById(R.id.RecOrgLog);
        reclogout = (Button) findViewById(R.id.RecOrgLogout);

        logintime = (TextView) findViewById(R.id.logintime);
        logouttime = (TextView) findViewById(R.id.logouttime);

        builder = new AlertDialog.Builder(this);

        DB = new DBHelper(this);

        prf = getSharedPreferences("user_details",MODE_PRIVATE);


        reclog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                builder.setMessage("Do you want to record Login Time?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
                                Date date = new Date();
                                logintime.setText("LogIn Time: "+formatter.format(date));
                                reclog.setVisibility(View.GONE);
                                reclogout.setVisibility(View.VISIBLE);
                                logintime.setVisibility(View.VISIBLE);
                                Intent intent = getIntent();
                                try {
                                    username = prf.getString("username",null);
                                    Boolean updatelogintime=DB.updatelogintime(username,formatter.format(date));
                                    if(updatelogintime==true){
                                        Toast.makeText(EmpDashboard.this, "Login Time Recorded Successfully", Toast.LENGTH_SHORT).show();
                                    }
                                    else {
                                        Toast.makeText(EmpDashboard.this, "Login Time Record Failed", Toast.LENGTH_SHORT).show();
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //  Action for 'NO' Button
                                dialog.cancel();
                                Toast.makeText(getApplicationContext(),"Login Time not recorded", Toast.LENGTH_SHORT).show();
                            }
                        });
                //Creating dialog box
                AlertDialog alert = builder.create();
                //Setting the title manually
                alert.setTitle("RecordTimeAlertDialog");
                alert.show();
            }
        });

        reclogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                builder.setMessage("Do you want to record Logout Time?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
                                Date date = new Date();
                                logouttime.setText("Logout Time: "+formatter.format(date));
                                reclogout.setVisibility(View.GONE);
                                reclog.setVisibility(View.VISIBLE);
                                logouttime.setVisibility(View.VISIBLE);
                                Intent intent = getIntent();
                                try {
                                    username = prf.getString("username",null);
                                    Boolean updatelogouttime=DB.updatelogouttime(username,formatter.format(date));
                                    if(updatelogouttime==true){
                                        Toast.makeText(EmpDashboard.this, "Loout Time Recorded Successfully", Toast.LENGTH_SHORT).show();
                                    }
                                    else {
                                        Toast.makeText(EmpDashboard.this, "Logout Time Record Failed", Toast.LENGTH_SHORT).show();
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //  Action for 'NO' Button
                                dialog.cancel();
                                Toast.makeText(getApplicationContext(),"Login Time not recorded", Toast.LENGTH_SHORT).show();
                            }
                        });
                //Creating dialog box
                AlertDialog alert = builder.create();
                //Setting the title manually
                alert.setTitle("RecordTimeAlertDialog");
                alert.show();
            }
        });
		
		/*Intent intent = getIntent();
        try {
            username = intent.getExtras().getString("username");
        } catch (Exception e) {
            e.printStackTrace();
        }*/

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EmpDashboard.this,MyProfile.class);
				intent.putExtra("username",prf.getString("username",null));
                startActivity(intent);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(EmpDashboard.this, "Logged Out Successfully", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(EmpDashboard.this,Employee.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                SharedPreferences.Editor editor = prf.edit();
                editor.clear();
                editor.commit();
                startActivity(intent);
            }
        });
    }
}