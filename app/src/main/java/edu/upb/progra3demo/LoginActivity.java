package edu.upb.progra3demo;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import edu.upb.progra3demo.model.User;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Context mContext;
    private User mUser;

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
        //createObject();
        createObjectFromString();
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

    private void createObject() {
        mUser = new User();
        mUser.setNombreUsuario("dilanAA");
        mUser.setPassword("bbcita420");
        mUser.setEdad(19);
        mUser.setEmail("nalid102@gmail.com");
        mUser.setCodigoUpb(46478);

        String json = new Gson().toJson(mUser);
        Log.e("Mi Usuario", json);
    }

    private void createObjectFromString() {
        String json = "{\"nombreUsuario\":\"IgnacioElBroko\",\"password\":\"todopoderosoYO\",\"edad\":19,\"email\":\"nachitotigredelriozenteno@gmail.com\",\"codigoUpb\":46036,\"celular\":77511999}";
        mUser = new Gson().fromJson(json, User.class);
        Toast.makeText(mContext, mUser.getPassword(), Toast.LENGTH_LONG).show();
    }
}
