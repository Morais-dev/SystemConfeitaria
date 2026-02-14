/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classesFinancas;

import classesProduto.Produto;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author kauam
 */
public class Vendas {
    private Date data;
    private String tipoPagamento;
    private ArrayList<Produto> produto;
    private double desconto;
    private double valor;
    
    private static ArrayList<Vendas>  vendas = new ArrayList<Vendas>();

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getTipoPagamento() {
        return tipoPagamento;
    }

    public void setTipoPagamento(String tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }

    
    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }


    public ArrayList<Vendas> getVendas() {
        return vendas;
    }

    public void setVendas(ArrayList<Vendas> vendas) {
        this.vendas = vendas;
    }
    
    public void adicionar(Vendas venda){
        vendas.add(venda);
    }

    public ArrayList<Produto> getProduto() {
        return produto;
    }

    public void setProduto(ArrayList<Produto> produto) {
        this.produto = produto;
    }

    public double getDesconto() {
        return desconto;
    }

    public void setDesconto(double desconto) {
        this.desconto = desconto;
    }
    
    public double getValorVenda(ArrayList<Produto> lista){
        double valor = 0;
        for(Produto p : lista){
           valor += p.getValorUnt();
           
        }
        
        return valor;
    }
    
    public void resumoDaVenda(ArrayList<Produto> lista){
        for(Produto p : lista){
            p.getNome();
            p.getValorUnt();
        }
    }
    
    public double ganhosTotais(ArrayList<Vendas> lista){
        double valorTotal = 0;
        for(Vendas v : lista){
            valorTotal += v.getValor();
        }
        return valorTotal;
    }
    
    

    
}
