package br.org.serratec.mm.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.org.serratec.mm.enums.TipoArtista;
import br.org.serratec.mm.model.Artista;

public class ArtistaDTO {
	
	private Long id;
	
	@Size(min = 5, max = 100)
	@NotNull
	private String nome;
	
	@NotNull
	private TipoArtista tipo;

	public ArtistaDTO() {}
	public ArtistaDTO(Artista artista) {
		this.id = artista.getId();
		this.nome=artista.getNome();
		this.tipo=artista.getTipo();
	}
		
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public TipoArtista getTipo() {
		return tipo;
	}

	public void setTipo(TipoArtista tipo) {
		this.tipo = tipo;
	}
	
	
}
