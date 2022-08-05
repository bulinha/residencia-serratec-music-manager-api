package br.org.serratec.mm.dto;

import br.org.serratec.mm.model.Musica;

public class MusicaDTO {
	private Long id;
	private String titulo;
	private Integer minutos;
	

	public MusicaDTO() {
		super();
	}
	public MusicaDTO(Musica musica) {
		this.id=musica.getId();
		this.titulo=musica.getTitulo();
		this.minutos=musica.getMinutos();
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
	public Integer getMinutos() {
		return minutos;
	}
	public void setMinutos(Integer minutos) {
		this.minutos = minutos;
	}
	
	

}
