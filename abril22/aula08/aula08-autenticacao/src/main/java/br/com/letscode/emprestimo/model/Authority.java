package br.com.letscode.emprestimo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity(name = "authorities")
@AllArgsConstructor
@NoArgsConstructor
public class Authority {

    @EmbeddedId
    private AuthorityKey authorityKey;

    @ManyToOne
    @MapsId("userName")
    @JoinColumn(name = "username")
    private User user;

    public static Authority convert(User user, String role) {
        Authority authority = new Authority();

        AuthorityKey key = new AuthorityKey(user.getUserName(), role);
        authority.setUser(user);
        authority.setAuthorityKey(key);

        return authority;
    }


}
