package br.org.serratec.mm.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import br.org.serratec.mm.model.Usuario;
import br.org.serratec.mm.repository.UsuarioRepository;
import br.org.serratec.mm.security.UsuarioDetalhe;

@Component
public class MMUtil {
	@Autowired
	private UsuarioRepository usuarioRepository; 
	
	public static String encode(String valor) {
		try {
			return URLEncoder.encode(valor, StandardCharsets.UTF_8.toString());
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public Usuario getUsuarioLogado() {
		UsuarioDetalhe uduarioDetalhe = (UsuarioDetalhe) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Optional<Usuario> usuario = usuarioRepository.findByEmail(uduarioDetalhe.getUsername());
		return usuario.orElse(null);
	}

}
