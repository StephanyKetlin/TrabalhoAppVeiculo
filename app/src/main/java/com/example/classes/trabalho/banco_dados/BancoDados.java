package com.example.classes.trabalho.banco_dados;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

public class BancoDados extends SQLiteOpenHelper {


    public BancoDados(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public BancoDados(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version, @Nullable DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    public BancoDados(@Nullable Context context, @Nullable String name, int version, @NonNull SQLiteDatabase.OpenParams openParams) {
        super(context, name, version, openParams);
    }

    @Override
    /*"onCreate" é  como se fosse uma "Main"*/
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        /*Comando direto no BD*/
        sqLiteDatabase.execSQL("CREATE TABLE  VEICULO ( " +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                " NOME VARCHAR(50) NOT NULL," +
                "PLACA VARCHAR(8)," +
                "COR  VARCHAR(20)" +
                ")"
        );

        /*Comando direto no BD*/
        sqLiteDatabase.execSQL("CREATE TABLE  ABASTECIMENTO ( " +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                "LITROS FLOAT NOT NULL," +
                "CPF VARCHAR(11)" +
                ")"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
