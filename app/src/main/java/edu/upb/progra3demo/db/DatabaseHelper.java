package edu.upb.progra3demo.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import edu.upb.progra3demo.model.User;

public class DatabaseHelper {
    private SQLiteDatabase mDatabase;


    public DatabaseHelper(Context context) {
        Database db = new Database(context);
        mDatabase = db.getWritableDatabase();
    }

    public void insert(User user) {
        ContentValues values = new ContentValues();
        values.put("usuario", user.getNombreUsuario());
        values.put("password", user.getPassword());
        values.put("edad", user.getEdad());
        values.put("email", user.getEmail());
        values.put("codigoUpb", user.getCodigoUpb());
        mDatabase.insert("usuarios", null, values);
        mDatabase.close();
    }

    public List<User> readAll() {
        List<User> users = new ArrayList<>();
        Cursor cursorUser = mDatabase.rawQuery("SELECT id, usuario, edad, email, codigoUpb FROM usuarios", null);
        if (cursorUser.moveToFirst()) {
            do {
                User user = new User();
                user.setNombreUsuario(cursorUser.getString(0));
                user.setEdad(cursorUser.getInt(1));
                user.setEmail(cursorUser.getString(2));
                user.setCodigoUpb(cursorUser.getInt(3));
            } while (cursorUser.moveToNext());
        }

        return users;
    }

    public boolean login(String username, String password) {
        String[] params = new String[2];
        params[0] = username;
        params[1] = password;

        Cursor cursorUser = mDatabase.rawQuery("SELECT id FROM usuarios WHERE usuario=? and password=?", params);
        return cursorUser.moveToFirst();
    }
}
