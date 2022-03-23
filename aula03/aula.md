# Spring Data

## Query Methods

### Consultas pelo nome dos campos

Consultas pelo nome dos campos, por exemplo:

  Pessoa pessoa findByName(String name);
  
  List<Emprestimo> findByDataEmprestimoGreaterThan(LocalDateTime data);


### Consultas nativas
  
  
    @Query(value="select * from pessoa p where p.nome = :nome", nativeQuery=true)
    List<Pessoa> getPessoasNome(String nome);
