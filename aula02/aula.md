# Aula 02

## Exercício


### DDL

        create table pessoa (
          id serial PRIMARY KEY,
          nome varchar(100) NOT NULL,
          cpf varchar(11) UNIQUE,
          salario float CHECK(salario > 0)
        );

         create table emprestimo (
          id serial PRIMARY KEY,
          valor float NOT NULL,
          num_parcelas integer,
          data_emprestimo TIMESTAMP,
          status varchar(100) not null default 'ABERTO'
        );


        CREATE TABLE parcela (
          id serial PRIMARY KEY,
          valor float NOT NULL,
          num varchar(11) UNIQUE,
          status varchar(100) not null default 'ABERTO',
          data_patagamento TIMESTAMP,
          id_emprestimo integer references emprestimo(id)
        );

       create table pessoa_emprestimo(
            id_pessoa integer references pessoa(id),
            id_emprestimo integer references emprestimo(id),
            primary key(id_pessoa, id_emprestimo)
       );
    
      drop table pessoa_emprestimo ;
      drop table parcela;
      drop table pessoa ;
      drop table emprestimo;

### DML

      insert into pessoa(nome, c    pf, salario) values('Eduardo', '123', 5000);
      insert into emprestimo(valor, num_parcelas, data_emprestimo) values(10000, 10, '2022-01-01');

      insert into pessoa_emprestimo values(1, 1);

      insert into parcela(valor, num, id_emprestimo) values(2200,1,1);
      insert into parcela(valor, num, id_emprestimo) values(2200,2,1);
      insert into parcela(valor, num, id_emprestimo) values(2200,3,1);
      insert into parcela(valor, num, id_emprestimo) values(2200,4,1);
      insert into parcela(valor, num, id_emprestimo) values(2200,5,1);

      
