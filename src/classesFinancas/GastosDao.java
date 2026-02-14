/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classesFinancas;

import conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author kauam
 */
public class GastosDao {
    private Conexao conexao;
    private Connection con;
    private PreparedStatement stmt;
    private ResultSet rs;

    public GastosDao(){
        this.conexao = new Conexao();
        con = this.conexao.conectar();
    }
    
    public void inserir(Gastos gasto) {
    String sql = "INSERT INTO gastos(data, descricao, qtd, valor, formaPagamento) VALUES (?, ?, ?, ?, ?)";
    
    try {

        stmt = con.prepareStatement(sql);
        
        java.sql.Date sqlDate = new java.sql.Date(gasto.getData().getTime()); 
        
        stmt.setDate(1, sqlDate);  
        stmt.setString(2, gasto.getDescricao());  
        stmt.setInt(3, gasto.getQtd());  
        stmt.setDouble(4, gasto.getValor());  
        stmt.setString(5, gasto.getTipoPagamento());  
       
        stmt.executeUpdate();
        
        JOptionPane.showMessageDialog(null, "Gasto adicionado com sucesso!");
    } catch (SQLException sqle) {
        JOptionPane.showMessageDialog(null, "Erro ao adicionar gasto: " + sqle.getMessage());
    }
}
    
       public double totalGastos() {
        String sql = "SELECT SUM(valor) FROM gastos WHERE data BETWEEN ? AND ?";

        LocalDate dataHoje = LocalDate.now();

        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy/MM/dd");

        double total = 0.0;

        try {

            stmt = con.prepareStatement(sql);

            
            stmt.setString(1, dataHoje.format(formato));  
            stmt.setString(2, dataHoje.format(formato));  

            
            rs = stmt.executeQuery();


            if (rs.next()) {
                total = rs.getDouble(1);  
            }

        } catch (SQLException sqle) {
            sqle.printStackTrace();  // Exibe o erro no console para facilitar a depuração
        }

        return total;  
    }
}
