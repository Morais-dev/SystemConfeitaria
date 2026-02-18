# Sistema de Gestão para Confeitaria

Sistema desenvolvido em Java para controle de produção, estoque, vendas e relatórios de uma confeitaria.

## Funcionalidades
- Cadastro de produtos  
- Controle de estoque 
- Registro de vendas
- Controle de gastos  
- Relatórios de vendas por período  
- Relatórios de gastos por período  
- Orçamento
- Autenticação de funcionários  

## Tecnologias
- Java (Swing)
- MySQL
- JDBC
- Apache POI (geração de relatórios Excel)
- Programação orientada a objetos

## Banco de dados
O script de criação do banco está em: database/schema.sql

As credenciais do banco estão em: src/config/db.properties

Edite esse arquivo com suas credenciais locais antes de rodar o sistema.

Exemplo:

db.url=jdbc:mysql://localhost:3306/brigadeiros_tk

db.user=root

db.password=123456

## Como executar

1. Clone o repositório
2. Execute o script database/schema.sql no MySQL
3. Configure o arquivo db.properties
4. Abra o projeto na IDE e execute

## Status
Projeto em desenvolvimento para fins de portfólio e estudo.

## Autor
Kauã Morais

Técnico em Desenvolvimento de Sistemas — Senac

Cursando Engenharia de Software
