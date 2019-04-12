package edu.upb.progra3demo;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import edu.upb.progra3demo.adapter.SongAdapter;
import edu.upb.progra3demo.model.Song;

public class ListaActivity extends AppCompatActivity {

    private Context mContext;

    private Spinner genero;
    private ListView canciones;

    private List<String> generos = new ArrayList<>();
    private List<Song> cancionesArray = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);
        mContext = this;

        receiveData();

        generos.add("Rock");
        generos.add("Cumbia");
        generos.add("Baladas");
        generos.add("Dubstep");

        cancionesArray.add(new Song(1, R.drawable.turizo, "Desconocidos", "Manuel Turizo"));
        cancionesArray.add(new Song(2, R.drawable.barak, "Sola", "Manuel Turizo"));
        cancionesArray.add(new Song(3, R.drawable.cover, "When I've done", "Linkin Park"));

        initViews();
        addEvents();
    }

    private void receiveData() {
        Intent intent = getIntent();
        String usuario = intent.getStringExtra(Constants.KEY_USUARIO);
        String password = intent.getStringExtra(Constants.KEY_PASSWORD);

        Toast.makeText(mContext,
                "Hola: " + usuario, Toast.LENGTH_SHORT)
                .show();
    }

    private void initViews() {
        genero = findViewById(R.id.genero);
        ArrayAdapter<String> simpleAdapter =
                new ArrayAdapter<String>(mContext, android.R.layout.simple_list_item_1, generos);
        genero.setAdapter(simpleAdapter);

        canciones = findViewById(R.id.canciones);

        SongAdapter cancionesAdapter = new SongAdapter(mContext, cancionesArray);
        canciones.setAdapter(cancionesAdapter);
    }

    private void addEvents() {
        genero.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(mContext,
                        "Se selecciono la opcion: " + generos.get(position),
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
