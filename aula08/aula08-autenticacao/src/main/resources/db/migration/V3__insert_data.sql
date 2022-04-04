  insert into pessoa(nome, cpf, endereco, salario) values('Eduardo', '123', 'Rua abc', 5000);
  insert into emprestimo(valor, num_parcelas, data_emprestimo) values(10000, 10, '2022-01-01');

  insert into pessoa_emprestimo values((select id from pessoa where cpf like '123'), (select id from emprestimo where data_emprestimo =  '2022-01-01'));

  insert into parcela(valor, num, id_emprestimo) values(2200,1,(select id from emprestimo where data_emprestimo =  '2022-01-01'));
  insert into parcela(valor, num, id_emprestimo) values(2200,2,(select id from emprestimo where data_emprestimo =  '2022-01-01'));
  insert into parcela(valor, num, id_emprestimo) values(2200,3,(select id from emprestimo where data_emprestimo =  '2022-01-01'));
  insert into parcela(valor, num, id_emprestimo) values(2200,4,(select id from emprestimo where data_emprestimo =  '2022-01-01'));
  insert into parcela(valor, num, id_emprestimo) values(2200,5,(select id from emprestimo where data_emprestimo =  '2022-01-01'));