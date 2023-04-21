package com.example.App_Clientes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.App_Clientes.db.DbClientes;
import com.example.App_Clientes.entidades.Cliente;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class EditarActivity extends AppCompatActivity {

    EditText txtNombre, txtApellido, txtCorreo, txtDireccion, txtTelefono, txtDni;
    Button btnGuardar,  saveCustomerData;
    private ArrayList<Cliente> clientes;

    boolean correcto = false;

    Cliente cliente;
    int id = 0;

    FloatingActionButton fabEditar, fabEliminar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver);

        txtNombre = findViewById(R.id.txtNombre);
        txtApellido = findViewById(R.id.txtApellido);
        txtCorreo = findViewById(R.id.txtCorreo);
        txtDireccion = findViewById(R.id.txtDireccion);
        txtTelefono = findViewById(R.id.txtTelefono);
        txtDni = findViewById(R.id.txtDni);

        btnGuardar = findViewById(R.id.btnGuardar);

        // para que no los muestre al editar
        saveCustomerData = findViewById(R.id.saveCustomerData);
        saveCustomerData.setVisibility(View.INVISIBLE);
        fabEditar = findViewById(R.id.fabEditar);
        fabEditar.setVisibility(View.INVISIBLE);
        fabEliminar = findViewById(R.id.fabEliminar);
        fabEliminar.setVisibility(View.INVISIBLE);


        // distitas validaciones del parametros que estamos recibiendo
        if(savedInstanceState == null){
            Bundle extras = getIntent().getExtras();
            if(extras == null){
                id = Integer.parseInt(null);
            } else {
                id = extras.getInt("ID");
            }
        } else {
            id = (int) savedInstanceState.getSerializable("ID");
        }

        DbClientes dbClientes = new DbClientes(EditarActivity.this);
        cliente = dbClientes.verCliente(id);
        clientes = dbClientes.mostrarClientes();

        if(cliente != null){
            txtNombre.setText(cliente.getNombre());
            txtApellido.setText(cliente.getApellido());
            txtCorreo.setText(cliente.getEmail());
            txtDireccion.setText(cliente.getDireccion());
            txtTelefono.setText(cliente.getTelefono());
            txtDni.setText(cliente.getDni());
        }

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!txtNombre.getText().toString().equals("") && !txtApellido.getText().toString().equals("") && !txtCorreo.getText().toString().equals("") && !txtDireccion.getText().toString().equals("") && !txtTelefono.getText().toString().equals("") && !txtDni.getText().toString().equals("")) {
                    if (!existeCliente(txtDni.getText().toString())) {

                        // editarCliente devuelve un boolean
                        correcto = dbClientes.editarCliente(id, txtNombre.getText().toString(), txtApellido.getText().toString(), txtDireccion.getText().toString(), txtCorreo.getText().toString(), txtTelefono.getText().toString(), txtDni.getText().toString());

                        if (correcto) {
                            Toast.makeText(EditarActivity.this, "REGISTRO MODIFICADO", Toast.LENGTH_SHORT).show();
                            verRegistro();
                        } else {
                            Toast.makeText(EditarActivity.this, "ERROR AL MODIFICAR REGISTRO", Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        Toast.makeText(EditarActivity.this, "El DNI ingresado ya está en uso.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(EditarActivity.this, "DEBE LLENAR LOS CAMPOS OBLIGATORIOS", Toast.LENGTH_SHORT).show();
                }
            }

        });
    }

    // Valida si el dni que quiere editar el usuario ya existe
    private boolean existeCliente(String dni){

        for(Cliente c: clientes){
            if(c.getDni().equals(dni)){
                return true;
            }
        }
        return false;
    }

    private void verRegistro(){
        Intent intent = new Intent(this, VerActivity.class);
        intent.putExtra("ID", id);
        startActivity(intent);
    }
}