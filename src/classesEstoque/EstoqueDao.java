/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classesEstoque;

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
public class EstoqueDao {
    private Conexao conexao;
    private Connection con;
    private PreparedStatement stmt;
    private ResultSet rs;

    public EstoqueDao() {
        this.conexao = new Conexao();
        con = this.conexao.conectar();
    }
    
    public void inserir(Estoque estoque){
        String sql = "INSERT INTO estoque(idProduto, qtd, sabor) Values (?, ?, ?)";
        try{
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, estoque.getIdProduto());
            stmt.setInt(2, estoque.getQtd());
            stmt.setString(3, estoque.getSabor());
            stmt.execute();
            JOptionPane.showMessageDialog(null, "Novo estoque adicionado com sucesso");
        }catch(SQLException sqle){
            JOptionPane.showMessageDialog(null, "Erro ao adicionar estoque");
        }
    }
    
    public ArrayList<Estoque> getEstoque(String nome){
        String sql = "SELECT e.id, p.nome, e.sabor, e.qtd FROM estoque e JOIN produto p ON e.idProduto = p.id WHERE p.nome LIKE ? ORDER BY id";
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
                e.setQtd(rs.getInt("qtd"));
                lista.add(e);
            }
            return lista;
            
        }catch(SQLException sqle){
            return null;
        }
    }
    
    public void editar(int novoValor, int id){
        String sql = "UPDATE estoque SET qtd = ? WHERE id = ?";
        try{
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, novoValor);
            stmt.setInt(2, id);
            stmt.execute();
            JOptionPane.showMessageDialog(null, "Estoque editado com sucesso");
        }catch(SQLException sqle){
            System.out.println("errpp");
        }
    }
    
    public void iniciarTransacao(){
        String sql = "";
    }
}
