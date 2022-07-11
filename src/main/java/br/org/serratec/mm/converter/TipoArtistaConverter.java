package br.org.serratec.mm.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import br.org.serratec.mm.enums.TipoArtista;

@Converter(autoApply = true)
public class TipoArtistaConverter implements AttributeConverter<TipoArtista, String> {
	@Override
	public String convertToDatabaseColumn(TipoArtista tipoArtista) {
		return tipoArtista.getTipo();
	}

	@Override
	public TipoArtista convertToEntityAttribute(String valor) {
		
		if (valor==null) {
			return null;
		}
		for(TipoArtista tipo : TipoArtista.values()) {
			if (tipo.getTipo().equalsIgnoreCase(valor)) {
				return tipo;
			}
		}
		throw new IllegalArgumentException("O valor " + valor + " não é tipo de artista!");
	}

}
