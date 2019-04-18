package edu.upb.progra3demo;

import android.content.Context;
import android.content.Intent;
import android.print.PrinterId;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import edu.upb.progra3demo.model.User;

public class RegisterActivity extends AppCompatActivity {
    private static final String LOG = RegisterActivity.class.getName();
    private Context mContext;

    private LinearLayout padre;
    private TextView txtUsuario;
    private EditText usuario;

    private TextView txtPassword;
    private EditText password;

    private TextView txtEdad;
    private EditText edad;

    private TextView txtEmail;
    private EditText email;

    private TextView txtCodigoUpb;
    private EditText codigoUpb;


    private LinearLayout botones;
    private Button enviar;
    private Button limpiar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.w(LOG, "onCreate");

        mContext = this;

        //Padre
        padre = new LinearLayout(mContext);
        padre.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, //Width
                ViewGroup.LayoutParams.MATCH_PARENT)); //Height
        padre.setOrientation(LinearLayout.VERTICAL);
        padre.setPadding(25, 25, 25, 25);
        //padre.setBackgroundColor(mContext.getResources().getColor(R.color.colorPrimary));


        //Usuario
        txtUsuario = new TextView(mContext);
        txtUsuario.setText(getString(R.string.usuario));
        txtUsuario.setTextSize(26);
        padre.addView(txtUsuario);

        usuario = new EditText(mContext);
        padre.addView(usuario);

        //Password
        txtPassword = new TextView(mContext);
        txtPassword.setTextSize(26);
        txtPassword.setText(getString(R.string.password));
        padre.addView(txtPassword);

        password = new EditText(mContext);
        password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        padre.addView(password);

        //Edad
        txtEdad = new TextView(mContext);
        txtEdad.setTextSize(26);
        txtEdad.setText(getString(R.string.edad));
        padre.addView(txtEdad);

        edad = new EditText(mContext);
        edad.setInputType(InputType.TYPE_CLASS_NUMBER);

        int maxLength = 2;
        edad.setFilters(new InputFilter[]{new InputFilter.LengthFilter(maxLength)});
        padre.addView(edad);

        //Email
        txtEmail = new TextView(mContext);
        txtEmail.setTextSize(26);
        txtEmail.setText(getString(R.string.email));
        padre.addView(txtEmail);

        email = new EditText(mContext);
        email.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
        padre.addView(email);

        //Codigo UPB
        txtCodigoUpb = new TextView(mContext);
        txtCodigoUpb.setTextSize(26);
        txtCodigoUpb.setText(getString(R.string.codigoUpb));
        padre.addView(txtCodigoUpb);

        codigoUpb = new EditText(mContext);
        int maxLengthCodigo = 7;
        codigoUpb.setFilters(new InputFilter[]{new InputFilter.LengthFilter(maxLengthCodigo)});
        codigoUpb.setInputType(InputType.TYPE_CLASS_NUMBER);
        padre.addView(codigoUpb);

        //Panel de botones
        botones = new LinearLayout(mContext);
        botones.setOrientation(LinearLayout.HORIZONTAL);


        enviar = new Button(mContext);
        enviar.setText(getString(R.string.enviar));
        enviar.setLayoutParams(new LinearLayout.LayoutParams(
                0, //Width
                ViewGroup.LayoutParams.MATCH_PARENT,
                1)); //Height
        botones.addView(enviar);

        limpiar = new Button(mContext);
        limpiar.setText(getString(R.string.limpiar));
        limpiar.setLayoutParams(new LinearLayout.LayoutParams(
                0, //Width
                ViewGroup.LayoutParams.MATCH_PARENT,
                1)); //Height
        botones.addView(limpiar);
        padre.addView(botones);

        setContentView(padre);
        addEvents();
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

    private void addEvents() {
        limpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                usuario.setText("");
                password.setText("");
                edad.setText("");
                email.setText("");
                codigoUpb.setText("");
            }
        });

        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateForm();
            }
        });
    }

    private void validateForm() {
        if (usuario.getText().toString().isEmpty()) {
            Toast.makeText(mContext, "Ingrese el usuario por favor", Toast.LENGTH_SHORT).show();
            return;
        }

        if (password.getText().toString().isEmpty()) {
            password.setError("Ingrese el password por favor");
            return;
        }

        if (edad.getText().toString().isEmpty()) {
            Snackbar snackbar = Snackbar.make(padre, "Ingrese la edad por favor", Snackbar.LENGTH_LONG);
            snackbar.show();
            return;
        }

        if (email.getText().toString().isEmpty()) {
            email.setError("Ingrese su email por favor");
            return;
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()) {
            email.setError("Ingrese un email valido por favor");
            return;
        }

        if (codigoUpb.getText().toString().isEmpty()) {
            codigoUpb.setError("Ingrese su codigo UPB por favor");
            return;
        }

        User alumno = new User();
        alumno.setNombreUsuario(usuario.getText().toString());
        alumno.setPassword(password.getText().toString());
        alumno.setEdad(Integer.parseInt(edad.getText().toString()));
        alumno.setEmail(email.getText().toString());
        alumno.setCodigoUpb(Integer.parseInt(codigoUpb.getText().toString()));

        String json = new Gson().toJson(alumno);
        Log.e("UsuarioEnviado", json);

        Intent intent = new Intent();
        intent.putExtra(Constants.KEY_REGISTRAR_USUARIO, json);
        setResult(RESULT_OK, intent); //OK: funciono, intent --> retornando el valor
        finish(); //Cierra el activity
    }
}
