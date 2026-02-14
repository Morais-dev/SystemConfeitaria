package TelasFinancas;

import classesFinancas.DatasRelatorio;
import conexao.Conexao;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.*;
import java.text.SimpleDateFormat;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class RelatoriosDao {

    private Conexao conexao;
    private Connection con;
    private PreparedStatement stmt;
    private ResultSet rs;

    public RelatoriosDao() {
        this.conexao = new Conexao();
        this.con = conexao.conectar();
    }

    // =====================================================
    // RELATÓRIO DE VENDAS
    // =====================================================
    public void gerarRelatorioVendas(DatasRelatorio datas) {

        String sql = "SELECT "
                + "v.id AS venda_id, "
                + "v.data AS data_venda, "
                + "GROUP_CONCAT(CONCAT(p.nome, ' - ', pv.sabor, ' (', pv.qtd, ')') SEPARATOR ', ') AS produtos, "
                + "v.formaPagamento AS forma_pagamento, "
                + "v.desconto AS desconto, "
                + "SUM(pv.qtd * p.valorUnt) - v.desconto AS valor_total "
                + "FROM vendas v "
                + "JOIN produtoVenda pv ON v.id = pv.idVenda "
                + "JOIN produto p ON pv.idProduto = p.id "
                + "WHERE v.data BETWEEN ? AND ? "
                + "GROUP BY v.id;";

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

        try {

            stmt = con.prepareStatement(sql);
            stmt.setString(1, sdf.format(datas.getData1()));
            stmt.setString(2, sdf.format(datas.getData2()));
            rs = stmt.executeQuery();

            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Relatorio Vendas");

            // Cabeçalho
            Row header = sheet.createRow(0);
            header.createCell(0).setCellValue("ID Venda");
            header.createCell(1).setCellValue("Data");
            header.createCell(2).setCellValue("Produtos");
            header.createCell(3).setCellValue("Forma Pagamento");
            header.createCell(4).setCellValue("Desconto");
            header.createCell(5).setCellValue("Valor Total");

            int rowNum = 1;
            double totalVendas = 0;
            int qtdVendas = 0;

            while (rs.next()) {
                Row row = sheet.createRow(rowNum++);

                row.createCell(0).setCellValue(rs.getInt("venda_id"));
                row.createCell(1).setCellValue(rs.getString("data_venda"));
                row.createCell(2).setCellValue(rs.getString("produtos"));
                row.createCell(3).setCellValue(rs.getString("forma_pagamento"));
                row.createCell(4).setCellValue(rs.getDouble("desconto"));
                row.createCell(5).setCellValue(rs.getDouble("valor_total"));

                totalVendas += rs.getDouble("valor_total");
                qtdVendas++;
            }

            // Linha total
            Row totalRow = sheet.createRow(rowNum + 1);
            totalRow.createCell(4).setCellValue("TOTAL:");
            totalRow.createCell(5).setCellValue(totalVendas);

            Row qtdRow = sheet.createRow(rowNum + 2);
            qtdRow.createCell(4).setCellValue("QTD VENDAS:");
            qtdRow.createCell(5).setCellValue(qtdVendas);

            String pasta = System.getProperty("user.home") + File.separator + "Documents" + File.separator + "relatorios";
            new File(pasta).mkdirs();

            String caminho = pasta + File.separator + "relatorio_vendas.xlsx";

            FileOutputStream out = new FileOutputStream(caminho);
            workbook.write(out);
            out.close();
            workbook.close();

            abrirArquivoExcel(caminho);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // =====================================================
    // RELATÓRIO DE GASTOS
    // =====================================================
    public void gerarRelatorioGastos(DatasRelatorio datas) {

        String sql = "SELECT data, descricao, qtd, formaPagamento, valor FROM gastos WHERE data BETWEEN ? AND ?;";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

        try {

            stmt = con.prepareStatement(sql);
            stmt.setString(1, sdf.format(datas.getData1()));
            stmt.setString(2, sdf.format(datas.getData2()));
            rs = stmt.executeQuery();

            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Relatorio Gastos");

            Row header = sheet.createRow(0);
            header.createCell(0).setCellValue("Data");
            header.createCell(1).setCellValue("Descricao");
            header.createCell(2).setCellValue("Quantidade");
            header.createCell(3).setCellValue("Forma Pagamento");
            header.createCell(4).setCellValue("Valor");

            int rowNum = 1;
            double totalGastos = 0;

            while (rs.next()) {
                Row row = sheet.createRow(rowNum++);

                row.createCell(0).setCellValue(rs.getString("data"));
                row.createCell(1).setCellValue(rs.getString("descricao"));
                row.createCell(2).setCellValue(rs.getInt("qtd"));
                row.createCell(3).setCellValue(rs.getString("formaPagamento"));
                row.createCell(4).setCellValue(rs.getDouble("valor"));

                totalGastos += rs.getDouble("valor");
            }

            Row totalRow = sheet.createRow(rowNum + 1);
            totalRow.createCell(3).setCellValue("TOTAL GASTO:");
            totalRow.createCell(4).setCellValue(totalGastos);

            String pasta = System.getProperty("user.home") + File.separator + "Documents" + File.separator + "relatorios";
            new File(pasta).mkdirs();

            String caminho = pasta + File.separator + "relatorio_gastos.xlsx";

            FileOutputStream out = new FileOutputStream(caminho);
            workbook.write(out);
            out.close();
            workbook.close();

            abrirArquivoExcel(caminho);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void abrirArquivoExcel(String caminho) {
        try {
            Desktop.getDesktop().open(new File(caminho));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
