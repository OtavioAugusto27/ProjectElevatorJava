package model;

import java.util.List;
import java.util.ArrayList;

public class Predio{
    private String nome;
    private List<Elevator> elevadores;

    public Predio(String nome) {
        this.nome = nome;
        this.elevadores = new ArrayList<>();
    }
    public void adicionarElevador(Elevator elevador){
        if(elevador == null){
            throw new NullPointerException("Erro ao adicionar elevador");
        }
        elevadores.add(elevador);
    }
    public Elevator getElevador(int indice){
        if(indice<0 || indice>=elevadores.size()){
            throw new IndexOutOfBoundsException("Indice inválido");
        }
        return elevadores.get(indice);
    }
    public int getQuantidadeElevadores(){
        return elevadores.size();
    }
    public String getNome(){
        return nome;
    }
}