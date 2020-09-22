package com.example.classes.trabalho.entidades;

import java.io.Serializable;
import java.util.List;

public class ListaVeiculos implements Serializable {

    /*Declarando as variaveis*/
    private Integer id;
    private String cor;
    private String nome;
    private String placa;
    private List<ListaVeiculos> Veiculos_List;

    public Integer getId() { //GET Retorna o valor da variavel
        return id;
    }

    public void setId(Integer id) { // SET Recebe valores e altera o valor da variavel
        this.id = id;
    }

    public List<ListaVeiculos> getVeiculos_List() {
        return Veiculos_List;
    }

    public void setVeiculos_List(List<ListaVeiculos> veiculos_List) {
        Veiculos_List = veiculos_List;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    @Override
    public String toString() {
        return "Carro{" +
                "nome='" + nome + '\'' +
                ", placa='" + placa + '\'' +
                ", cor=" + cor +
                '}';
    }
}
