package com.example.classes.trabalho.banco_dados;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.classes.trabalho.entidades.ListaVeiculos;
import java.util.LinkedList;
import java.util.List;

public class DaoVeiculos {

    /*Conexão com o BD*/
    private SQLiteDatabase conexaoBD;
    public DaoVeiculos(SQLiteDatabase conexaoBD) {
        this.conexaoBD = conexaoBD;
    }

    public void inserirVeiculo(ListaVeiculos carro){

        ContentValues values = new ContentValues();

        values.put("NOME",carro.getNome());
        values.put("PLACA",carro.getPlaca());
        values.put("COR",carro.getCor());

        /*"insertOrThrow": insere dados*/
        conexaoBD.insertOrThrow("VEICULO",null,values); //SINTAXE: ([Nome da tabela],null,values)
    }


    public void atualizarVeiculo(ListaVeiculos carro){
        ContentValues values = new ContentValues();
        values.put("NOME",carro.getNome());
        values.put("ANO",carro.getPlaca());
        values.put("COR",carro.getCor());

        /*"update": atualiza tabela*/
        conexaoBD.update("VEICULO",values,"ID=?",new String[]{carro.getId().toString()});
    }


    public void removeVeiculo(Integer id){

        conexaoBD.delete("VEICULO","ID=?",new String[]{id.toString()});
    }


    /*Salva dentro da lista*/
    public List<ListaVeiculos> getCarros(){

        List<ListaVeiculos>Listcarros = new LinkedList<>();
        Cursor cursor;
        ListaVeiculos carro;

        cursor = conexaoBD.rawQuery("SELECT * FROM VEICULO ",null);

        cursor.moveToFirst();

        for (int i = 0; i < cursor.getCount(); i++) {

            carro=new ListaVeiculos();
            carro.setId(
                    cursor.getInt(cursor.getColumnIndex("ID"))
            );
            carro.setNome(cursor.getString(cursor.getColumnIndex("NOME")));
            carro.setPlaca(cursor.getString(cursor.getColumnIndex("PLACA")));
            carro.setCor(cursor.getString(cursor.getColumnIndex("COR")));

            Listcarros.add(carro);
            cursor.moveToNext();
        }
        return Listcarros;
    }

}
