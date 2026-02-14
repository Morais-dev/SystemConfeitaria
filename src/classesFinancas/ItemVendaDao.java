/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classesFinancas;

import classesEstoque.Estoque;
import conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author kauam
 */
public class ItemVendaDao {
     private Conexao conexao;
    private Connection con;
    private PreparedStatement stmt;
    private ResultSet rs;
    
    public ItemVendaDao() {
        this.conexao = new Conexao();
        con = this.conexao.conectar();
    }
    
    public ArrayList<Estoque> getEstoque(String nome){
        String sql = "SELECT p.id, p.nome, e.sabor, p.valorUnt, e.qtd FROM estoque e JOIN produto p ON e.idProduto = p.id WHERE p.nome LIKE ? ORDER BY id";
        try{
            stmt = con.prepareStatement(sql);
            stmt.setString(1, "%" + nome + "%");
            rs = stmt.executeQuery();
            
            ArrayList<Estoque> lista = new ArrayList<Estoque>();
            
            while(rs.next()){
                Estoque e = new Estoque();
                e.setId(rs.getInt("id"));
                e.setNome(rs.getString("nome"));
                e.setSabor(rs.getString("sabor"));
                e.setValor(rs.getDouble("valorUnt"));
                e.setQtd(rs.getInt("qtd"));
                lista.add(e);
            }
            return lista;
            
        }catch(SQLException sqle){
            JOptionPane.showMessageDialog(null, "Insira a quantidade de produtos");
            return null;
        }
    }
    
    
    public void atualizarEstoque(int novaQtd, int id){
        String sql = "UPDATE estoque SET qtd = ? WHERE id = ?";
        try{
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, novaQtd);
            stmt.setInt(2, id);
            stmt.executeUpdate();
            
        }catch(SQLException sqle){
            JOptionPane.showMessageDialog(null, "Erro ao mudar quantidade");
        }
    }
        public void inserir(int idVenda, int idProduto, int qtd, String sabor){
        String sql = "INSERT INTO produtoVenda(idVenda, idProduto, qtd, sabor) VALUES (?, ?, ?, ?)";
        try{
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, idVenda);
            stmt.setInt(2, idProduto);
            stmt.setInt(3, qtd);
            stmt.setString(4, sabor);
            stmt.executeUpdate();
            
        }catch(SQLException sqle){
            JOptionPane.showMessageDialog(null, "Erro ao mudar inserir vendasProduto " + sqle.getMessage());
        }
    }
        
    public int idEstoque(String sabor, int id){
        String sql = "SELECT id FROM estoque WHERE sabor = ? AND idProduto = ?";
        int estoqueId = -1;
        try{
            stmt = con.prepareStatement(sql);
            stmt.setString(1, sabor);
            stmt.setInt(2, id);
            rs = stmt.executeQuery();
            if(rs.next()){
                estoqueId = rs.getInt(1);
            }
        }catch(SQLException sqle){
            
        }
        return estoqueId;
    }
    
        
}
