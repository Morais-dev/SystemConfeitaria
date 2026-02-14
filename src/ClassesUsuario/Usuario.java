package ClassesUsuario;


import javax.swing.JOptionPane;
import TelasPrincipais.Escolhas;



public class Usuario {
    private String nome;
    private String senha;
    private String cargo;
   

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
    
    
    
    public void autenticar(String acesso, String senhaU){
            if(senhaU.equals(senha) && acesso.equals(nome)){
                JOptionPane.showMessageDialog(null, "Bem vindo " + nome);
                Escolhas tela = new Escolhas();
                tela.setVisible(true);
            }
            else{
                JOptionPane.showMessageDialog(null, "Senha incorreta");                
            }
    }   
}
