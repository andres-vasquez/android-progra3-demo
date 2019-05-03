package edu.upb.progra3demo;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.File;
import java.util.List;

import edu.upb.progra3demo.db.DatabaseHelper;
import edu.upb.progra3demo.model.User;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String LOG = LoginActivity.class.getName();

    private Context mContext;
    private User mUser;

    private ImageView mFotoImageView;

    private EditText mUsuarioEditText;
    private EditText mPasswordEditText;

    private Button mIniciarSesionButton;

    private Uri imageUri;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.w(LOG, "onCreate");

        setContentView(R.layout.activity_login);
        mContext = this;

        initViews();
        addEvents();
        //createObject();
        createObjectFromString();

        dbHelper = new DatabaseHelper(mContext);
        int cantidad = dbHelper.getCount();
        Toast.makeText(mContext,
                "Existen: " + cantidad + " de usuarios registrados",
                Toast.LENGTH_SHORT).show();

        //TODO solo con fines academicos no hacer esto
        thanos();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.w(LOG, "onStart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.w(LOG, "onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.w(LOG, "onResume");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.w(LOG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.w(LOG, "onDestroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.w(LOG, "onRestart");
    }

    private void initViews() {
        mFotoImageView = findViewById(R.id.foto);
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

        if (validarUsuario(usuario, password)) {
            Intent intent = new Intent(mContext, ListaActivity.class);
            intent.putExtra(Constants.KEY_USUARIO, usuario);
            intent.putExtra(Constants.KEY_PASSWORD, password);
            startActivity(intent);
        } else {
            Toast.makeText(mContext, "Usuario o password invalido", Toast.LENGTH_SHORT)
                    .show();
        }
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

    public void registrarClick(View view) {
        Toast.makeText(mContext, "El click funciona", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(mContext, RegisterActivity.class);
        startActivityForResult(intent, Constants.CODIGO_TRANSACCION);
    }

    public void llamarAJordiClick(View view) {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "78898825"));
        startActivity(intent);
    }

    public void compartirTextClick(View view) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT, "Hola muchachos!");
        intent.setType("text/plain");
        startActivity(intent);

    }

    public void tomarUnaFotoClick(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, Constants.CODIGO_TRANSACCION_FOTO);
    }

    public void llevameAlaU(View view) {
        /*String name = "Campus UPB";
        Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                Uri.parse("geo:0,0?q=-16.575137, -68.126868 (" + name + ")"));
        startActivity(intent);*/

        String url = "http://www.upb.edu/";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Constants.CODIGO_TRANSACCION) {
            //Objeto usuario
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    String json = data.getStringExtra(Constants.KEY_REGISTRAR_USUARIO);
                    Log.e("Usuario recibido", json);

                    User usuarioRecibido = new Gson().fromJson(json, User.class);
                    mUsuarioEditText.setText(usuarioRecibido.getNombreUsuario());
                    mPasswordEditText.setText(usuarioRecibido.getPassword());
                }
            }
        } else if (requestCode == Constants.CODIGO_TRANSACCION_FOTO) {
            //Foto
            if (resultCode == RESULT_OK) {
                Log.e("Foto", "Valida");
                Bitmap thumbnail = data.getParcelableExtra("data"); // Obtenemos el Bitmap (imagen) capturada
                // Mostramos nuestra imagen en el imageView
                mFotoImageView.setImageBitmap(thumbnail);
            } else {
                Log.e("Foto cancelada", "Canceled");
            }
        }
    }


    private boolean validarUsuario(String usuario, String password) {
        if (usuario == null || usuario.isEmpty()) {
            return false;
        }

        if (password == null || password.isEmpty()) {
            return false;
        }

        /*SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(mContext);
        String usuarioGuardado = prefs.getString(Constants.PREF_USUARIO, "");
        String passwordGuardado = prefs.getString(Constants.PREF_PASSWORD, "");

        return usuario.equals(usuarioGuardado) && password.equals(passwordGuardado);*/

        DatabaseHelper dbHelper = new DatabaseHelper(this.mContext);
        return dbHelper.login(usuario, password);
    }

    public void eliminarDatos(View view) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(mContext);
        SharedPreferences.Editor editor = prefs.edit();
        editor.clear();
        editor.apply();
    }

    public void thanos() {
        //Obtenemos la lista de usuarios
        final List<User> usuarios = dbHelper.getAll();
        Log.e("LISTA usuarios", new Gson().toJson(usuarios));

        //Si hubieran datos
        if (usuarios.size() > 0) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        //Esperamos 3 segundos
                        Thread.sleep(3000);

                        //Obtenemos el primero
                        User primerUsuario = usuarios.get(0);
                        //Lo borramos!!!!! xD
                        dbHelper.delete(primerUsuario.getId());

                        //Esperamos 1 segundo mas
                        Thread.sleep(3000);
                        //Obtenemos la nueva cantidad
                        int cantidad = dbHelper.getCount();
                        //Mostramos la nueva cantidad
                        Log.e("AHORA", "Ahora existen: " + cantidad + " de usuarios registrados");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
}
