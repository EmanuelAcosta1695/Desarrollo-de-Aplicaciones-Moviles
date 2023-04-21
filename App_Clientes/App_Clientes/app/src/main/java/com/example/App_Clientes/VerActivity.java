package com.example.App_Clientes;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.App_Clientes.db.DbClientes;
import com.example.App_Clientes.entidades.Cliente;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.IOException;
import java.io.OutputStreamWriter;

public class VerActivity extends AppCompatActivity {

    EditText txtNombre, txtApellido, txtCorreo, txtDireccion, txtTelefono, txtDni;
    Button btnGuardar, saveCustomerData;
    FloatingActionButton fabEditar, fabEliminar;

    Cliente cliente;
    int id = 0;

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
        fabEditar = findViewById(R.id.fabEditar);
        fabEliminar = findViewById(R.id.fabEliminar);


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

        DbClientes dbClientes = new DbClientes(VerActivity.this);
        cliente = dbClientes.verCliente(id);

        if(cliente != null){
            txtNombre.setText(cliente.getNombre());
            txtApellido.setText(cliente.getApellido());
            txtCorreo.setText(cliente.getEmail());
            txtDireccion.setText(cliente.getDireccion());
            txtTelefono.setText(cliente.getTelefono());
            txtDni.setText(cliente.getDni());

            btnGuardar.setVisibility(View.INVISIBLE);

            // para q no habra teclado
            txtNombre.setInputType(InputType.TYPE_NULL);
            txtApellido.setInputType(InputType.TYPE_NULL);
            txtCorreo.setInputType(InputType.TYPE_NULL);
            txtDireccion.setInputType(InputType.TYPE_NULL);
            txtTelefono.setInputType(InputType.TYPE_NULL);
            txtDni.setInputType(InputType.TYPE_NULL);
        }

        // Guarda datos del cliente en un .txt
        saveCustomerData = findViewById(R.id.saveCustomerData);
        saveCustomerData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(VerActivity.this);
                builder.setMessage("Desea guardar los datos del cliente?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                String filename = "Datos del Cliente.txt";
                                if (cliente != null) {

                                    try {
                                        OutputStreamWriter archivo = new OutputStreamWriter(openFileOutput(filename, Context.MODE_PRIVATE));

                                        archivo.write(cliente.getId()+ " \n");
                                        archivo.write(cliente.getNombre()+ " \n");
                                        archivo.write(cliente.getApellido()+ " \n");
                                        archivo.write(cliente.getEmail()+ " \n");
                                        archivo.write(cliente.getDireccion()+ " \n");
                                        archivo.write(cliente.getTelefono()+ " \n");
                                        archivo.write(cliente.getDni()+ " \n");
                                        archivo.write(" \n");

                                        archivo.flush();
                                        archivo.close();
                                        Toast.makeText(VerActivity.this, "Datos del cliente guardados exitosamente.", Toast.LENGTH_LONG).show();

                                    } catch (IOException e) {
                                        Toast.makeText(VerActivity.this, "No se pudo guardar los datos del Cliente.", Toast.LENGTH_LONG).show();
                                    }
                                }
                            }
                            })
                            .setNegativeButton("No",new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick (DialogInterface dialogInterface,int i){

                                }
                            }).show();
                        }

                });

        fabEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VerActivity.this, EditarActivity.class);
                intent.putExtra("ID", id);
                startActivity(intent);
            }
        });

        fabEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(VerActivity.this);
                builder.setMessage("¿Desea eliminar este contacto?")
                    .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            if(dbClientes.eliminarCliente(id)){
                                lista();
                            }
                        }
                    })
                    .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    }).show();
            }
        });
    }

    private void lista(){
        Intent intent = new Intent(this,ClientesActivity.class);
        startActivity(intent);
    }
}