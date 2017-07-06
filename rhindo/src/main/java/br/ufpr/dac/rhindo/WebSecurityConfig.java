package br.ufpr.dac.rhindo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;

import br.ufpr.dac.rhindo.entity.RoleEnum;
import br.ufpr.dac.rhindo.security.ProfileAuthenticationProvider;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private ProfileAuthenticationProvider authProvider;
	
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/", "/lib/**").permitAll()
                .antMatchers("/gerente/**").hasRole(RoleEnum.GERENTE.name())
                .antMatchers("/home").hasRole(RoleEnum.FUNCIONARIO.name())
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
            .logout()
                .permitAll();
        http.headers().frameOptions().disable();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//            .inMemoryAuthentication()
//                .withUser("user").password("1").roles(RoleEnum.GERENTE.name());
    	auth.authenticationProvider(authProvider);
    }
}