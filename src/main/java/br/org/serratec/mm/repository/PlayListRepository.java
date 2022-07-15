package br.org.serratec.mm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.org.serratec.mm.model.Album;
import br.org.serratec.mm.model.PlayList;
import br.org.serratec.mm.model.Usuario;

public interface PlayListRepository extends JpaRepository<PlayList, Long>{
	
	@Query("from PlayList p where p.usuario=:usuario")
	public List<PlayList> findAllList(@Param("usuario") Usuario usuario);
	
	
}
