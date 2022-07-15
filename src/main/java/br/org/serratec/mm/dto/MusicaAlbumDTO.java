package br.org.serratec.mm.dto;

import br.org.serratec.mm.model.Musica;

public class MusicaAlbumDTO {
	private Long id;
	private String titulo;
	private Integer minutos;
	private Long idArtista;
	private String nomeArtista;
	

	public MusicaAlbumDTO(Musica musica, Long idArtista, String nomeArtista) {
		this.id=musica.getId();
		this.titulo=musica.getTitulo();
		this.minutos=musica.getMinutos();
		this.idArtista = idArtista;
		this.nomeArtista = nomeArtista;
	}
	
	
	public Long getId() {
		return id;
	}
	public String getTitulo() {
		return titulo;
	}
	public Integer getMinutos() {
		return minutos;
	}


	public Long getIdArtista() {
		return idArtista;
	}


	public String getNomeArtista() {
		return nomeArtista;
	}
	
	
	
	

}
