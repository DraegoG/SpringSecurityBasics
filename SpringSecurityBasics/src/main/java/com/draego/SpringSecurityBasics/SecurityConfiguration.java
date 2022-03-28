package com.draego.SpringSecurityBasics;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    //Authentication
    @Override
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        //returns an AuthenticationManager object
        authenticationManagerBuilder.inMemoryAuthentication()
                .withUser("user1")
                .password("abcd")
                .roles("USER")
                .and()  //seperator
                .withUser("Admin")
                .password("admin123")
                .roles("ADMIN");
    }

    //Spring invokes this method to encode any password
    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

    //Authorization
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeRequests()
                //its important to note here that the chaining should go from most restrictive to least restrictive
                .antMatchers("/admin").hasRole("ADMIN")
                .antMatchers("/user1").hasAnyRole("USER","ADMIN") //the role name is case sensitive
                .antMatchers("/").permitAll()
                .and() //used here to end the method chaining
                .formLogin();
    }
}
