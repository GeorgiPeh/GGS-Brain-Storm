package com.example.digitallibrary.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter
{
  @Override
  protected void configure(HttpSecurity http) throws Exception
  {
    http
        .csrf().disable()
        .authorizeRequests()
        .antMatchers("/login", "/registerAuth", "/registerReader", "/error/*", "/verifyRegistration/*").permitAll()
        .antMatchers().permitAll()
        .anyRequest().authenticated()
        .and()
        .formLogin().loginPage("/login").permitAll()
        .usernameParameter("username")
        .passwordParameter("password")
        .defaultSuccessUrl("/index.html", true)
        .and()
        .httpBasic()
        .and()
        .logout().logoutSuccessUrl("/login?logout").permitAll();
  }

}
