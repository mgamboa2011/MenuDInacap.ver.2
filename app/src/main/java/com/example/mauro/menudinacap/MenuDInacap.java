package com.example.mauro.menudinacap;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MenuDInacap extends AppCompatActivity {
    // definir variable
    TextView tv_registrar;
    EditText etusuario, etcontrasena;
    CardView cv_iniciar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_dinacap);

        //asociar elemento de tv registrar
        etusuario = findViewById(R.id.TV_usu);
        etcontrasena = findViewById(R.id.TV_pas);
        cv_iniciar = findViewById(R.id.cv_iniciar);
        tv_registrar = (TextView) findViewById(R.id.tv_registrar);
        //crear evento
        tv_registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentReg = new Intent(MenuDInacap.this, Registro.class);
                MenuDInacap.this.startActivity(intentReg);
            }
        });

        cv_iniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String usuario = etusuario.getText().toString();
                final String contrasena = etcontrasena.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (usuario.isEmpty()) {
                        }
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if (success) {

                                String usuario = jsonResponse.getString("usuario");
                                Intent intent = new Intent(MenuDInacap.this, Usuario.class);

                                intent.putExtra("usuario", usuario);
                                intent.putExtra("contrasena", contrasena);

                                MenuDInacap.this.startActivity(intent);


                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(MenuDInacap.this);
                                builder.setMessage("error Login ")
                                        .setNegativeButton("Retry", null)
                                        .create().show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                };

                LoginRequest loginRequest = new LoginRequest(usuario, contrasena, responseListener);
                RequestQueue queue = Volley.newRequestQueue(MenuDInacap.this);
                queue.add(loginRequest);
            }
        });

    }
}

