package br.org.serratec.mm.model;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="album_capa")
public class Capa {
	
	@Id
	@Column(name="alb_cd_id")
	private Long id;
	
	@OneToOne
	@MapsId
	@JoinColumn(name="alb_cd_id")
	private Album album;
	
	@Column(name="alc_tx_media_type", nullable = false, length=200)
	private String mimetype;
	
	@Lob
	@Column(name="alc_bl_capa", nullable = false)
	private byte[] data;
	
	@ManyToOne
	@JoinColumn(name = "usu_cd_id")
	private Usuario usuario;
	
	@Column(name = "alc_dt_cadastro", columnDefinition = "TIMESTAMP")
	private LocalDateTime dataCadastro;
	
	@Column(name = "alc_dt_altercao", columnDefinition = "TIMESTAMP")
	private LocalDateTime dataAlteracao;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMimetype() {
		return mimetype;
	}

	public void setMimetype(String mimetype) {
		this.mimetype = mimetype;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}
	
	

	public Album getAlbum() {
		return album;
	}

	public void setAlbum(Album album) {
		this.album = album;
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
		Capa other = (Capa) obj;
		return Objects.equals(id, other.id);
	}

	
}
