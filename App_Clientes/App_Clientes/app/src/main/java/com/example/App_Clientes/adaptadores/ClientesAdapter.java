package com.example.App_Clientes.adaptadores;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.App_Clientes.R;
import com.example.App_Clientes.VerActivity;
import com.example.App_Clientes.entidades.Cliente;

import java.util.ArrayList;
import java.util.Random;

public class ClientesAdapter extends RecyclerView.Adapter<ClientesAdapter.ContactoViewHolder> {

    ArrayList<Cliente> listaClientes;

    public ClientesAdapter(ArrayList<Cliente> listaClientes){
        this.listaClientes = listaClientes;
    }

    @NonNull
    @Override
    public ContactoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_contacto, null, false);

        return new ContactoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactoViewHolder holder, int position) {
        holder.viewNombre.setText(listaClientes.get(position).getNombre());
        holder.viewApellido.setText(listaClientes.get(position).getApellido());
        holder.viewCorreo.setText(listaClientes.get(position).getEmail());
        holder.viewDireccion.setText(listaClientes.get(position).getDireccion());

        // setea foto en base a valor de la propiedad avatar en cada objeto cliente
        switch (listaClientes.get(position).getAvatar()){
            case 1:
                holder.foto.setImageResource(R.drawable.avatar);
                break;
            case 2:
                holder.foto.setImageResource(R.drawable.avatar1);
                break;
            case 3:
                holder.foto.setImageResource(R.drawable.avatar2);
                break;
            case 4:
                holder.foto.setImageResource(R.drawable.avatar3);
                break;
            case 5:
                holder.foto.setImageResource(R.drawable.avatar4);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return listaClientes.size();
    }

    public class ContactoViewHolder extends RecyclerView.ViewHolder {

        TextView viewNombre, viewApellido, viewCorreo, viewDireccion;
        ImageView foto;

        public ContactoViewHolder(@NonNull View itemView) {
            super(itemView);

            viewNombre = itemView.findViewById(R.id.viewNombre);
            viewApellido = itemView.findViewById(R.id.viewApellido);
            viewCorreo = itemView.findViewById(R.id.viewCorreo);
            viewDireccion = itemView.findViewById(R.id.viewDireccion);
            foto = itemView.findViewById(R.id.item_foto);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, VerActivity.class);
                    intent.putExtra("ID", listaClientes.get(getAdapterPosition()).getId());
                    context.startActivity(intent);
                }
            });
        }
    }

}
