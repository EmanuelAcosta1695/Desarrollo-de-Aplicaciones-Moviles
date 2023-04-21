package com.example.clientesapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.example.clientesapp.modelo.Cliente;

import java.util.ArrayList;
import java.util.List;

public class Clientes extends AppCompatActivity {
    private ListView listaClientes;
    private Button btnNuevoCLiente;
    private Button btnVolver;
    private List<Cliente> clientes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.clientes);

        listaClientes = findViewById(R.id.lista_clientes);

        clientes = new ArrayList<>();

        Cliente cliente1 = new Cliente("Ramiro", "Perez", 33445588, "juan@mail.com", 231482793, "av siepreviva 222");
        Cliente cliente2 = new Cliente("Pedro", "Perez", 33445588, "juan@mail.com", 231482793, "av siepreviva 222");
        Cliente cliente3 = new Cliente("Alberto", "Perez", 33445588, "juan@mail.com", 231482793, "av siepreviva 222");

        clientes.add(cliente1);
        clientes.add(cliente2);
        clientes.add(cliente3);

        listaClientes.setAdapter(new AdaptadorCliente(getApplicationContext(), clientes));

        listaClientes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Cliente clienteSeleccion = (Cliente) listaClientes.getAdapter().getItem(position);
            }
        });

        btnNuevoCLiente = findViewById(R.id.btnNuevoCliente);
        btnNuevoCLiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nuevoCliente = new Intent(getApplicationContext(), NuevoCliente.class);
                nuevoCliente.putExtra("list", (ArrayList) clientes);
                nuevoLauncher.launch(nuevoCliente);
            }
        });

        btnVolver = findViewById(R.id.volver);
        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent volver = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(volver);
            }
        });
    }

    ActivityResultLauncher<Intent> nuevoLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if(result.getResultCode() == RESULT_OK){
                Bundle extras = result.getData().getExtras();
                Cliente c = (Cliente) extras.get("cliente");
                clientes.add(c);
                listaClientes.setAdapter(new AdaptadorCliente(getApplicationContext(), clientes));

            }
        }
    });
}

