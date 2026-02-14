/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classesProduto;

import java.util.ArrayList;

/**
 *
 * @author kauam
 */
public class Produto {
    private int id;
    private String nome;
    private double valorUnt;

    public static ArrayList<Produto> produto = new ArrayList<Produto>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getValorUnt() {
        return valorUnt;
    }

    public void setValorUnt(double valorUnt) {
        this.valorUnt = valorUnt;
    }

    public static ArrayList<Produto> getProduto() {
        return produto;
    }

    public static void setProduto(ArrayList<Produto> produto) {
        Produto.produto = produto;
    }


    

}
