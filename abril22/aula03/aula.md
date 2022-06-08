# Spring Data

## Query Methods

### Consultas pelo nome dos campos

    Pessoa pessoa findByName(String name);
  
    List<Emprestimo> findByDataEmprestimoGreaterThan(LocalDateTime data);


### Consultas nativas
  
  
    @Query(value="select * from pessoa p where p.nome = :nome", nativeQuery=true)
    List<Pessoa> getPessoasNome(String nome);

### Consultas com agregação


    @Query("SELECT AVG(p.salario), endereco FROM pessoa AS p GROUP BY endereco")
    List<Object[]> getMediaSalario();


    @Query("SELECT new br.com.letscode.emprestimo.model.PessoaSalario(AVG(p.salario), endereco) FROM pessoa AS p GROUP BY endereco")
    List<PessoaSalario> getMediaSalarioObjeto();


https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repository-query-keywords
