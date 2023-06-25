package com.example.tarea2webservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import WebService.Asynchtask;
import WebService.WebService;

public class MainActivity extends AppCompatActivity implements Asynchtask {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void btnenviar(View view){
        Map<String, String> datos = new HashMap<String, String>();
        WebService ws= new WebService(
                "https://dummyjson.com/users",
                datos, MainActivity.this, MainActivity.this);
        ws.execute("GET");
    }
    @Override
    public void processFinish(String result) throws JSONException {
        String dato ="";
        JSONObject jobject = new JSONObject(result);
        JSONArray listpersonas = jobject.optJSONArray("users");
        for(int i=0; i< listpersonas.length();i++){
            JSONObject personas=
                    listpersonas.getJSONObject(i);
            dato=dato+personas.getString("id").toString()+", "+
                    personas.getString("firstName").toString()+", "+
                    personas.getString("age").toString()+", "+
                    personas.getString("email").toString()+"\n";
        }
        TextView cadena =(TextView) findViewById(R.id.textView5);
        cadena.setText(dato);
    }
}