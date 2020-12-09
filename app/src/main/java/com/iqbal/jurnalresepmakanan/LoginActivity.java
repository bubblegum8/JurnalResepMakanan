package com.iqbal.jurnalresepmakanan;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;
import android.view.View;
import android.content.Intent;
import android.os.Bundle;

public class LoginActivity extends AppCompatActivity {
    EditText username,password;
    Button btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        btnLogin = (Button) findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String usernameKey = username.getText().toString();
               String passwordKey = password.getText().toString();

               if(usernameKey.equals("aldy") && passwordKey.equals("123456")){
                   Toast.makeText(getApplicationContext(), "LOGIN BERHASIL", Toast.LENGTH_SHORT).show();
                   Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                   intent.putExtra("username", usernameKey);
                   LoginActivity.this.startActivity(intent);
               } else {
                   AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                   builder.setMessage("Username atau Password Salah!").setNegativeButton("Retry", null).create().show();
               }
            }
        });
    }
}