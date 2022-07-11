package br.org.serratec.mm.enums;

public enum TipoArtista {
	SOLO("S"), BANDA("B"), DUPLA("D");

	private String tipo;

	TipoArtista(String tipo) {
		this.tipo=tipo;
	}
	
	public String getTipo() {
		return this.tipo;
	}
	
}
