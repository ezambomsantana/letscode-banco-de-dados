package br.com.letscode.emprestimo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity(name = "authorities")
public class Authority {

    @EmbeddedId
    private AuthorityKey authorityKey;

    @ManyToOne
    @MapsId("userName")
    @JoinColumn(name = "username")
    private User user;

}
