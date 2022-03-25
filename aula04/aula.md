# spring data

## Sort

### OrderBy na query

Jeito mais simples, colocar o OrderBy no nome da query.

    List<Pessoa> findByEnderecoOrderByNomeAsc(String nome);
    
### Objeto Sort
    
Outra forma é passar o objeto Sort.

    List<Pessoa> findByNome(String nome, Sort sort);
    
Para usar depois:

    pessoaRepository.findByNome("Eduardo", Sort.by(Sort.Direction.ASC, "salario"));

## Pagination


    List<Pessoa> findByNome(String nome, Pageable pageable);


## Projection

## Speficication
