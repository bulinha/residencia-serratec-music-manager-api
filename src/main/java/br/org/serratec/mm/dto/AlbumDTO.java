package br.org.serratec.mm.dto;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import br.org.serratec.mm.model.Album;

public class AlbumDTO {

	private Long id;
	private String titulo;
	private Long idArtista;
	private String nomeArtista;
	private List<MusicaAlbumDTO> musicas;
	public AlbumDTO() {
		super();
	}
	public AlbumDTO(Album album) {
		this.id=album.getId();
		this.titulo = album.getTitulo();
		this.idArtista = album.getArtista().getId();
		this.nomeArtista = album.getArtista().getNome();
		this.musicas = album.getMusicas().stream().map(m -> new MusicaAlbumDTO(m, this.idArtista, this.nomeArtista)).collect(Collectors.toList());
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public Long getIdArtista() {
		return idArtista;
	}
	public void setIdArtista(Long idArtista) {
		this.idArtista = idArtista;
	}
	public String getNomeArtista() {
		return nomeArtista;
	}
	public void setNomeArtista(String nomeArtista) {
		this.nomeArtista = nomeArtista;
	}
	public List<MusicaAlbumDTO> getMusicas() {
		return musicas;
	}
	public void setMusicas(List<MusicaAlbumDTO> musicas) {
		this.musicas = musicas;
	}
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AlbumDTO other = (AlbumDTO) obj;
		return Objects.equals(id, other.id);
	}
	
	
}
