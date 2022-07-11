package br.org.serratec.mm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.org.serratec.mm.dto.AlbumListDTO;
import br.org.serratec.mm.model.Album;

public interface AlbumRepository extends JpaRepository<Album, Long>{
	
	@Query("select a.id as id, a.titulo as titulo, ar.id as idArtista, "
			+ " ar.nome as nomeArtista, sum(m.minutos) as minutos "
			+ " from Album a join a.musicas m join a.artista ar"
			+ " group by a.id, a.titulo, ar.id, ar.nome ")
	public List<AlbumListDTO> listaAlbunsComMinutos();

}
