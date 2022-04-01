INSERT INTO pessoa(nome, endereco, salario, cpf)
    values('Eduardo', 'Rua abc', 10000, '321');

INSERT INTO pessoa(nome, endereco, salario, cpf)
    values('Joao', 'Rua abc', 15000, '4321');

INSERT INTO emprestimo
(valor, num_parcelas, data_emprestimo, status)
VALUES(10000, 5, '2020-01-01', 'ABERTO');

INSERT INTO pessoa_emprestimo
(id_pessoa, id_emprestimo)
VALUES(1, 1);

INSERT INTO parcela
(valor, num, status, data_pagamento, id_emprestimo)
VALUES(2200, 1, 'ABERTO', null, 1);

INSERT INTO parcela
(valor, num, status, data_pagamento, id_emprestimo)
VALUES(2200, 2, 'ABERTO', null, 1);

INSERT INTO parcela
(valor, num, status, data_pagamento, id_emprestimo)
VALUES(2200, 3, 'ABERTO', null, 1);

INSERT INTO parcela
(valor, num, status, data_pagamento, id_emprestimo)
VALUES(2200, 4, 'ABERTO', null, 1);

INSERT INTO parcela
(valor, num, status, data_pagamento, id_emprestimo)
VALUES(2200, 5, 'ABERTO', null, 1);