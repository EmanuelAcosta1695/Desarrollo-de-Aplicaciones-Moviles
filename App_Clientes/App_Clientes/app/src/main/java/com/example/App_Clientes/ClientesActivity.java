package com.example.App_Clientes;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.App_Clientes.adaptadores.ClientesAdapter;
import com.example.App_Clientes.db.DbClientes;
import com.example.App_Clientes.entidades.Cliente;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class ClientesActivity extends AppCompatActivity  {

    RecyclerView listaClientes;
    ArrayList<Cliente> listaArrayClientes;
    private Button btnSaveDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clientes);

        listaClientes = findViewById(R.id.listaContactos);

        listaClientes.setLayoutManager(new LinearLayoutManager(this));

        DbClientes dbClientes = new DbClientes(ClientesActivity.this);

        listaArrayClientes = new ArrayList<>();

        ClientesAdapter adapterCliente = new ClientesAdapter(dbClientes.mostrarClientes());
        listaClientes.setAdapter(adapterCliente);


        // Guarda datos de los clientes en un .txt
        btnSaveDb = findViewById(R.id.saveDb);
        btnSaveDb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ClientesActivity.this);
                builder.setMessage("Desea guardar los datos de los clientes?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                String filename = "Datos de los Clientes.txt";
                                ArrayList<Cliente> list_Clientes = dbClientes.mostrarClientes();
                                try {
                                    OutputStreamWriter file = new OutputStreamWriter(openFileOutput(filename, Context.MODE_PRIVATE));


                                    for (int y = 0; y<list_Clientes.size(); y++) {
                                        file.write("[ " + list_Clientes.get(y).getId() + " \n");
                                        file.write(list_Clientes.get(y).getNombre() + " \n");
                                        file.write(list_Clientes.get(y).getApellido() + " \n");
                                        file.write(list_Clientes.get(y).getEmail() + " \n");
                                        file.write(list_Clientes.get(y).getDireccion() + " \n");
                                        file.write(list_Clientes.get(y).getTelefono() + " \n");
                                        file.write(list_Clientes.get(y).getDni() + " ], " + " \n");
                                        file.write(" \n");
                                    }

                                    file.flush();
                                    file.close();
                                    Toast.makeText(ClientesActivity.this, "Los datos se guardaron exitosamente", Toast.LENGTH_LONG).show();

                                } catch (IOException e) {
                                    Toast.makeText(ClientesActivity.this, "No se pudieron guardar los datos", Toast.LENGTH_LONG).show();
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

    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_principal, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.menuNuevo:
                nuevoRegistro();
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }

    private void nuevoRegistro(){
        Intent intent = new Intent(this, NuevoActivity.class);
        startActivity(intent);
    }
}
