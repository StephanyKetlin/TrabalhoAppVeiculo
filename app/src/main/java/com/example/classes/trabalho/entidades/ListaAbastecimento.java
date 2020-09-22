package com.example.classes.trabalho.entidades;

import java.io.Serializable;
import java.util.List;

public class ListaAbastecimento implements Serializable {

    /*Declarando as variaveis*/
    private Integer id;
    private String litros;
    private String cpf;
    private List<ListaAbastecimento> Abastecimento_List;

    public Integer getId() { //GET Retorna o valor da variavel
        return id;
    }

    public void setId(Integer id) { // SET Recebe valores e altera o valor da variavel
        this.id = id;
    }

    public String getLitros() {
        return litros;
    }

    public void setLitros(String litros) {
        this.litros = litros;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public List<ListaAbastecimento> getAbastecimento_List() {
        return Abastecimento_List;
    }

    public void setAbastecimento_List(List<ListaAbastecimento> abastecimento_List) {
        Abastecimento_List = abastecimento_List;
    }

    @Override
    public String toString() {
        return "Carro{" +
                "CPF='" + cpf + '\'' +
                ", Litros='" + litros + '\'' +
                '}';
    }
}
