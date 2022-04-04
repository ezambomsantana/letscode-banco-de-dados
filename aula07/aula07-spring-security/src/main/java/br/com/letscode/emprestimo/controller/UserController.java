package br.com.letscode.emprestimo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.ArrayList;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    @GetMapping
    public Principal retrievePrincipal(Principal principal) {
        return principal;
    }

}
