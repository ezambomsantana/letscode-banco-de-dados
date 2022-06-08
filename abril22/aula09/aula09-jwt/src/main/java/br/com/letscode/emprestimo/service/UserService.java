package br.com.letscode.emprestimo.service;

import br.com.letscode.emprestimo.dto.UserDTO;
import br.com.letscode.emprestimo.exception.InvalidValueFieldException;
import br.com.letscode.emprestimo.model.Authority;
import br.com.letscode.emprestimo.model.User;
import br.com.letscode.emprestimo.repository.AuthorityRepository;
import br.com.letscode.emprestimo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;
    private final PasswordEncoder passwordEncoder;

    public void createUser(UserDTO userDTO) {

        if (userDTO.getPassword() == null) {
            throw new InvalidValueFieldException();
        }

        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        User user = User.convert(userDTO);

        final User userDB = userRepository.save(user);

        userDTO.getRoles()
                .forEach(a -> saveAuthority(userDB, a));


    }

    public void saveAuthority(User user, String role) {
        Authority authority = Authority.convert(user, "ROLE_" + role);
        authorityRepository.save(authority);
    }

}
