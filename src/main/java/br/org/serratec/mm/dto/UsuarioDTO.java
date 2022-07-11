package br.org.serratec.mm.dto;

import br.org.serratec.mm.model.Usuario;

public class UsuarioDTO {
	private Long id;
	private String nome;
	private String email;
	private String perfilUsuario;
	public UsuarioDTO() {
		super();
	}
	public UsuarioDTO(Usuario usuario) {
		this.id=usuario.getId();
		this.nome=usuario.getNome();
		this.email=usuario.getEmail();
		this.perfilUsuario=usuario.getPerfilUsuario();
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPerfilUsuario() {
		return perfilUsuario;
	}
	public void setPerfilUsuario(String perfilUsuario) {
		this.perfilUsuario = perfilUsuario;
	}
	
	
}
