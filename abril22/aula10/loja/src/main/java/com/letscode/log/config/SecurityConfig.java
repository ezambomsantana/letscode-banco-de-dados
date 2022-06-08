package com.letscode.log.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers("/compra").authenticated()
                .antMatchers("/produto").permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("user01")
                .password("{noop}user01")
                .roles("ADMIN")
                .and()
                .withUser("user02")
                .password("{noop}user02")
                .roles("DEV")
                .and()
                .withUser("user03")
                .password("{noop}user03")
                .roles("DEV");
    }
}
