package com.example.App_Clientes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.App_Clientes.db.DbClientes;
import com.example.App_Clientes.entidades.Cliente;

import java.util.ArrayList;
import java.util.Random;

public class NuevoActivity extends AppCompatActivity {

    EditText txtNombre, txtApellido, txtCorreo, txtDireccion, txtTelefono, txtDni;
    Button btnGuardar;
    private ArrayList<Cliente> clientes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo);

        DbClientes dbClientes = new DbClientes(NuevoActivity.this);
        clientes = dbClientes.mostrarClientes();

        txtNombre = findViewById(R.id.txtNombre);
        txtApellido = findViewById(R.id.txtApellido);
        txtCorreo = findViewById(R.id.txtCorreo);
        txtDireccion = findViewById(R.id.txtDireccion);
        txtTelefono = findViewById(R.id.txtTelefono);
        txtDni = findViewById(R.id.txtDni);

        btnGuardar = findViewById(R.id.btnGuardar);


        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!txtNombre.getText().toString().equals("") && !txtApellido.getText().toString().equals("") && !txtCorreo.getText().toString().equals("") && !txtDireccion.getText().toString().equals("") && !txtTelefono.getText().toString().equals("") && !txtDni.getText().toString().equals("")){
                    if(!existeCliente(txtDni.getText().toString())){

                        // Genera valor random que se le asigna a la propeidad avatar del objeto cliente.
                        // Este valor establece cual foto se le colocara a cada cliente en la lista de clientes y se mantendra asi.
                        Random r = new Random();
                        int avatar = r.nextInt(5)+1;

                        long id = dbClientes.insertarCliente(txtNombre.getText().toString(), txtApellido.getText().toString(), txtDireccion.getText().toString(), txtCorreo.getText().toString(), txtTelefono.getText().toString(), txtDni.getText().toString(), avatar);

                        if(id>0){
                            Toast.makeText(NuevoActivity.this, "Registro Guardado", Toast.LENGTH_LONG).show();
                            verClientes();
                        } else {
                            Toast.makeText(NuevoActivity.this, "Error al guardar registro", Toast.LENGTH_LONG).show();
                        }

                    }else{
                        Toast.makeText(NuevoActivity.this, "El DNI ingresado ya está en uso.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(NuevoActivity.this, "SE DEBEN LLENAR LOS CAMPOS", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // valida si el dni que ingra el usuario ya existe
    private boolean existeCliente(String dni){
        for(Cliente c: clientes){
            if(c.getDni().equals(dni)){
                return true;
            }
        }
        return false;
    }


    private void verClientes(){
        Intent intent = new Intent(this, ClientesActivity.class);
        startActivity(intent);
    }
}