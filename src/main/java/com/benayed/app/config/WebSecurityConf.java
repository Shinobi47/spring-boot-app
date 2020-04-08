package com.benayed.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConf extends WebSecurityConfigurerAdapter{
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.inMemoryAuthentication().withUser("Haytham").password(passwordEncoder().encode("123456")).authorities("ROOT", "USER");
		auth.inMemoryAuthentication().withUser("Haytam").password(passwordEncoder().encode("123456")).roles("ROOT", "USER");
		auth.inMemoryAuthentication().withUser("user1").password(passwordEncoder().encode("123456")).roles("USER");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http         
		//.csrf().disable()
        .authorizeRequests().anyRequest().authenticated();
        //s.and().httpBasic();
	}

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
