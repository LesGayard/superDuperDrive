package com.udacity.jwdnd.course1.cloudstorage.services;


import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig  extends WebSecurityConfigurerAdapter {

    private AuthenticationService authenticationService;

    public SecurityConfig(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @Override
    protected void configure (AuthenticationManagerBuilder authenticationManagerBuilder){
        authenticationManagerBuilder.authenticationProvider(this.authenticationService);
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {

        /* DATABASE ACCESS */
        httpSecurity.authorizeRequests()
                .antMatchers("/signup", "/css/**", "/js/**","/h2console/**").permitAll().and()
                .authorizeRequests().anyRequest().authenticated();

       httpSecurity.csrf().disable();
        httpSecurity.headers().frameOptions().disable();

        httpSecurity.formLogin()
                .loginPage("/")
                .permitAll();

        httpSecurity.formLogin()
                .defaultSuccessUrl("/home", true);
    }
}
