package com.example.carsloginandregister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    //declare your edit text text view and button
    EditText names,email,username, password,cfmpassword;
    TextView login;
    Button btnRegister;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        //map all ids to their respective views
        db = new DatabaseHelper(this);
        names = (EditText) findViewById(R.id.edNames);
        username = (EditText) findViewById(R.id.edUsername);
        password = (EditText) findViewById(R.id.edPassword);
        cfmpassword = (EditText) findViewById(R.id.edPassword2);
        login = (TextView) findViewById(R.id.txtLogin);
        btnRegister = (Button) findViewById(R.id.btnRegister);

        login.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                Intent loginIntent = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(loginIntent);
            }

        });

        btnRegister.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                String fullNames = names.getText().toString().trim();
                String user = username.getText().toString().trim();
                String pwd = password.getText().toString().trim();
                String cnf_pwd = cfmpassword.getText().toString().trim();

                //password validation goes here
                if (pwd.equals(cnf_pwd)){
                    long val =db.addUser(fullNames,user,pwd);
                    if (val > 0){
                        Toast.makeText(RegisterActivity.this, "You have been successfully Registered",Toast.LENGTH_SHORT).show();
                        Intent moveToLogin = new Intent(RegisterActivity.this,MainActivity.class);
                        startActivity(moveToLogin);
                    } else {
                        Toast.makeText(RegisterActivity.this, "Registration Unsuccessful",Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(RegisterActivity.this, "Password does not match",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
