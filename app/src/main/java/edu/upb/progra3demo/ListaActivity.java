package edu.upb.progra3demo;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class ListaActivity extends AppCompatActivity {

    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);
        mContext = this;

        receiveData();
    }

    private void receiveData() {
        Intent intent = getIntent();
        String usuario = intent.getStringExtra(Constants.KEY_USUARIO);
        String password = intent.getStringExtra(Constants.KEY_PASSWORD);

        Toast.makeText(mContext,
                "Hola: " + usuario, Toast.LENGTH_SHORT)
                .show();
    }
}
