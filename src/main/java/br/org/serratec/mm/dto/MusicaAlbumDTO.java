package br.org.serratec.mm.dto;

import br.org.serratec.mm.model.Musica;

public class MusicaAlbumDTO extends MusicaDTO{
	private Long idArtista;
	private String nomeArtista;
	
	public MusicaAlbumDTO(Musica musica, Long idArtista, String nomeArtista) {
		super(musica);
		this.idArtista = idArtista;
		this.nomeArtista = nomeArtista;
	}
	
	public Long getIdArtista() {
		return idArtista;
	}

	public String getNomeArtista() {
		return nomeArtista;
	}

}
