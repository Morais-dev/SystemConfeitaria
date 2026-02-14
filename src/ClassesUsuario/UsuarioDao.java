/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ClassesUsuario;

import conexao.Conexao;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author kauam
 */
public class UsuarioDao {
    private Conexao conexao;
    private Connection con;
    private PreparedStatement stmt;
    private ResultSet rs;
    
    public UsuarioDao(){
        this.conexao = new Conexao();
        con = this.conexao.conectar();
    }
    
    public Usuario validarUsuario(String nome, String senha){
        String sql = "SELECT * FROM funcionario WHERE nome = ? AND validacao = ?";
        Usuario usuario = null;
        String senhaCriptografada = criptografar(senha);
        try{
            stmt = con.prepareStatement(sql);
            stmt.setString(1, nome);
            stmt.setString(2, senhaCriptografada);
            rs = stmt.executeQuery();
            while(rs.next()){
                usuario = new Usuario();
                usuario.setNome(rs.getString("nome"));
                usuario.setSenha(rs.getString("validacao"));
                usuario.setCargo(rs.getString("cargo"));
            }
        }catch(SQLException sqle){
            
        }
        return usuario;
    }
   
    
    private String criptografar(String senha) {
    try {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] messageDigest = md.digest(senha.getBytes());
       
        BigInteger no = new BigInteger(1, messageDigest);
        String hashtext = no.toString(16);
        while (hashtext.length() < 32) {
            hashtext = "0" + hashtext;
        }
        
        return hashtext;
    } catch (NoSuchAlgorithmException e) {
        throw new RuntimeException("Erro ao criptografar a senha", e);
    }
  }
    
}
