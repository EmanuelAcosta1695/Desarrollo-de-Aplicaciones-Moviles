package com.example.clientesapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.clientesapp.modelo.Cliente;


public class EditarCliente extends AppCompatActivity {
    private Button btnCancelar;
    private Button btnAgregar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.editar_cliente);

        btnCancelar = findViewById(R.id.cancelar_editarcliente);
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cancelar = new Intent(getApplicationContext(), Clientes.class);
                startActivity(cancelar);

            }
        });


        btnAgregar = findViewById(R.id.editar_cliente);
        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


    }
}

