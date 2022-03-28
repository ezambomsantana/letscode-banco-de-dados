# Testes Spring Data

## Testando chamadas ao repository

  
    @Test
    public void testGetList() {

        List<Pessoa> pessoas = new ArrayList<>();
        pessoas.add(Pessoa.builder().nome("teste").build());

        final Page<Pessoa> pageList = new PageImpl<>(pessoas);


        Pageable page = PageRequest.of(0,5);
        Mockito.when(pessoaRepository.findAll(page)).thenReturn(pageList);

        Page<PessoaDTO> ret = pessoaService.listAll(page);

        Assertions.assertEquals(ret.getSize(), 1);
        Mockito.verify(pessoaRepository, Mockito.times(1)).findAll(page);

    }
    
## Testando os métodos do Repository


### Criando os dados diretamente no teste


    @Test
    public void whenFindByName_thenReturnEmployee0  () {
        // given
        Pessoa pessoa = new Pessoa();
        pessoa.setNome("Eduardo");
        pessoa.setCpf("1234");
        pessoa.setSalario(1000F);
        entityManager.persist(pessoa);
        entityManager.flush();

        // when
        Double found = pessoaRepository.sumSalarios();

        // then
        Assertions.assertEquals(2000, found);
    }

### Criando dados de teste no arquivo data.sql

    @Test
    public void testGetAll  () {

        // when
        List<Pessoa> found = pessoaRepository.findAll();

        // then
        Assertions.assertEquals(1, found.size());
    }
