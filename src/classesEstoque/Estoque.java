/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classesEstoque;

import classesProduto.Produto;
import java.util.ArrayList;

/**
 *
 * @author kaua
 */
public class Estoque {
    private int id;
    private String nome;
    private int idProduto;
    private int qtd;
    private String sabor;
    private Double valor;
    
   private static ArrayList<Estoque> estoque = new ArrayList<Estoque>();

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQtd() {
        return qtd;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }

    public ArrayList<Estoque> getEstoque() {
        return estoque;
    }

    public void setEstoque(ArrayList<Estoque> estoque) {
        this.estoque = estoque;
    }
    
    public void adicionar(Estoque produto){
        estoque.add(produto);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSabor() {
        return sabor;
    }

    public void setSabor(String sabor) {
        this.sabor = sabor;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }


    public void gerarRelatorio(){
        for(Estoque produto : estoque){
            System.out.println(produto.getNome()+ " " + produto.getQtd());
        }
    }
}
