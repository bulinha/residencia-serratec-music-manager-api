package br.org.serratec.mm.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import br.org.serratec.mm.model.Usuario;
import br.org.serratec.mm.repository.UsuarioRepository;

@Component
public class UsuarioDetalheService implements UserDetailsService{

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Usuario> usuario = usuarioRepository.findByEmail(username);
		if (!usuario.isPresent()) {
			throw new UsernameNotFoundException("Login '" + username + "' não encontrado!");
		}
		return new UsuarioDetalhe(usuario.get());
	}

}
