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

Definir a paginação em um método de busca:

    List<Pessoa> findByNome(String nome, Pageable pageable);
    
    Page<Pessoa> pessoas2 = pessoaRepository.findAll(PageRequest.of(2, 4));
    pessoas2.forEach(x -> System.out.println(x.getNome() + x.getSalario()));
    System.out.println(pessoas2.getTotalElements() + " " + pessoas2.getTotalPages());
    
Paginação com ordernação:
    
    pessoaRepository.findAll(PageRequest.of(2, 4, Sort.by(Sort.Direction.ASC, "nome")));


## Projection

Projeções são usadas quando queremos fazer queries específicas, que não retornem um objeto inteira. Podemos definir as consultas assim:

    @Query(value = "select p.id as idPessoa, e.id as idEmprestimo, e.valor as valorEmprestimo " +
            "from pessoa p " +
            "join pessoa_emprestimo pe on p.id = pe.id_pessoa " +
            "join emprestimo e on pe.id_emprestimo = e.id", nativeQuery = true)
    List<PessoaEmprestimoProjection> listPessoaEmprestimo();

    @Query(value = "select p.nome as nomePessoa, pa.valor as valorParcela " +
            "from pessoa p " +
            "join pessoa_emprestimo pe on p.id = pe.id_pessoa " +
            "join emprestimo e on pe.id_emprestimo = e.id " +
            "join parcela pa on e.id = pa.id_emprestimo", nativeQuery = true)
    List<PessoaParcelaProjection> listPessoaParcela();
    
E elas retornam os valores usando uma interface definida com os campos:    

    public interface PessoaEmprestimoProjection {
        Integer getIdPessoa();
        Integer getIdEmprestimo();
        Double getValorEmprestimo();
    }


## Speficication

Specifications são usadas quando precisamos construir as condições (where) de forma dinâmica, por exemplo, se quisermos fazer uma tela de busca que conterá diversos filtros opcionais.

Adicionamos a interface JpaSpecificationExecutor na nossa interface PessoaRepository, e criamos uma classe com as possíveis condições:

    public class PessoaSpecification {

        public static Specification<Pessoa> filterByName(String nome) {
            return (root, query, builder) -> builder.equal(root.get("nome"), nome);
        }

        public static Specification<Pessoa> filterByEnredereco(String endereco) {
            return (root, query, builder) -> builder.equal(root.get("endereco"), endereco);
        }

    }
    
Depois podemos usar essas especificações:

        List<Pessoa> p = pessoaRepository.findAll(PessoaSpecification.filterByName("Eduardo"));
        p.forEach(x -> System.out.println(x.getNome() + "  " + x.getEndereco()));


        System.out.println("----");
        p = pessoaRepository.findAll(PessoaSpecification.filterByName("Eduardo").and(PessoaSpecification.filterByEnredereco("sp")));
        p.forEach(x -> System.out.println(x.getNome() + "  " + x.getEndereco()));
