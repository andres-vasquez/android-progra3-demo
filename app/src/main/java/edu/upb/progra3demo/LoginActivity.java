package edu.upb.progra3demo;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Context mContext;

    private EditText mUsuarioEditText;
    private EditText mPasswordEditText;

    private Button mIniciarSesionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mContext = this;

        initViews();
        addEvents();
    }

    private void initViews() {
        mUsuarioEditText = findViewById(R.id.usuario);
        mPasswordEditText = findViewById(R.id.password);
        mIniciarSesionButton = findViewById(R.id.iniciarSesion);
    }

    private void addEvents() {
        mIniciarSesionButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String usuario = mUsuarioEditText.getText().toString();
        String password = mPasswordEditText.getText().toString();
        Log.e("Mis datos", usuario + " " + password);

        Intent intent = new Intent(mContext, ListaActivity.class);
        intent.putExtra(Constants.KEY_USUARIO, usuario);
        intent.putExtra(Constants.KEY_PASSWORD, password);
        startActivity(intent);
    }
}
