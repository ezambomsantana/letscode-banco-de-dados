package br.com.letscode.emprestimo.repository.projection;

public interface PessoaEmprestimoProjection {
    String getNomePessoa();
    Double getSalarioPessoa();
    Double getValorEmprestimo();
    Integer getNumParcelas();
    String getStatus();
}
