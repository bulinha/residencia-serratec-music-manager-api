package br.org.serratec.mm.dto;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import br.org.serratec.mm.model.PlayList;

public class PlayListDTO {

	private Long id;
	private String nome;
	private List<MusicaDTO> musicas;
	public PlayListDTO() {
		super();
	}
	public PlayListDTO(PlayList playList) {
		this.id=playList.getId();
		this.nome = playList.getNome();
		this.musicas = playList.getMusicas().stream().map(MusicaDTO::new).collect(Collectors.toList());
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
	public List<MusicaDTO> getMusicas() {
		return musicas;
	}
	public void setMusicas(List<MusicaDTO> musicas) {
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
		PlayListDTO other = (PlayListDTO) obj;
		return Objects.equals(id, other.id);
	}
	
	
}
