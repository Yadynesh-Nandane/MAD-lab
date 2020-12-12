package com.example.androidlab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Layout;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Employee extends AppCompatActivity {
    Button signup,login, register, gotologin;
    LinearLayout LoginSection, SignupSection;
    EditText txtfname, txtphone, txtaddress, txtid, txtemail,txtpassword,txt_email,txt_password;
    DBHelper DB;
    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee);

        signup = (Button) findViewById(R.id.btnsignup);
        login = (Button) findViewById(R.id.btnLogin);
        register = (Button) findViewById(R.id.btnRegister);
        gotologin = (Button) findViewById(R.id.btngotoLogin);

        LoginSection = (LinearLayout) findViewById(R.id.LoginSection);
        SignupSection = (LinearLayout) findViewById(R.id.SignupSection);

        txtfname=(EditText)findViewById(R.id.txtfname);
        txtphone=(EditText)findViewById(R.id.txtphone);
        txtaddress=(EditText)findViewById(R.id.txtaddress);
        txtid=(EditText)findViewById(R.id.txtid);
        txtemail=(EditText)findViewById(R.id.txtemail);
        txtpassword=(EditText)findViewById(R.id.txtpassword);

        txt_email = (EditText)findViewById(R.id.txt_email);
        txt_password = (EditText)findViewById(R.id.txt_password);
        DB = new DBHelper(this);

        pref = getSharedPreferences("user_details",MODE_PRIVATE);


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginSection.setVisibility(View.GONE);
                SignupSection.setVisibility(View.VISIBLE);
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String fullName= txtfname.getText().toString();
                final String phone= txtphone.getText().toString();
                final String address= txtaddress.getText().toString();
                final String id = txtid.getText().toString();
                final String email= txtemail.getText().toString().trim();
                String password= txtpassword.getText().toString().trim();
                String logintime="NA";
                String logouttime="NA";

                if (TextUtils.isEmpty(fullName)){
                    Toast.makeText(Employee.this, "Please Enter Full Name", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(phone)){
                    Toast.makeText(Employee.this, "Please Enter Phone", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(address)){
                    Toast.makeText(Employee.this, "Please Enter Address", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(id)){
                    Toast.makeText(Employee.this, "Please Enter ID", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(email)){
                    Toast.makeText(Employee.this, "Please Enter Email", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)){
                    Toast.makeText(Employee.this, "Please Enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }


                if (password.length()<6){
                    Toast.makeText(Employee.this, "Password too small", Toast.LENGTH_SHORT).show();
                }

                Boolean checkuser = DB.checkusername(email);
                if (checkuser == false){
                    Boolean insert=DB.insertData(id,fullName,address,email,password,logintime, logouttime);
                    if(insert == true){
                        Toast.makeText(Employee.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                        LoginSection.setVisibility(View.VISIBLE);
                        SignupSection.setVisibility(View.GONE);
                        txtid.setText("");
                        txtfname.setText("");
                        txtphone.setText("");
                        txtaddress.setText("");
                        txtemail.setText("");
                        txtpassword.setText("");
                    }
                    else {
                        Toast.makeText(Employee.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(Employee.this, "User Already Exists! Please Sign In", Toast.LENGTH_SHORT).show();
                }x
            }
        });


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email= txt_email.getText().toString().trim();
                String password= txt_password.getText().toString().trim();

                if (TextUtils.isEmpty(email)){
                    Toast.makeText(Employee.this, "Please Enter Email", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)){
                    Toast.makeText(Employee.this, "Please Enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }

                Boolean checkusernamepassword = DB.checkusernamepassword(email,password);
                if (checkusernamepassword==true){
                    Toast.makeText(Employee.this, "Logged In Successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Employee.this,EmpDashboard.class);
                    intent.putExtra("username",email);
                    SharedPreferences.Editor editor = pref.edit();
                    editor.putString("username",email);
                    editor.putString("password",password);
                    editor.commit();
                    startActivity(intent);
                    txt_email.setText("");
                    txt_password.setText("");
                }
                else {
                    Toast.makeText(Employee.this, "Invalid User", Toast.LENGTH_SHORT).show();
                }

            }
        });

        gotologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginSection.setVisibility(View.VISIBLE);
                SignupSection.setVisibility(View.GONE);
            }
        });
    }
}