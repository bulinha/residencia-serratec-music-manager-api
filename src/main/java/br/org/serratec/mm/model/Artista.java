package br.org.serratec.mm.model;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import br.org.serratec.mm.enums.TipoArtista;

@Entity
@Table(name = "artista")
public class Artista {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="artista_sequence")
	@SequenceGenerator(name="artista_sequence", sequenceName="seq_artista", allocationSize = 1)
	@Column(name="art_cd_id")
	private Long id;
	
	@Size(min = 5, max = 100)
	@Column(name = "art_tx_nome", nullable = false, length = 100)
	private String nome;
	
	//@Enumerated(EnumType.STRING)
	@Column(name = "art_tx_tipo", nullable = false, length = 1)
	private TipoArtista tipo;
	
	@ManyToOne
	@JoinColumn(name = "usu_cd_id")
	private Usuario usuario;
	
	@Column(name = "art_dt_cadastro", columnDefinition = "TIMESTAMP")
	private LocalDateTime dataCadastro;
	
	@Column(name = "art_dt_altercao", columnDefinition = "TIMESTAMP")
	private LocalDateTime dataAlteracao;
	

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public LocalDateTime getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDateTime dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public LocalDateTime getDataAlteracao() {
		return dataAlteracao;
	}

	public void setDataAlteracao(LocalDateTime dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
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
		Artista other = (Artista) obj;
		return Objects.equals(id, other.id);
	}

}
