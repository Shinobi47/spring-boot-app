package com.benayed.app.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true) => put in CustomMethodSecurityExpressionConfig otherwise the bean methodSecurityInterceptor could not be registred
public class BasicAuthWebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	private final String GET_USER_PROFILE_QUERY  = "select\r\n" + 
			"UPR_USER_NAME as principal,\r\n" + 
			"UPR_USER_PASSWD as credendials,\r\n" + 
			"UPR_IS_USER_ACTIVE as enabled\r\n" + 
			"from USER_PROFILE\r\n" + 
			"where UPR_USER_NAME = ?";
	
	
	private final String GET_USER_ROLES_QUERY  = "select\r\n" + 
			"UPR_USER_NAME as principal,\r\n" + 
			"ROLE_NAME as role\r\n" + 
			"from USER_PROFILE\r\n" + 
			"inner join USER_ROLE on USRL_USER_ID = UPR_USER_ID\r\n" + 
			"inner join ROLE on USRL_ROLE_ID = ROLE_ID\r\n" + 
			"where  UPR_USER_NAME = ?";
	
	//setting up the AuthenticationManager
	@Autowired
	protected void configureGlobal(AuthenticationManagerBuilder auth, DataSource dataSource) throws Exception {
		auth.inMemoryAuthentication().withUser("Sbatalouj").password(passwordEncoder().encode("123456")).roles("ROOT", "USER");
		auth.jdbcAuthentication().dataSource(dataSource)
				.usersByUsernameQuery(GET_USER_PROFILE_QUERY)
				.authoritiesByUsernameQuery(GET_USER_ROLES_QUERY)
				.rolePrefix("ROLE_");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
        .httpBasic(); // Important to activate basic auth otherwise you will get 403 in your face ("Authorization: Basic base64(user:password)")
//		.and()
//		.csrf().ignoringAntMatchers("/h2/**") // for h2 console
//		.csrf().disable()
//      .authorizeRequests().anyRequest().authenticated()

	}

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

//authoritiesByUsernameQuery : The query to use for selecting the username, authority by username. Must contain a single parameter for the username.
//usersByUsernameQuery : query - The query to use for selecting the username, password, and if the user is enabled by username. Must contain a single parameter for the username.