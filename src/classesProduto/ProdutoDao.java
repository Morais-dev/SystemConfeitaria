/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classesProduto;

import conexao.Conexao;
import static java.awt.image.ImageObserver.HEIGHT;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author kauam
 */
public class ProdutoDao {
    private Conexao conexao;
    private Connection con;
    private PreparedStatement stmt;
    private ResultSet rs;

    public ProdutoDao() {
        this.conexao = new Conexao();
        con = this.conexao.conectar();
    }
    
    public ArrayList<Produto> getProdutos(String nome){
        String sql = "SELECT * FROM produto WHERE nome LIKE ?";
        try{
            stmt = con.prepareStatement(sql);
            stmt.setString(1, "%" + nome + "%");
            rs = stmt.executeQuery();
            
            ArrayList<Produto> lista = new ArrayList();
            
            while(rs.next()){
                Produto p = new Produto();
                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                p.setValorUnt(rs.getDouble("valorUnt"));
                lista.add(p);
            }
            return lista;
            
        }catch(SQLException sqle){
            return null;
        }
    }
    
    public void cadastrarProduto(String nome, Double valor){
        String sql = "INSERT INTO produto(nome, valorUnt) VALUES (?, ?)";
        try{
            stmt = con.prepareStatement(sql);
            stmt.setString(1, nome);
            stmt.setDouble(2, valor);
            stmt.execute();
            
        }catch(SQLException sqle){
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar produto", "462", HEIGHT);
        }
    }
    
    public void editarNomeProduto(String nome, int id){
        String sql = "UPDATE produto SET nome = ? WHERE id = ?";
        try{
            stmt = con.prepareStatement(sql);
            stmt.setString(1, nome);
            stmt.setInt(2, id);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Produto editado");
            
        }catch(SQLException sqle){
            JOptionPane.showMessageDialog(null, "Produto não editado" + sqle.getMessage());

        }
    }
    public void editarPrecoProduto(double preco, int id){
        String sql = "UPDATE produto SET valorUnt = ? WHERE id = ?";
        try{
            stmt = con.prepareStatement(sql);
            stmt.setDouble(1, preco);
            stmt.setInt(2, id);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Produto editado");
        }catch(SQLException sqle){
            
        }
    }
    
}
