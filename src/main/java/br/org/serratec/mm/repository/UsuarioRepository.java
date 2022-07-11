package br.org.serratec.mm.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.org.serratec.mm.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

	Optional<Usuario> findByEmail(String email);

}
