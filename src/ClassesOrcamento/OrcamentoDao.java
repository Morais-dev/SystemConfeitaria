package ClassesOrcamento;

import conexao.Conexao;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.JOptionPane;
import java.awt.Desktop;

public class OrcamentoDao {
    private Conexao conexao;
    private Connection con;
    private PreparedStatement stmt;
    private ResultSet rs;

    public OrcamentoDao() {
        this.conexao = new Conexao();
        con = this.conexao.conectar();
    }

    public void gerarOrcamentoExcel(javax.swing.JTable tblProdutos) {

        try {

            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Orçamento");

            // Criar um formato de célula para valores monetários (R$)
            CellStyle moneyCellStyle = workbook.createCellStyle();
            DataFormat format = workbook.createDataFormat();
            moneyCellStyle.setDataFormat(format.getFormat("R$ #,##0.00")); // Formato monetário

            // Cabeçalho
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("Quantidade");
            headerRow.createCell(1).setCellValue("ID Produto");
            headerRow.createCell(2).setCellValue("Nome Produto");
            headerRow.createCell(3).setCellValue("Preço Unitário");
            headerRow.createCell(4).setCellValue("Valor Total");

            // Preencher os dados da tabela
            double valorTotal = 0;
            int rowCount = 1;  // Linha onde os dados começam

            // Consultando o banco de dados
            String sql = "SELECT p.id, p.nome, p.valorUnt FROM produto p";
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();

            // Percorrer os produtos retornados da consulta ao banco
            while (rs.next()) {
                int idProduto = rs.getInt("id");
                String nomeProduto = rs.getString("nome");
                double precoUnitario = rs.getDouble("valorUnt");

                // Verifica a quantidade do produto na tabela (tabela de orçamentos)
                int quantidade = 0;
                for (int i = 0; i < tblProdutos.getRowCount(); i++) {
                    if (idProduto == Integer.parseInt(String.valueOf(tblProdutos.getValueAt(i, 1)))) {
                        quantidade = Integer.parseInt(String.valueOf(tblProdutos.getValueAt(i, 0)));
                        break;
                    }
                }

                if (quantidade > 0) {
                    // Preencher a linha com os dados do produto
                    Row row = sheet.createRow(rowCount++);
                    row.createCell(0).setCellValue(quantidade);
                    row.createCell(1).setCellValue(idProduto);
                    row.createCell(2).setCellValue(nomeProduto);

                    // Preço Unitário com formato monetário
                    Cell precoUnitarioCell = row.createCell(3);
                    precoUnitarioCell.setCellValue(precoUnitario);
                    precoUnitarioCell.setCellStyle(moneyCellStyle);  // Aplica o estilo monetário

                    double valorItem = quantidade * precoUnitario;

                    // Valor Total com formato monetário
                    Cell valorTotalCell = row.createCell(4);
                    valorTotalCell.setCellValue(valorItem);
                    valorTotalCell.setCellStyle(moneyCellStyle);  // Aplica o estilo monetário

                    valorTotal += valorItem;  // Atualiza o valor total
                }
            }

            // Linha do total
            Row totalRow = sheet.createRow(rowCount);
            totalRow.createCell(3).setCellValue("Total:");
            
            // Valor Total na última célula
            Cell totalValueCell = totalRow.createCell(4);
            totalValueCell.setCellValue(valorTotal);
            totalValueCell.setCellStyle(moneyCellStyle);  // Aplica o estilo monetário

            // Salvar o arquivo Excel
            String caminhoArquivo = "C:\\Users\\kauam\\Documents\\Orcamento.xlsx";  // Caminho para salvar o arquivo
            try (FileOutputStream fileOut = new FileOutputStream(new File(caminhoArquivo))) {
                workbook.write(fileOut);
            }

            workbook.close();

            // Abrir o arquivo gerado
            abrirArquivoExcel(caminhoArquivo);

        } catch (SQLException | IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao gerar o orçamento.");
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void abrirArquivoExcel(String caminhoArquivo) {
        try {
            File file = new File(caminhoArquivo);
            if (file.exists()) {
                if (Desktop.isDesktopSupported()) {
                    Desktop.getDesktop().open(file);
                } else {
                    JOptionPane.showMessageDialog(null, "Não é possível abrir o arquivo.");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
