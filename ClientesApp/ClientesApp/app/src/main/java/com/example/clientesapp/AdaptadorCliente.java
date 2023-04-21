package com.example.clientesapp;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.clientesapp.modelo.Cliente;

import java.util.List;

public class AdaptadorCliente extends BaseAdapter {

    private LayoutInflater inflador;
    private List<Cliente> clientes;
    private Context contexto;

    public AdaptadorCliente(Context contexto, List<Cliente> pClientes){
        inflador = (LayoutInflater) contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        clientes = pClientes;
        this.contexto = contexto;
    }

    @Override
    public int getCount() {
        return clientes.size();
    }

    @Override
    public Object getItem(int position) {
        return clientes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View vistaReciclada, ViewGroup viewGroup) {

        if (vistaReciclada == null) {
            vistaReciclada = inflador.inflate(R.layout.view_clientes, null);
        }

        Cliente c = clientes.get(position);

        TextView nombre = vistaReciclada.findViewById(R.id.item_nombre);
        nombre.setText(c.getNombre());

        TextView apellido = vistaReciclada.findViewById(R.id.item_apellido);
        apellido.setText(c.getApellido());

        TextView email = vistaReciclada.findViewById(R.id.item_email);
        email.setText(c.getEmail());

        TextView direccion = vistaReciclada.findViewById(R.id.item_direccion);
        direccion.setText(c.getDireccion());

        ImageView foto = vistaReciclada.findViewById(R.id.item_foto);
        foto.setImageResource(R.drawable.avatar);


        Button btnEditar = vistaReciclada.findViewById(R.id.editar);
        //View finalVistaReciclada = vistaReciclada;
        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nuevoCliente = new Intent(contexto, EditarCliente.class);

                contexto.startActivity(nuevoCliente);
            }
        });


        Button btnEliminar = vistaReciclada.findViewById(R.id.eliminar);
        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        return vistaReciclada;
    }
}
