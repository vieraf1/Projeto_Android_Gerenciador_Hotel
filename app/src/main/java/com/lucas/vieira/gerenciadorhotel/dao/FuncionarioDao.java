package com.lucas.vieira.gerenciadorhotel.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.lucas.vieira.gerenciadorhotel.modelo.Funcionario;
import com.lucas.vieira.gerenciadorhotel.modelo.Hotel;

import java.util.ArrayList;
import java.util.List;

public class FuncionarioDao extends DAO {

    private  static final int VERSAO = 1;
    private static final String TABELA = "Funcionario";
    private static final String DATABASE = "GerenciadorHotel";

    public FuncionarioDao(Context context)
    {
        super(context);
    }

    public void insere(Funcionario func) {
        ContentValues values = new ContentValues();
        values.put("ID_HOTEL", func.getIdHotel());
        values.put("NOME_FUNCIONARIO", func.getNome());
        values.put("IDADE_FUNCIONARIO", func.getIdade());
        values.put("RG_FUNCIONARIO ", func.getRg());

        getWritableDatabase().insert(TABELA, null, values);
    }

    public List<Funcionario> getLista()
    {
        List<Funcionario> funcs = new ArrayList<Funcionario>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + TABELA + ";", null);

        while (c.moveToNext())
        {
            Funcionario func = new Funcionario();
            func.setId(c.getLong(c.getColumnIndex("ID_FUNCIONARIO")));
            func.setNome(c.getString(c.getColumnIndex("NOME_FUNCIONARIO")));
            func.setIdade(c.getInt(c.getColumnIndex("IDADE_FUNCIONARIO")));
            func.setRg(c.getString(c.getColumnIndex("RG_FUNCIONARIO")));

            Hotel hotel = new Hotel();
            hotel.setId(c.getLong(c.getColumnIndex("ID_HOTEL")));
            func.setIdHotel(c.getLong(c.getColumnIndex("ID_HOTEL")));
            func.setHotel(hotel);

            funcs.add(func);
        }
        c.close();
        return funcs;
    }

    public void deletar(Funcionario func)
    {
        String [] args = {func.getId().toString()};
        getWritableDatabase().delete(TABELA, "ID_FUNCIONARIO=?", args);
    }

    public void alterar(Funcionario func)
    {
        ContentValues values = new  ContentValues();
        values.put("ID_HOTEL", func.getIdHotel());
        values.put("NOME_FUNCIONARIO", func.getNome());
        values.put("IDADE_FUNCIONARIO ", func.getIdade());
        values.put("RG_FUNCIONARIO", func.getRg());

        getWritableDatabase().update(TABELA, values, "ID_FUNCIONARIO=?", new String[] {func.getId().toString()});
    }
}
