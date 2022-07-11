package br.org.serratec.mm.dto;

import br.org.serratec.mm.model.Musica;

public class MusicaDTO {
	private Long id;
	private String titulo;
	private Integer minutos;
	

	public MusicaDTO(Musica musica) {
		this.id=musica.getId();
		this.titulo=musica.getTitulo();
		this.minutos=musica.getMinutos();
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

}
