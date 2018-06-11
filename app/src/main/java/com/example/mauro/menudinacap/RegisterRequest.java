package com.example.mauro.menudinacap;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegisterRequest extends StringRequest {

    private static final String REGISTER_REQUEST_URL = "http://localhost/Register.php";
    private Map<String,String> params;
    public RegisterRequest(String usuario, String cotrasena, String rcontrasena, Response.Listener<String> listener){
        super(Method.POST, REGISTER_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("usuario",usuario);
        params.put("contrasena", cotrasena);
        params.put("rcontrasena", rcontrasena);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
