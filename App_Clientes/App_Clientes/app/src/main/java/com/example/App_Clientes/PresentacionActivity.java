package com.example.App_Clientes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;


public class PresentacionActivity extends AppCompatActivity {

    Button btnClientes, btnAyuda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_presentacion);

        // Lleva a pantalla donde se muestra la lista de clientes
        btnClientes = findViewById(R.id.btnClientes);
        btnClientes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent clientes = new Intent(getApplicationContext(), ClientesActivity.class);
                startActivity(clientes);

            }
        });

        // Lleva a pantalla donde estan los videos
        btnAyuda = findViewById(R.id.btnAyuda);
        btnAyuda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ayuda = new Intent(getApplicationContext(), AyudaActivity.class);
                startActivity(ayuda);
            }
        });

    }

}
