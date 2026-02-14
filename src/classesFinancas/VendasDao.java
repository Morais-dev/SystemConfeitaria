package classesFinancas;


import conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;

public class VendasDao {
    private Conexao conexao;
    private Connection con;
    private PreparedStatement stmt;
    private ResultSet rs;

    public VendasDao() {
        this.conexao = new Conexao();
        con = this.conexao.conectar();
    }

    public void inserir(Vendas venda) {
        String sql = "INSERT INTO vendas(data, formaPagamento, desconto, valor) VALUES (?, ?, ?, ?)";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        try{
            stmt = con.prepareStatement(sql);
            stmt.setString(1, sdf.format(venda.getData()));
            stmt.setString(2, venda.getTipoPagamento());
            stmt.setDouble(3, venda.getDesconto());
            stmt.setDouble(4, venda.getValor());
            stmt.executeUpdate();
            
        }catch(SQLException sqle){
            JOptionPane.showMessageDialog(null, "Venda não registrada " + sqle.getMessage());
        }
    }
    
    public int maiorId(){
        String sql = "SELECT MAX(id) FROM vendas";
        int id = -1;
        try{
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            if(rs.next()){
                id = rs.getInt(1);
            }
        }catch(SQLException sqle){
           JOptionPane.showMessageDialog(null, "Erro ao pegar maior id " + sqle.getMessage());
        }
        return id;
    }
    
    public void atualizarValor(double valor, int id){
    String sql = "UPDATE vendas SET valor = ? WHERE id = ?";
    try{
        stmt = con.prepareStatement(sql);
        stmt.setDouble(1, valor);
        stmt.setInt(2, id);
        stmt.executeUpdate();
    }catch(SQLException sqle){
            
    }
  }
    public double totalVendas() {
        String sql = "SELECT SUM(valor) FROM vendas WHERE data BETWEEN ? AND ?";

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
