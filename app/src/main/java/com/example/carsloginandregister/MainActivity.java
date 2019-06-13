package com.example.carsloginandregister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //declare your edit text text view and button
    EditText username, password;
    TextView register;
    Button btnLogin;
    DatabaseHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DatabaseHelper(this);
        //map all ids to their respective views
        username = (EditText) findViewById(R.id.edUsername);
        password = (EditText) findViewById(R.id.edPassword);
        register = (TextView) findViewById(R.id.txtRegister);
        btnLogin = (Button) findViewById(R.id.btnLogin);

        register.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                Intent registerIntent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(registerIntent);
            }

        });

        btnLogin.setOnClickListener(new View.OnClickListener(){
            @Override

            public void onClick(View view) {
                String user = username.getText().toString().trim();
                String pwd = password.getText().toString().trim();
                Boolean res = db.checkUser(user,pwd);

                if (res == true){
                    Intent LoginScreen = new Intent(MainActivity.this,HomeActivity.class);
                    Toast.makeText(MainActivity.this, "Successfully Logged In",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "Login Unsuccessful",Toast.LENGTH_SHORT).show();
                }
            }

        });

    }
}
