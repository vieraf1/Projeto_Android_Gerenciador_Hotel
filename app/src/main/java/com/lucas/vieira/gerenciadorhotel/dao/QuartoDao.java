package com.lucas.vieira.gerenciadorhotel.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.lucas.vieira.gerenciadorhotel.modelo.Hotel;
import com.lucas.vieira.gerenciadorhotel.modelo.Quarto;

import java.util.ArrayList;
import java.util.List;

public class QuartoDao extends DAO {
    private  static final int VERSAO = 1;
    private static final String TABELA = "Quarto";
    private static final String DATABASE = "GerenciadorHotel";

    public QuartoDao(Context context)
    {
        super(context);
    }


    public void insere(Quarto quarto) {
        ContentValues values = new ContentValues();
        values.put("ID_HOTEL", quarto.getIdHotel());
        values.put("NUMERO_QUARTO", quarto.getNumeroQuarto());
        values.put("VALOR_DIARIA", quarto.getValorDiaria());
        values.put("TELEFONE_QUARTO", quarto.getTelefoneQuarto());

        getWritableDatabase().insert(TABELA, null, values);
    }

    public List<Quarto> getLista()
    {
        List<Quarto> quarts = new ArrayList<Quarto>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + TABELA + ";", null);

        while (c.moveToNext())
        {
            Quarto quarto = new Quarto();
            quarto.setId(c.getLong(c.getColumnIndex("ID_QUARTO")));
            quarto.setNumeroQuarto(c.getInt(c.getColumnIndex("NUMERO_QUARTO")));
            quarto.setValorDiaria(c.getFloat(c.getColumnIndex("VALOR_DIARIA")));
            quarto.setTelefoneQuarto(c.getString(c.getColumnIndex("TELEFONE_QUARTO")));

            Hotel hotel = new Hotel();
            hotel.setId(c.getLong(c.getColumnIndex("ID_HOTEL")));
            quarto.setIdHotel(c.getLong(c.getColumnIndex("ID_HOTEL")));
            quarto.setHotel(hotel);

            quarts.add(quarto);
        }
        c.close();
        return quarts;
    }

    public void deletar(Quarto quarto)
    {
        String [] args = {quarto.getId().toString()};
        getWritableDatabase().delete(TABELA, "ID_QUARTO=?", args);
    }

    public void alterar(Quarto quarto)
    {
        ContentValues values = new  ContentValues();
        values.put("ID_HOTEL", quarto.getIdHotel());
        values.put("NUMERO_QUARTO", quarto.getNumeroQuarto());
        values.put("VALOR_DIARIA", quarto.getValorDiaria());
        values.put("TELEFONE_QUARTO", quarto.getTelefoneQuarto());

        getWritableDatabase().update(TABELA, values, "ID_QUARTO=?", new String[] {quarto.getId().toString()});
    }

}
