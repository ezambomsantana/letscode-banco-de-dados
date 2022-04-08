package br.com.letscode.emprestimo.model;


import br.com.letscode.emprestimo.dto.UserDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Entity(name = "users")
public class User {

    @Id
    @Column(name = "username")
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "enabled")
    private Boolean enabled;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<Authority> authorities = new ArrayList<>();

    public static User convert(UserDTO dto) {
        User user = new User();
        user.setEnabled(dto.getEnabled());
        user.setPassword(dto.getPassword());
        user.setUserName(dto.getUserName());
        return user;
    }

}
