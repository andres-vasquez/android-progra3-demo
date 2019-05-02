package edu.upb.progra3demo.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import edu.upb.progra3demo.model.User;

public class DatabaseHelper {
    private SQLiteDatabase mDatabase;

    public DatabaseHelper(Context context) {
        Database instancia = new Database(context);
        this.mDatabase = instancia.getWritableDatabase();
    }


    public void insert(User user) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("usuario", user.getNombreUsuario());
        contentValues.put("password", user.getPassword());
        contentValues.put("edad", user.getEdad());
        contentValues.put("email", user.getEmail());
        contentValues.put("codigoUpb", user.getCodigoUpb());
        this.mDatabase.insert("usuarios",
                null,
                contentValues);
        this.mDatabase.close();
    }

    public boolean login(String usuario, String password) {
        String[] params = new String[2];
        params[0] = usuario;
        params[1] = password;

        Cursor cursor = this.mDatabase.rawQuery("SELECT codigoUpb FROM usuarios" +
                " WHERE usuario=? AND password = ?", params);

        if (cursor.moveToFirst()) {
            Log.d("CodigoUPB", "" + cursor.getInt(0));
            return true;
        } else {
            return false;
        }
    }
}
