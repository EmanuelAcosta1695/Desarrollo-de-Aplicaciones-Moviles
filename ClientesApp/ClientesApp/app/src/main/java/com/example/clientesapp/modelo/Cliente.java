package com.example.clientesapp.modelo;


import android.os.Parcel;
import android.os.Parcelable;


public class Cliente implements Parcelable {

    private String nombre;
    private String apellido;
    private Integer dni;
    private String email;
    private Integer telefono;
    private String direccion;

    public Cliente(){}

    public Cliente(String pNombre, String pApellido, Integer pDni, String pEmail, Integer pTelefono, String pDireccion){
        this.nombre = pNombre;
        this.apellido = pApellido;
        this.dni = pDni;
        this.email = pEmail;
        this.telefono = pTelefono;
        this.direccion = pDireccion;
    }

    protected Cliente(Parcel in) {
        nombre = in.readString();
        apellido = in.readString();
        if (in.readByte() == 0) {
            dni = null;
        } else {
            dni = in.readInt();
        }
        email = in.readString();
        if (in.readByte() == 0) {
            telefono = null;
        } else {
            telefono = in.readInt();
        }
        direccion = in.readString();
    }

    public static final Creator<Cliente> CREATOR = new Creator<Cliente>() {
        @Override
        public Cliente createFromParcel(Parcel in) {
            return new Cliente(in);
        }

        @Override
        public Cliente[] newArray(int size) {
            return new Cliente[size];
        }
    };

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Integer getDni() {
        return dni;
    }

    public void setDni(Integer dni) {
        this.dni = dni;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getTelefono() {
        return telefono;
    }

    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(nombre);
        parcel.writeString(apellido);
        if (dni == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(dni);
        }
        parcel.writeString(email);
        if (telefono == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(telefono);
        }
        parcel.writeString(direccion);
    }
}
