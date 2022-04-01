package com.task.init.configurations;


import com.task.init.filters.CustomAuthenticationFilter;
import com.task.init.filters.CustomAuthorizationFilter;
import com.task.init.services.CustomUserDetailsService;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.http.HttpMethod.*;


@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomUserDetailsService userDetailsService;

    public SpringSecurityConfig(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http.csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeRequests().antMatchers(GET, "/movies").permitAll();
        http.authorizeRequests().antMatchers("/login").permitAll();
        http.authorizeRequests().antMatchers(DELETE, "/movies/**")
                .hasAuthority("ADMIN");
        http.authorizeRequests().antMatchers(POST, "/movies")
                .hasAnyAuthority("ADMIN", "USER");
        http.authorizeRequests().antMatchers(PUT, "/movies/**")
                .hasAnyAuthority("ADMIN", "USER");
        http.authorizeRequests().antMatchers(POST, "/categories")
                .hasAuthority("ADMIN");
        http.authorizeRequests().antMatchers(PUT, "/categories/**")
                .hasAuthority("ADMIN");
        http.authorizeRequests().antMatchers(GET, "/categories")
                .hasAnyAuthority("ADMIN", "USER");
        http.addFilter(new CustomAuthenticationFilter(userDetailsService));
        http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

}
