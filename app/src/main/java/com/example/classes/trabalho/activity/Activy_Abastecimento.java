package com.example.classes.trabalho.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import com.example.classes.R;
import com.example.classes.trabalho.banco_dados.DaoAbastecimento;
import com.example.classes.trabalho.banco_dados.BancoDados;
import com.example.classes.trabalho.banco_dados.DaoVeiculos;
import com.example.classes.trabalho.entidades.ListaAbastecimento;
import com.example.classes.trabalho.entidades.ListaVeiculos;

public class Activy_Abastecimento extends AppCompatActivity {

    /*Declarando as variaveis com referencia aos componentes da tela*/
    private EditText litros;
    private EditText cpf;
    private ListaAbastecimento Abastecimento_List;
    private DaoAbastecimento dao_abastecimento;
    private BancoDados banco_dados;


    @Override
    /*"onCreate" é  como se fosse uma "Main"*/
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abastecimento); //Seta o Layout utilizado (xml)

        /*Captura do XML (Mapeamento)*/
        litros = findViewById(R.id.idlitros);
        cpf = findViewById(R.id.idcpf);

        Intent it = getIntent();
        Abastecimento_List = (ListaAbastecimento) it.getSerializableExtra("Abastecimento_List");

        if (Abastecimento_List != null) {
            litros.setText(Abastecimento_List.getLitros());
            cpf.setText(Abastecimento_List.getCpf().toString());
        }
    }

    /*PROCEDIMENTO "AcaoBotao", aparece na barra de opções onClick*/
    public void salvarVeiculo(View view) {

        banco_dados = new BancoDados(this); //Declarando o BD
        dao_abastecimento = new DaoAbastecimento(banco_dados.getWritableDatabase());

        if (Abastecimento_List == null)
            Abastecimento_List = new ListaAbastecimento();//Declaro a classe "ListaVeiculos" na variavel Veiculos_List

        /*Pego os dados inseridos e preencho a classe "Carro"*/
        if (valida()) {
            /*Executa a função setNome,setAno e setPlaca da classe "Carro"*/
            Abastecimento_List.setLitros(litros.getText().toString());
            Abastecimento_List.setCpf(cpf.getText().toString());

            //Salvo carro na lista
            if (Abastecimento_List.getId() != null && Abastecimento_List.getId() > 0)
                dao_abastecimento.atualizarAbastecimento(Abastecimento_List);
            else
                dao_abastecimento.inserirAbastecimento(Abastecimento_List);


            //Verifico se foi salvo
            //  System.out.println(CarroDao.getDados());
            setResult(RESULT_OK);
            super.onBackPressed(); //Volta p/ Activy(Pagina) anterior
        }
    }

    public boolean valida() {
        return validaEditText(litros) && validaEditText(cpf)? true : false;
    }

    //qualquer edittext
    public boolean validaEditText(EditText editText) {
        if (!TextUtils.isEmpty(editText.getText().toString().trim())) {
            return true;
        } else {
            editText.setError("O Campo deve ser preenchido");
            editText.requestFocus();
            // Toast.makeText(this,"O Campo nome deve ser preenchido",Toast.LENGTH_LONG).show();
            return false;
        }
    }

    public void RemoverAbastecimento(View view){
        ListaAbastecimento Abastecimento_List = new ListaAbastecimento();
        dao_abastecimento.removeAbastecimento(Abastecimento_List);
    }
}