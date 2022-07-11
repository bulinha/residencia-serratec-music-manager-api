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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.org.serratec.mm.dto.MusicaDTO;

@Entity
@Table(name="musica")
public class Musica {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="musica_sequence")
	@SequenceGenerator(name="musica_sequence", sequenceName="seq_musica", allocationSize = 1)
	@Column(name="mus_cd_id")
	private Long id;
	
	@NotNull
	@Size(min=2,max=200)
	@Column(name="mus_tx_titulo", nullable = false, length=200)
	private String titulo;
	
	@NotNull
	@Column(name="mus_nu_minuto", nullable=false)
	private Integer minutos;
	
	@ManyToOne
	@JoinColumn(name = "usu_cd_id")
	private Usuario usuario;
	
	@Column(name = "mus_dt_cadastro", columnDefinition = "TIMESTAMP")
	private LocalDateTime dataCadastro;
	
	@Column(name = "mus_dt_altercao", columnDefinition = "TIMESTAMP")
	private LocalDateTime dataAlteracao;	

	public Musica() {
		
	}
	public Musica(MusicaDTO musicaDTO) {
		this.id = musicaDTO.getId();
		this.titulo=musicaDTO.getTitulo();
		this.minutos=musicaDTO.getMinutos();
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
		Musica other = (Musica) obj;
		return Objects.equals(id, other.id);
	}
	
	
	

}
