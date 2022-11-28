package com.example.appdespesas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class BancoDeDados extends SQLiteOpenHelper {
    public BancoDeDados(@Nullable Context context) {
        super(context, "despesas", null, 1);
    }

    public long insereDespesa(String descricao, String data, double valor) {
        SQLiteDatabase banco = this.getWritableDatabase();
        ContentValues registro = new ContentValues();
        registro.put("descricao", descricao);
        registro.put("data", data);
        registro.put("valor", valor);
        long id = banco.insert("gastos", null, registro);
        banco.close();
        return id;
    }

    public ArrayList<String> consulta() {
        SQLiteDatabase banco = this.getReadableDatabase();
        String sql = "SELECT * FROM gastos";
        ArrayList<String> resultado = null;
        Cursor c = banco.rawQuery(sql, null);
        if (c.moveToFirst()) {
            resultado = new ArrayList<String>();
            do {
                String registro;
                registro = "\n Descricao : " + c.getString(1);
                registro+= "\n Data      : " + c.getString(2);
                registro+= "\n Valor     : " + c.getDouble(3);
                resultado.add(registro);
            } while (c.moveToNext());
        }
        banco.close();
        return resultado;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
    String sql = "CREATE TABLE gastos (" +
                 "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                 "descricao TEXT," +
                 "data TEXT," +
                 "valor REAL" +
            ")";
    sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
