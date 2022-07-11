package br.org.serratec.mm.dto;

import br.org.serratec.mm.model.Musica;

public class MusicaLetraDTO {
	private Long id;
	private String titulo;
	private Integer minutos;
	private String letra;
	

	public MusicaLetraDTO(Musica musica, String letra) {
		this.id=musica.getId();
		this.titulo=musica.getTitulo();
		this.minutos=musica.getMinutos();
		this.letra=letra;
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
	public String getLetra() {
		return letra;
	}

}
