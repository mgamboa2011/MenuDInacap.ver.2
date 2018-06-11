package com.example.mauro.menudinacap;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Usuario extends AppCompatActivity {
TextView tvusuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario);

        tvusuario = (TextView) findViewById(R.id.TextV_Usuario);

        Intent intent = getIntent();
        String usuario = intent.getStringExtra("usuario");

        tvusuario.setText(usuario);
    }
}
