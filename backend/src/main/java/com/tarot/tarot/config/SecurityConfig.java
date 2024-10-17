package com.tarot.tarot.config;

import com.tarot.tarot.filter.JWTAuthenticationFilter;
import com.tarot.tarot.filter.JWTAuthorizationFilter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


public class SecurityConfig extends WebSecurityConfigurerAdapter{
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/user/login", "/user/register/**","/user/password/**").permitAll() // Allow login and registration without authentication
                .anyRequest().authenticated() // All other requests need authentication
                .and()
                .addFilter(new JWTAuthenticationFilter(authenticationManager()))
                //.addFilter(new JWTAuthorizationFilter(authenticationManager()));
    }
}
