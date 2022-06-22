# MongoDB

https://www.mongodb.com/docs/manual/tutorial/install-mongodb-on-windows/


## Comandos

Definir o banco de dados a ser utilizado:

    use cliente
    
Inserir um documento em uma coleção

    db.cliente.insertOne({
      "_id": 1,
      "nome": "Eduardo",
      "idade": 36,
      "dataNascimento": new Date('1986-01-01'),
      "telefone": {
        "ddd": "16",
        "numero": "12345678"
      }
    })
    
Fazer uma busca
    
    db.cliente.find()
    
Fazer uma busca com filtro    
    
    db.cliente.find({nome: "Eduardo"})
    
Fazer uma busca em subcampo, duas formas de fazer:

    db.cliente.find({ "telefone.ddd": "16"} })

Apenas matches exatos
    
    db.cliente.find({ "telefone": { "ddd": "16", numero:'12345678'} })
    
Funções

    db.cliente.find( { idade: { $gt: 18 } } )

    db.cliente.find(
       { _id: { $in: [ 1,2,3 ] } }
    )
    

Busca de range de data

    db.cliente.find( { dataNascimento: { $gt: new Date('1940-01-01'), $lt: new Date('1990-01-01') } } )
    
Buscar elemento dentro de uma lista

    db.cliente.find({telefone: {$in: [{ddd:'30',numero:'3121312'}]}})
    
Executar uma função

    db.cliente.find().forEach(x => console.log(x))



## Exercícios

Vamos criar um banco de dados com três coleções, clientes, produtos e compras. 

Os clientes devem possuir id, cpf, nome, idade, data de nascimento, telefone e endereço.

Os produtos devem possuir id, nome, codigo, descricao e preço.

As compras devem possuir id, id do cliente que fez a compra, data da compra, lista de produtos com o código e a quantidade do produto comprada e o valor total da compra.

Vamos cadastrar pelo menos 3 objetos em cada coleção.

Compras

    db.compra.insertOne({
      "_id": "dbd287cf-5b1d-4190-9f1b-1a0ede83d1f9",
      "cliente": "13d9bac2-2544-45ea-81ab-da2766502261",
      "dataCompra": new Date('2022-01-01'),
      "valorTotal": 2001.00,
      "produtos": [
        {
            "idProduto": "80f3ac17-cd8c-42da-b867-d395cd271340",
            "quantidade": 2
        }
      
      ]
    })
    
    db.compra.insertOne({
      "_id": "a6069b72-3f1a-4e7c-ba6f-67568b5673af",
      "cliente": "13d9bac2-2544-45ea-81ab-da2766502261",
      "dataCompra": new Date('2022-01-01'),
      "valorTotal": 7001.50,
      "produtos": [
        {
            "idProduto": "80f3ac17-cd8c-42da-b867-d395cd271340",
            "quantidade": 2
        },
        {
            "idProduto": "871ffd07-9f84-47e4-a264-b4dd3c137158",
            "quantidade": 1
        }
      
      ]
    })
    
    
        db.compra.insertOne({
      "_id": "cc5f126d-af7c-46da-9c57-0346cfc661a3",
      "cliente": "13d9bac2-2544-45ea-81ab-da2766502261",
      "dataCompra": new Date('2023-01-01'),
      "valorTotal": 2001.00,
      "produtos": [
        {
            "idProduto": "80f3ac17-cd8c-42da-b867-d395cd271340",
            "quantidade": 2
        }
      
      ]
    })

Produtos

    db.produto.insertOne({
      "_id": "80f3ac17-cd8c-42da-b867-d395cd271340",
      "nome": "TV",
      "descricao": "TV",
      "preco": 1000.50
    })

    db.produto.insertOne({
      "_id": "43b5ca42-4b94-4dd1-9bdf-c0cd3b392179",
      "nome": "PS5",
      "descricao": "PS5",
      "preco": 5000.50
    })
    
    db.produto.insertOne({
      "_id": "871ffd07-9f84-47e4-a264-b4dd3c137158",
      "nome": "Notebook",
      "descricao": "Notebook",
      "preco": 5000.50
    })    

Clientes

    db.cliente.insertOne({
      "_id": "13d9bac2-2544-45ea-81ab-da2766502261",
      "nome": "Eduardo",
      "cpf": "12345",
      "idade": 36,
      "dataNascimento": new Date('1986-01-01'),
      "telefone": [{
        "ddd": "16",
        "numero": "12345678"
      }],
      "endereco": {
        "logradouro": "rua abc",
        "numero": 300,
        "cidade": "SP"
      }
    })
    
    db.cliente.insertOne({
      "_id": "acd1452e-74dc-4182-bb70-ca02e36cdaa3",
      "nome": "Maria",
      "cpf": "54321",
      "idade": 36,
      "dataNascimento": new Date('1986-01-01'),
      "telefone": [{
        "ddd": "16",
        "numero": "12345678"
      }],
      "endereco": {
        "logradouro": "rua abc",
        "numero": 300,
        "cidade": "SP"
      }
    })
    
        db.cliente.insertOne({
      "_id": "6b0f8797-7f4c-42d9-9ea3-d1943daa8afd",
      "nome": "Joao",
      "cpf": "423344",
      "idade": 15,
      "dataNascimento": new Date('1986-01-01'),
      "telefone": [{
        "ddd": "16",
        "numero": "12345678"
      }],
      "endereco": {
        "logradouro": "rua abc",
        "numero": 300,
        "cidade": "RJ"
      }
    })

Vamos implementar as seguintes consultas:

- Todos os clientes que moram em São Paulo.

        db.cliente.find({'endereco.cidade': 'SP'})

        db.cliente.find({endereco: {logradouro: "rua abc", numero: 300, cidade: 'SP'}})

- Todos os clientes que tenham mais de 20 anos.

        db.cliente.find( { idade: { $gt: 20 } } )

- Todos os produtos que custam mais de 1000 reais.

        db.produto.find( { preco: { $gt: 1000 } } )

- Todas as compras feitas no ano de 2022.
    
        db.compra.find( { dataCompra: { $gte: new Date('2022-01-01'), $lt: new Date('2023-01-01') } } )

- Todas as compras que tenham custado mais de 5000 reais.


        db.compra.find( { valorTotal: { $gte: 5000 } } )
