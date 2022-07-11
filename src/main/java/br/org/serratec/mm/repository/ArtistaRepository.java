package br.org.serratec.mm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.org.serratec.mm.model.Artista;

public interface ArtistaRepository extends JpaRepository<Artista, Long> {
	

}
