package br.org.serratec.mm.dto;

import javax.validation.constraints.NotNull;

public class UsuarioUpdateDTO {
	@NotNull
	private Long id;
	@NotNull
	private String nome;
	@NotNull
	private String email;
	@NotNull
	private String perfilUsuario;
	private String senha;
	private String confirmaSenha;
	
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
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getConfirmaSenha() {
		return confirmaSenha;
	}
	public void setConfirmaSenha(String confirmaSenha) {
		this.confirmaSenha = confirmaSenha;
	}
	
	
}
