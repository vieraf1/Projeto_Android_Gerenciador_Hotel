package com.lucas.vieira.gerenciadorhotel.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.lucas.vieira.gerenciadorhotel.modelo.Hotel;

import java.util.ArrayList;
import java.util.List;

public class HotelDao extends DAO {
    private  static final int VERSAO = 1;
    private static final String TABELA = "Hotel";
    private static final String DATABASE = "GerenciadorHotel";

    public HotelDao(Context context)
    {
        super(context);
    }

    public void insere(Hotel hotel) {
        ContentValues values = new ContentValues();
        values.put("NOME_HOTEL", hotel.getNome());
        values.put("CNPJ_HOTEL", hotel.getCnpj());
        values.put("TELEFONE_HOTEL", hotel.getTelefone());
        values.put("ENDERECO_HOTEL", hotel.getEndereco());

        getWritableDatabase().insert(TABELA, null, values);

    }

    public List<Hotel> getLista()
    {
        List<Hotel> hotels = new ArrayList<Hotel>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + TABELA + ";", null);

        while (c.moveToNext())
        {
            Hotel hotel = new Hotel();
            hotel.setId(c.getLong(c.getColumnIndex("ID_HOTEL")));
            hotel.setNome(c.getString(c.getColumnIndex("NOME_HOTEL")));
            hotel.setCnpj(c.getString(c.getColumnIndex("CNPJ_HOTEL")));
            hotel.setTelefone(c.getString(c.getColumnIndex("TELEFONE_HOTEL")));
            hotel.setEndereco(c.getString(c.getColumnIndex("ENDERECO_HOTEL")));

            hotels.add(hotel);
        }
        c.close();
        return hotels;
    }

    public Hotel seleciona(Hotel hotel)
    {
        Hotel hot = new Hotel();
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + TABELA
                + " WHERE ID_HOTEL = " + hotel.getId() + ";", null);

        hot.setId(c.getLong(c.getColumnIndex("ID_HOTEL")));
        hot.setNome(c.getString(c.getColumnIndex("NOME_HOTEL")));
        hot.setTelefone(c.getString(c.getColumnIndex("TELEFONE_HOTEL")));
        hot.setEndereco(c.getString(c.getColumnIndex("ENDERECO_HOTEL")));
        hot.setCnpj(c.getString(c.getColumnIndex("CNPJ_HOTEL")));

        c.close();
        return hot;
    }

    public void deletar(Hotel hotel)
    {
        String [] args = {hotel.getId().toString()};
        getWritableDatabase().delete(TABELA, "ID_HOTEL=?", args);
    }

    public void alterar(Hotel hotel)
    {
        ContentValues values = new  ContentValues();
        values.put("NOME_HOTEL", hotel.getNome());
        values.put("CNPJ_HOTEL", hotel.getCnpj());
        values.put("TELEFONE_HOTEL", hotel.getTelefone());
        values.put("ENDERECO_HOTEL", hotel.getEndereco());

        getWritableDatabase().update(TABELA, values, "ID_HOTEL=?", new String[] {hotel.getId().toString()});
    }
}
