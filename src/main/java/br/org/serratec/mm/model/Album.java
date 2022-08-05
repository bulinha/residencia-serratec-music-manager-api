package br.org.serratec.mm.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "album")
public class Album {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "album_sequence")
	@SequenceGenerator(name="album_sequence", sequenceName="seq_album", allocationSize = 1)
	@Column(name="alb_cd_id")
	private Long id;
	
	@NotNull
	@Column(name="alb_tx_titulo", nullable = false, length=200)
	private String titulo;
	
	@ManyToOne
	@JoinColumn(name = "art_cd_id")
	private Artista artista;

	@OneToOne(cascade=CascadeType.ALL,orphanRemoval = true, fetch = FetchType.LAZY )
	@PrimaryKeyJoinColumn
	private Capa capa;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(
	  name = "album_musica", 
	  joinColumns = @JoinColumn(name = "alb_cd_id"), 
	  inverseJoinColumns = @JoinColumn(name = "mus_cd_id"))
	private List<Musica> musicas;
	
	@ManyToOne
	@JoinColumn(name = "usu_cd_id")
	private Usuario usuario;
	
	@Column(name = "alb_dt_cadastro", columnDefinition = "TIMESTAMP")
	private LocalDateTime dataCadastro;
	
	@Column(name = "alb_dt_altercao", columnDefinition = "TIMESTAMP")
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

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Artista getArtista() {
		return artista;
	}

	public void setArtista(Artista artista) {
		this.artista = artista;
	}

	public List<Musica> getMusicas() {
		return musicas;
	}

	public void setMusicas(List<Musica> musicas) {
		this.musicas = musicas;
	}

	
	public Capa getCapa() {
		return capa;
	}

	public void setCapa(Capa capa) {
		this.capa = capa;
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
		Album other = (Album) obj;
		return Objects.equals(id, other.id);
	}

	
	
}
