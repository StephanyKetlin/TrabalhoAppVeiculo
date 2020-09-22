package com.example.classes.trabalho.banco_dados;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.classes.trabalho.entidades.ListaAbastecimento;
import java.util.LinkedList;
import java.util.List;

public class DaoAbastecimento {

    /*Conexão com o BD*/
    private SQLiteDatabase conexaoBD;
    public DaoAbastecimento(SQLiteDatabase conexaoBD) {
        this.conexaoBD = conexaoBD;
    }

    public void inserirAbastecimento(ListaAbastecimento abs){

        ContentValues values = new ContentValues();

        values.put("LITROS",abs.getLitros());
        values.put("CPF",abs.getCpf());

        /*"insertOrThrow": insere dados*/
        conexaoBD.insertOrThrow("ABASTECIMENTO",null,values); //SINTAXE: ([Nome da tabela],null,values)
    }

    public void atualizarAbastecimento(ListaAbastecimento abs){
        ContentValues values = new ContentValues();
        values.put("LITROS",abs.getLitros());
        values.put("CPF",abs.getCpf());

        /*"update": atualiza tabela*/
        conexaoBD.update("ABASTECIMENTO",values,"ID=?",new String[]{abs.getId().toString()});
    }

    public void removeAbastecimenoto(Integer id){

        conexaoBD.delete("ABASTECIMENTO","ID=?",new String[]{id.toString()});
    }

    /*Salva dentro da lista*/
    public List<ListaAbastecimento> getAbastecimento(){

        List<ListaAbastecimento>listABS = new LinkedList<>();
        Cursor cursor;
        ListaAbastecimento abs;

        cursor = conexaoBD.rawQuery("SELECT * FROM ABASTECIMENTO ",null);

        cursor.moveToFirst();

        for (int i = 0; i < cursor.getCount(); i++) {

            abs=new ListaAbastecimento();
            abs.setId(
                    cursor.getInt(cursor.getColumnIndex("ID"))
            );
            abs.setLitros(cursor.getString(cursor.getColumnIndex("LITROS")));
            abs.setCpf(cursor.getString(cursor.getColumnIndex("CPF")));

            listABS.add(abs);
            cursor.moveToNext();
        }
        return listABS;

    }

}
