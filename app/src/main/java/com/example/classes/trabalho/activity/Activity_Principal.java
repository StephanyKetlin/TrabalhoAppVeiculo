package com.example.classes.trabalho.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.classes.R;
import com.example.classes.trabalho.entidades.ListaAbastecimento;
import com.example.classes.trabalho.entidades.ListaVeiculos;
import com.example.classes.trabalho.banco_dados.DaoAbastecimento;
import com.example.classes.trabalho.banco_dados.DaoVeiculos;
import com.example.classes.trabalho.banco_dados.BancoDados;

import java.util.List;

public class Activity_Principal extends AppCompatActivity {

    /*Declarando as variaveis com referencia aos componentes da tela*/
    private ListView list_view; //ListView (Declarando um componente da tela)
    private  List<ListaVeiculos> Veiculos_List; //Declarando uma lista da classe "ListaVeiculos"
    private List<ListaAbastecimento> Abastecimento_List; //Declarando uma lista da classe "ListaVeiculos"
    private DaoVeiculos dao_veiculos; //Declarando classe DaoVeiculos
    private DaoAbastecimento dao_abastecimento; //Declarando classe DaoAbastecimento
    private BancoDados banco_dados; //Declarando classe BancoDados

    public Activity_Principal() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        /*Permite edição ao clicar em cima do ListView*/
        list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(),"valor "+i,Toast.LENGTH_LONG).show();

                ListaAbastecimento ListABS= (ListaAbastecimento) Abastecimento_List.get(i);

                Intent it = new Intent(Activity_Principal.this,Activity_Cadastro.class);//SINTAXE: Intent([Nome da classe que deseja ser o "link" de volta].this,[Nome da outra classe ("link" de ida)].class)
                /*O método "putExtra()" é usado para adicionar valores ao Bundle(pacote utilizados para a passagem de dados entre as Activity) interno*/
                it.putExtra("abs", ListABS); //SINTAXE: putExtra("chave",[valor]);
                startActivityForResult(it,3);//Carrega os dados da Intent (Carrega a pagina "NovaPagina")
            }
        });
        list_view.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                                                     @Override
                                                     public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                                                         dao_abastecimento.removeAbastecimento(Abastecimento_List.get(i).getId());
                                                         atualizaListagemAbastecimento();
                                                         return false;
                                                     }
                                                 }
        );

        /*Permite edição ao clicar em cima do ListView*/
        list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(),"valor "+i,Toast.LENGTH_LONG).show();

                ListaVeiculos carro= (ListaVeiculos) Veiculos_List.get(i);

                Intent it = new Intent(Activity_Principal.this,Activity_Cadastro.class);//SINTAXE: Intent([Nome da classe que deseja ser o "link" de volta].this,[Nome da outra classe ("link" de ida)].class)
                /*O método "putExtra()" é usado para adicionar valores ao Bundle(pacote utilizados para a passagem de dados entre as Activity) interno*/
                it.putExtra("carro", carro); //SINTAXE: putExtra("chave",[valor]);
                startActivityForResult(it,3);//Carrega os dados da Intent (Carrega a pagina "NovaPagina")
            }
        });
        list_view.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                                                 @Override
                                                 public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                                                     dao_veiculos.removeVeiculo(Veiculos_List.get(i).getId());
                                                     atualizaListagemVeiculos();
                                                     return false;
                                                 }
                                             }
        );



    }

    /*PROCEDIMENTO "AcaoBotao", aparece na barra de opções onClick*/
    public void Cadastro(View view){
        Intent it = new Intent(Activity_Principal.this, Activity_Cadastro.class); //SINTAXE: Intent([Nome da classe que deseja ser o "link" de volta].this,[Nome da outra classe ("link" de ida)].class)
        startActivityForResult(it, 0); //Carrega os dados da Intent (Carrega a pagina "NovaPagina")
    }

    /*PROCEDIMENTO "AcaoBotao", aparece na barra de opções onClick*/
    public void Abastecimento(View view){
        Intent it = new Intent(Activity_Principal.this, Activy_Abastecimento.class); //SINTAXE: Intent([Nome da classe que deseja ser o "link" de volta].this,[Nome da outra classe ("link" de ida)].class)
        startActivityForResult(it,1); //Carrega os dados da Intent (Carrega a pagina "NovaPagina")
    }

    /*PROCEDIMENTO de atualização de listView*/
    public void atualizaListagemVeiculos(){
        dao_veiculos = new DaoVeiculos(banco_dados.getReadableDatabase()); //Declara a classe "DaoVeiculos"
        Veiculos_List = dao_veiculos.getCarros(); //Chama o metodo getCarros da classe "CarroDaoBanco"

        /*ArrayAdapter: Manipula dados com base em Arrays (vetores/matrizes) ou java.util.List*/
        ArrayAdapter adapter = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item, Veiculos_List); //SINTAXE: ArrayAdapter(this,[layout],[lista]);
        list_view.setAdapter(adapter); //Coloca o ArrayAdapter dentro da ListView na tela
    }

    /*PROCEDIMENTO de atualização de listView*/
    public void atualizaListagemAbastecimento(){
        dao_abastecimento = new DaoAbastecimento(banco_dados.getReadableDatabase()); //Declara a classe "DaoVeiculos"
        Abastecimento_List = dao_abastecimento.getAbastecimento(); //Chama o metodo getCarros da classe "CarroDaoBanco"

        /*ArrayAdapter: Manipula dados com base em Arrays (vetores/matrizes) ou java.util.List*/
        ArrayAdapter adapter = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item, Abastecimento_List); //SINTAXE: ArrayAdapter(this,[layout],[lista]);
        list_view.setAdapter(adapter); //Coloca o ArrayAdapter dentro da ListView na tela
    }

}