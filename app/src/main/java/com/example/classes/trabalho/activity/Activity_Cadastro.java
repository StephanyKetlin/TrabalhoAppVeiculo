package com.example.classes.trabalho.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import com.example.classes.R;
import com.example.classes.trabalho.entidades.ListaVeiculos;
import com.example.classes.trabalho.banco_dados.BancoDados;
import com.example.classes.trabalho.banco_dados.DaoVeiculos;


public class Activity_Cadastro extends AppCompatActivity {

    /*Declarando as variaveis com referencia aos componentes da tela*/
    private EditText nome;
    private EditText placa;
    private EditText cor;
    private Button Botao_Salvar;
    private Button Botao_Excluir;
    private DaoVeiculos dao_veiculos;
    private ListaVeiculos Veiculos_List;
    private BancoDados banco_dados;

    @Override
    /*"onCreate" é  como se fosse uma "Main"*/
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro); //Seta o Layout utilizado (xml)

        /*Captura do XML (Mapeamento)*/
        nome = findViewById(R.id.idnome);
        placa = findViewById(R.id.idplaca);
        cor = findViewById(R.id.idcor);

        Intent it =getIntent();
        Veiculos_List= (ListaVeiculos) it.getSerializableExtra("Veiculos_List");

        if(Veiculos_List!=null) {
            nome.setText(Veiculos_List.getNome());
            placa.setText(Veiculos_List.getPlaca());
            cor.setText(Veiculos_List.getCor().toString());
        }
    }

    /*PROCEDIMENTO "AcaoBotao", aparece na barra de opções onClick*/
    public  void salvarVeiculo(View view){

        banco_dados = new BancoDados(this); //Declarando o BD
        dao_veiculos = new DaoVeiculos(banco_dados.getWritableDatabase());

        if(Veiculos_List==null)
            Veiculos_List = new ListaVeiculos();//Declaro a classe "ListaVeiculos" na variavel Veiculos_List

        /*Pego os dados inseridos e preencho a classe "Carro"*/
        if(valida()) {
            /*Executa a função setNome,setAno e setPlaca da classe "Carro"*/
            Veiculos_List.setNome(nome.getText().toString());
            Veiculos_List.setPlaca(placa.getText().toString());
            Veiculos_List.setCor(cor.getText().toString());

            //Salvo carro na lista
            if(Veiculos_List.getId()!=null && Veiculos_List.getId()>0)
                dao_veiculos.atualizarVeiculo(Veiculos_List);
            else
                dao_veiculos.inserirVeiculo(Veiculos_List);


            //Verifico se foi salvo
            //  System.out.println(CarroDao.getDados());
            setResult(RESULT_OK);
            super.onBackPressed(); //Volta p/ Activy(Pagina) anterior
        }
    }

    public boolean valida(){
        return validaEditText(nome) &&validaEditText(placa)&&validaEditText(cor)?true:false;
    }

    //qualquer edittext
    public boolean validaEditText(EditText editText){
        if(!TextUtils.isEmpty(editText.getText().toString().trim())){
            return true;
        }else{
            editText.setError("O Campo deve ser preenchido");
            editText.requestFocus();
            // Toast.makeText(this,"O Campo nome deve ser preenchido",Toast.LENGTH_LONG).show();
            return  false;
        }


}