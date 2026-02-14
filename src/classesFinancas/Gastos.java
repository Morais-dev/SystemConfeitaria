/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classesFinancas;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author kauam
 */
public class Gastos {
    private String descricao;
    private Date data;
    private int qtd;
    private String tipoPagamento;
    private double valor;
    
    private static ArrayList<Gastos> despesa = new ArrayList<Gastos>();


    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public ArrayList<Gastos> getDespesa() {
        return despesa;
    }

    public void setDespesa(ArrayList<Gastos> despesa) {
        this.despesa = despesa;
    }
    
     public void adicionar(Gastos gasto){
        despesa.add(gasto);
    }
    
    public void gerarRelatorio(){
        for(Gastos gasto : despesa){
            System.out.println(gasto.getDescricao()+ " " + gasto.getData()+ " " + gasto.getValor());
        }
     }

    public int getQtd() {
        return qtd;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }

    public String getTipoPagamento() {
        return tipoPagamento;
    }

    public void setTipoPagamento(String tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }
    
    public double gastoToltal(ArrayList<Gastos> lista){
        double valor = 0;
        for(Gastos g : lista){
            valor += g.getValor();
        }
        return valor;
    }
    
    
    
    
}
    
