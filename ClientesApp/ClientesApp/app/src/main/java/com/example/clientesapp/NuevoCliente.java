package com.example.clientesapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.clientesapp.modelo.Cliente;

import java.util.ArrayList;
import java.util.List;


public class NuevoCliente extends AppCompatActivity {

    private EditText etNombre, etApellido, etDni, etEmail, etTelefono, etDireccion;
    private Button nuevoCliente;
    private Button btnCancelar;
    private List<Cliente> clientes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.nuevo_cliente);

        btnCancelar = findViewById(R.id.cancelar_nuevocliente);
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cancelar = new Intent(getApplicationContext(), Clientes.class);
                startActivity(cancelar);
            }
        });

        etNombre = findViewById(R.id.nombre);
        etApellido = findViewById(R.id.apellido);
        etDni = findViewById(R.id.dni);
        etEmail = findViewById(R.id.email);
        etTelefono = findViewById(R.id.telefono);
        etDireccion = findViewById(R.id.direccion);
        nuevoCliente = findViewById(R.id.nuevo_cliente);

        nuevoCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nom = etNombre.getText().toString();
                String ape = etApellido.getText().toString();
                int dni = Integer.parseInt(etDni.getText().toString());
                String email = etEmail.getText().toString();
                int tel = Integer.parseInt(etTelefono.getText().toString());
                String dir = etDireccion.getText().toString();


                Cliente c = new Cliente(nom, ape, dni, email, tel, dir);
                Intent intent = new Intent();
                intent.putExtra("cliente", c);
                setResult(RESULT_OK, intent);
                finish();

            }
        });



    }
}
