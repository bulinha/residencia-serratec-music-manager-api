package br.org.serratec.mm.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.org.serratec.mm.dto.UsuarioDTO;
import br.org.serratec.mm.dto.UsuarioInsertDTO;
import br.org.serratec.mm.dto.UsuarioUpdateDTO;
import br.org.serratec.mm.exception.DataNotFoundException;
import br.org.serratec.mm.model.Usuario;
import br.org.serratec.mm.repository.UsuarioRepository;

@Service
public class UsuarioService {
	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	
	
	public UsuarioDTO findById(Long id) throws DataNotFoundException {
		Optional<UsuarioDTO> optionalUsuario = usuarioRepository.findById(id).map(UsuarioDTO::new);
		return optionalUsuario.orElseThrow(() -> new DataNotFoundException(Usuario.class, id));
	}
	
   @Transactional
   public UsuarioDTO insert(@Valid UsuarioInsertDTO usuarioDTO) {
	   Usuario usuario = new Usuario();
	   usuario.setNome(usuarioDTO.getNome());
	   usuario.setEmail(usuarioDTO.getEmail());
	   usuario.setPerfilUsuario(usuarioDTO.getPerfilUsuario());
	   if (!usuarioDTO.getSenha().equals(usuarioDTO.getConfirmaSenha())) {
		   throw new Error("Senhas não são iguais");
	   }
	   usuario.setSenha(encoder.encode(usuarioDTO.getSenha()));
	   
	   usuario.setDataAlteracao(LocalDateTime.now());
	   usuario.setDataCadastro(LocalDateTime.now());
	   usuario = usuarioRepository.save(usuario);
	   return new UsuarioDTO(usuario);
   }
	
	
	@Transactional
	public UsuarioDTO update(Long id, UsuarioUpdateDTO usuarioDTO) throws DataNotFoundException{
		Optional<Usuario> optionalUsuario = usuarioRepository.findById(id);
		if (optionalUsuario.isEmpty()) {
			throw new DataNotFoundException(Usuario.class, id);
		}
		Usuario usuarioDB = optionalUsuario.get();
		usuarioDB.setNome(usuarioDTO.getNome());
		usuarioDB.setEmail(usuarioDTO.getEmail());
		usuarioDB.setPerfilUsuario(usuarioDTO.getPerfilUsuario());
		if (usuarioDTO.getSenha()!=null && usuarioDTO.getConfirmaSenha()!=null && !usuarioDTO.getSenha().isEmpty()) {
			if (!usuarioDTO.getSenha().equals(usuarioDTO.getConfirmaSenha())) {
			   throw new Error("Senhas não são iguais");
			}
			usuarioDB.setSenha(encoder.encode(usuarioDTO.getSenha()));
		}
		   
		usuarioDB.setDataAlteracao(LocalDateTime.now());
		usuarioDB = usuarioRepository.save(usuarioDB);
		return new UsuarioDTO(usuarioDB);

	}
	
	@Transactional
	public void delete(Long id) throws DataNotFoundException {
		Optional<Usuario> optionalUsuario = usuarioRepository.findById(id);
		if (optionalUsuario.isEmpty()) {
			throw new DataNotFoundException(Usuario.class, id);
		}
		usuarioRepository.delete(optionalUsuario.get());
	}


	public List<UsuarioDTO> listAll() {
		return usuarioRepository.findAll().stream().map(UsuarioDTO::new).collect(Collectors.toList());
	}

}
