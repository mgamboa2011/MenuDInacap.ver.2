package com.example.mauro.menudinacap;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Registro extends AppCompatActivity implements View.OnClickListener {
    EditText etusuario, etcontrasena, etrcontrasena;
    CardView cv_registrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        etusuario = (EditText) findViewById(R.id.EditT_usuario);
        etcontrasena = (EditText) findViewById(R.id.EditT_pas);
        etrcontrasena = (EditText) findViewById(R.id.EditT_repetirpas);

        cv_registrar = (CardView) findViewById(R.id.CardV_registrar);

        cv_registrar.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        //definir variables que reciben datos de los editext
        final String usuario = etusuario.getText().toString();
        final String contrasena = etcontrasena.getText().toString();
        final String rcontrasena = etrcontrasena.getText().toString();

        if (usuario.equals("") || contrasena.equals("") || rcontrasena.equals("")) {
            AlertDialog.Builder builder = new AlertDialog.Builder(Registro.this);
            builder.setMessage("Complete todos los datos solicitados.")
                    .setNegativeButton("Reintentar", null)
                    .create().show();
        } else {
            if(rcontrasena.equals(contrasena)){


                Response.Listener<String> respoListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success"); //manda respuesta de registro.

                            if (success) {
                                Intent intent = new Intent(Registro.this, MenuDInacap.class);
                                Registro.this.startActivity(intent);
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(Registro.this);
                                builder.setMessage("El Usuario ya existe")
                                        .setNegativeButton("Reintentar", null)
                                        .create().show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                RegisterRequest registerRequest = new RegisterRequest(usuario, contrasena, rcontrasena, respoListener);
                RequestQueue queue = Volley.newRequestQueue(Registro.this);
                queue.add(registerRequest);
            }else {
                AlertDialog.Builder builder = new AlertDialog.Builder(Registro.this);
                builder.setMessage("Las contraseñas deben coincidir.")
                        .setNegativeButton("Reintentar", null)
                        .create().show();
            }

        }

    }
}