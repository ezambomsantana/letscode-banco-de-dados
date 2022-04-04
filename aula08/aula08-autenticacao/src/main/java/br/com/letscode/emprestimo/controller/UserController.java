package br.com.letscode.emprestimo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.ArrayList;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user/")
public class UserController {

    @GetMapping
    public Principal getUser(Principal principal) {
        return principal;
    }

}
