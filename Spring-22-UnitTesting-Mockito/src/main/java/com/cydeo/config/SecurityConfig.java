package com.cydeo.config;

import com.cydeo.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfig {

    private final SecurityService securityService;
    private final AuthSuccessHandler authSuccessHandler;

    @Autowired
    public SecurityConfig(SecurityService securityService, AuthSuccessHandler authSuccessHandler) {
        this.securityService = securityService;
        this.authSuccessHandler = authSuccessHandler;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

        return httpSecurity.authorizeHttpRequests(rmr -> rmr
                .requestMatchers("/user/create/**").hasAuthority("Admin")
                .requestMatchers("/project/create/**").hasAuthority("Admin")
                .requestMatchers("/task/create/**").hasAuthority("Manager")
                .requestMatchers("/project/manager/**").hasAuthority("Manager")
                .requestMatchers("/task/employee/**").hasAuthority("Employee")

                //.requestMatchers("/task/**").hasAnyRole("EMPLOYEE","ADMIN")
                //.requestMatchers("task/**").hasAuthority("ROLE_EMPLOYEE")
                //.requestMatchers("task/**").hasRole("EMPLOYEE")

                .requestMatchers("/",
                "/login",
                "/fragments/**",
                "/assets/**",
                "/images/**",
                "/error/**").permitAll().anyRequest().authenticated()

        )
        //.httpBasic(withDefaults())
        .formLogin(formLogin -> formLogin.loginPage("/login")
                //.defaultSuccessUrl("/welcome")
                .successHandler(authSuccessHandler)
                .failureUrl("/login?error=true")
                .permitAll()
        )
        .logout(logout -> logout
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login")
        )
        .rememberMe(remember -> remember
                .tokenValiditySeconds(120)
                .key("cydeo")
                .userDetailsService(securityService)
                )
        .build();

    }
}
