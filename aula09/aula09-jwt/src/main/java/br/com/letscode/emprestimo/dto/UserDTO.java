package br.com.letscode.emprestimo.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
public class UserDTO {

    @NotNull
    private String userName;
    private String password;
    private Boolean enabled;
    private List<String> roles;

}
