package com.diegoa.inmovinesrest.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.LdapShaPasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    // Authentication : User --> Roles
    @Autowired
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {

        auth.inMemoryAuthentication().passwordEncoder(new LdapShaPasswordEncoder())
                .withUser("user1").password("usersecret").roles("USER")
                .and()
                .withUser("admin1").password("adminsecret")
                .roles("USER", "ADMIN");
    }

    // Authorization : Role -> Access
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().and().authorizeRequests()

                .antMatchers("**/admin/**").hasRole("ADMIN")
                .antMatchers("**/user/**").hasRole("USER")
                .anyRequest().authenticated()
                .and()

                .csrf().disable().headers().frameOptions().disable();
    }

}