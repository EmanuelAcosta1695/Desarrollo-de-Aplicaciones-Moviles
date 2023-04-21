package com.example.App_Clientes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.App_Clientes.db.DbHelper;


public class MainActivity extends AppCompatActivity {

    private Button btnInngresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnInngresar = findViewById(R.id.btnIngresar);

        MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.background);
        mediaPlayer.start();

        btnInngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DbHelper dBhelper = new DbHelper(MainActivity.this);
                SQLiteDatabase db = dBhelper.getWritableDatabase();

                if(db != null){
                    Intent lista_clientes = new Intent(getApplicationContext(), PresentacionActivity.class);

                    mediaPlayer.stop();

                    startActivity(lista_clientes);

                    Toast.makeText(MainActivity.this, "base de datos creada", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this, "no se pudo crear la base de datos", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

}