package br.com.letscode.emprestimo.controller;

import br.com.letscode.emprestimo.dto.LoginDTO;
import br.com.letscode.emprestimo.dto.TokenDTO;
import br.com.letscode.emprestimo.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController {

    private final TokenService tokenService;

    private final AuthenticationManager authenticationManager;

    @PostMapping
    public TokenDTO login(@RequestBody LoginDTO loginDTO) {

        UsernamePasswordAuthenticationToken user
                = new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword());

        Authentication authentication = authenticationManager.authenticate(user);
        String token = tokenService.generateToken(authentication);

        TokenDTO tokenDTO = new TokenDTO();
        tokenDTO.setToken(token);
        return tokenDTO;


    }

}
