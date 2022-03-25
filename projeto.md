# Projeto - Banco de Dados

Criar uma API REST que possua as seguintes rotas:

## POST /produto

Cria um produto no sistema, o produto deve conter os seguintes dados: código, preço e quantidade disponível.

## GET /produto

Lista todos os produtos disponíveis. Essa rota deve permitir paginação e conter um filtro por codigo do produto.

## POST /compra

Cria uma compra no sistema, a compra deve ter os seguintes dados: data da compra, cpf do cliente, o valor total da compra e a lista de produtos que fazem parte da compra.

O valor total deve ser calculado a partir da lista de produtos passados.

## GET /compra

Lista todas as compras disponíveis. Essa rota deve permitir paginação e conter um filtro por cpf do cliente.


# Outros Requisitos

- Todas as rotas devem usar autenticação básica.

- A aplicação deve usar um banco de dados relacional (Postgres, MySQL, ...)

