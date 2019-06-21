package com.lucas.vieira.gerenciadorhotel.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DAO extends SQLiteOpenHelper {
    private  static final int VERSAO = 1;
    private static final String TABELA = "Hotel";
    private static final String TABELA2 = "Quarto";
    private static final String TABELA3 = "Funcionario";
    private static final String DATABASE = "GerenciadorHotel";

    public DAO(Context context) {
        super(context, DATABASE, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {

        //String SQL_PERIODO = "CREATE TABLE Periodo(codPeriodo INTEGER PRIMARY KEY AUTOINCREMENT, nomePeriodo TEXT)";

        //String SQL_DISCIPLINA = "CREATE TABLE Disciplina(codDiscip INTEGER PRIMARY KEY AUTOINCREMENT, nomeDiscip TEXT, " +
        //        "periodoDiscip INTEGER, piDiscip INTEGER, FOREIGN KEY(periodoDiscip) REFERENCES Periodo(codPeriodo))";

        //String SQL_NOTAS = "CREATE TABLE Notas (codNota INTEGER PRIMARY KEY AUTOINCREMENT, av1 NUMERIC, av12 NUMERIC, av2 NUMERIC, av3 NUMERIC, " +
        //        "disciplina INTEGER, FOREIGN KEY(disciplina) REFERENCES Disciplina(codDiscip))";

        String SQL_HOTEL = "CREATE TABLE " + TABELA + " (ID_HOTEL INTEGER PRIMARY KEY AUTOINCREMENT, NOME_HOTEL TEXT, CNPJ_HOTEL TEXT, TELEFONE_HOTEL TEXT, ENDERECO_HOTEL TEXT)";

        String SQL_QUARTO = "CREATE TABLE " + TABELA2 + " (ID_QUARTO INTEGER PRIMARY KEY AUTOINCREMENT, ID_HOTEL INTEGER, NUMERO_QUARTO INTEGER, VALOR_DIARIA FLOAT, TELEFONE_QUARTO TEXT, FOREIGN KEY(ID_HOTEL) REFERENCES Hotel(ID_HOTEL))";

        String SQL_FUNCIONARIO = "CREATE TABLE " + TABELA3 + " (ID_FUNCIONARIO INTEGER PRIMARY KEY AUTOINCREMENT, ID_HOTEL INTEGER, NOME_FUNCIONARIO TEXT, IDADE_FUNCIONARIO INTEGER, RG_FUNCIONARIO TEXT, FOREIGN KEY(ID_HOTEL) REFERENCES Hotel(ID_HOTEL))";

        String ddl = "CREATE TABLE " + TABELA
                +" (ID_HOTEL INTEGER PRIMARY KEY,"
                +" NOME_HOTEL TEXT,"
                +" CNPJ_HOTEL TEXT,"
                +" TELEFONE_HOTEL TEXT,"
                +" ENDERECO_HOTEL TEXT);";

        String ddl2 = "CREATE TABLE " + TABELA2
                +" (ID_QUARTO INTEGER PRIMARY KEY,"
                + "ID_HOTEL INTEGER NOT NULL,"
                +" NUMERO_QUARTO INTEGER,"
                +" VALOR_DIARIA FLOAT,"
                +" TELEFONE_QUARTO TEXT,"
                +" FOREIGN KEY(ID_HOTEL) REFERENCES Hotel(ID_HOTEL));";

        String ddl3 = "CREATE TABLE " + TABELA3
                +" (ID_FUNCIONARIO INTEGER PRIMARY KEY,"
                + "ID_HOTEL INTEGER NOT NULL,"
                +" NOME_FUNCIONARIO TEXT,"
                +" IDADE_FUNCIONARIO INTEGER,"
                +" RG_FUNCIONARIO TEXT,"
                +" FOREIGN KEY(ID_HOTEL) REFERENCES Hotel(ID_HOTEL));";

        database.execSQL(SQL_HOTEL);
        database.execSQL(SQL_QUARTO);
        database.execSQL(SQL_FUNCIONARIO);
        //db.execSQL(SQL_NOTAS);

        //database.execSQL(ddl);
        //database.execSQL(ddl2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int i, int i1) {
        String SQL_QUARTO1 = "INSERT INTO  " + TABELA2 + " (ID_HOTEL, NUMERO_QUARTO, VALOR_DIARIA, TELEFONE_QUARTO) values(1, 12, 100,'957805023')";

        database.execSQL(SQL_QUARTO1);
    }
}
