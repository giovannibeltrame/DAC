package br.ufpr.dac.rhindo.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.ufpr.dac.rhindo.entity.Funcionario;
import br.ufpr.dac.rhindo.entity.RoleEnum;
import br.ufpr.dac.rhindo.repository.FuncionarioRepository;

@Service
public class ProfileDetailsService implements UserDetailsService {
	
	@Autowired
	private FuncionarioRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	public UserDetails loadUserByEmailAndPassword(String email, String senha) throws UsernameNotFoundException {
		Funcionario profile = repository.loadProfile(email, senha);

		if (profile == null) {
			throw new UsernameNotFoundException("Usuário e senha inválidos.");
		}
		
		List<GrantedAuthority> grants = new ArrayList<GrantedAuthority>();
		if (profile.getRole() != null) {
			grants.add(new SimpleGrantedAuthority(profile.getRole().getValue()));
		}
		
		return new User(profile.getEmail(), profile.getSenha(), grants);
	}
	

}
