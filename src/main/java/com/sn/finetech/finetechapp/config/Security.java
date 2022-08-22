package com.sn.finetech.finetechapp.config;

import com.sn.finetech.finetechapp.config.jwt.JwtFilter;
import com.sn.finetech.finetechapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletResponse;

import static java.lang.String.format;

@EnableWebSecurity
@EnableGlobalMethodSecurity(
        securedEnabled = true,
        jsr250Enabled = true,
        prePostEnabled = true
)
public class Security extends WebSecurityConfigurerAdapter {

    private final com.sn.finetech.finetechapp.repositories.UserRepository UserRepository;
    private final JwtFilter jwtFilter;

    @Value("${springdoc.api-docs.path}")
    private String apiDocPath;
    @Value("${springdoc.swagger-ui.path}")
    private String swaggerPath;

    public Security(UserRepository UserRepository, JwtFilter jwtFilter) {
        super();

        this.UserRepository = UserRepository;
        this.jwtFilter = jwtFilter;
        SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_INHERITABLETHREADLOCAL);
    }

    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(username -> UserRepository
                .findByUsername(username)
                .orElseThrow(
                        () -> new UsernameNotFoundException(
                                format("utilisateur: %s,  pas trouvé", username)
                        )
                ));
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // activer les cors et desactiver les CSRF
        http = http.cors().and().csrf().disable();

        // Mettre la getion de la session a un sans etat
        http = http
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and();

        // mettre pas autoriser si on a une exception
        http = http
                .exceptionHandling()
                        .authenticationEntryPoint(
                                ((request, response, authException) -> {
                                    System.out.println("Demande pas autoriser - "+authException.getMessage());
                                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());
                                })
                        )
                                .and();

        // mettre les permissions sur nos resources
        http.authorizeHttpRequests()
                .antMatchers("/").permitAll()
                .antMatchers(apiDocPath+"/**").permitAll()
                .antMatchers(swaggerPath+"/**").permitAll()
                .antMatchers("/h2/**").permitAll()
                .antMatchers("/api/v1/auth/**").permitAll()
                .anyRequest().authenticated();

        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    }

    // mettre le type d'encodage du mot de passe
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    // Exposer le bean du authentication manager
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception{
        return super.authenticationManagerBean();
    }
}
