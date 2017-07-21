package br.ufpr.dac.rhindo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import br.ufpr.dac.rhindo.entity.RoleEnum;
import br.ufpr.dac.rhindo.security.ProfileAuthenticationProvider;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private ProfileAuthenticationProvider authProvider;
	
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/", "/lib/**", "/cargo-atribuido-funcionario/**", "/cargos/**", "/cidades/**", "/departamento-aloca-funcionario/**", "/departamentos/**", "/funcionarios/**", "/requisitos/**", "/ufs/**").permitAll()
                .antMatchers("/gerente/**").hasRole(RoleEnum.GERENTE.name())
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
            .logout()
                .permitAll();
        http.headers().frameOptions().disable();
        http.csrf().disable();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    	auth.authenticationProvider(authProvider);
    }
}