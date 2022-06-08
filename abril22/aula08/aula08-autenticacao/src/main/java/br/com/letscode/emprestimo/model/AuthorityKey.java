package br.com.letscode.emprestimo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthorityKey implements Serializable {

    @Column(name = "username")
    private String userName;

    @Column(name = "authority")
    private String authority;

}
