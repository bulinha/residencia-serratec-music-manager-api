package br.org.serratec.mm.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.org.serratec.mm.dto.ArtistaDTO;
import br.org.serratec.mm.exception.DataNotFoundException;
import br.org.serratec.mm.model.Artista;
import br.org.serratec.mm.repository.ArtistaRepository;
import br.org.serratec.mm.util.MMUtil;

@Service
public class ArtistaService {
	@Autowired
	private ArtistaRepository artistaRepository;
	@Autowired 
	private MMUtil mmUtil;
	
	
	
	public ArtistaDTO findById(Long id) throws DataNotFoundException {
		Optional<ArtistaDTO> optionalArtista = artistaRepository.findById(id).map(ArtistaDTO::new);
		return optionalArtista.orElseThrow(() -> new DataNotFoundException(Artista.class, id));
	}
	
   @Transactional
   public ArtistaDTO insert(@Valid ArtistaDTO artistaDTO) {
	   Artista artista = new Artista();
	   artista.setNome(artistaDTO.getNome());
	   artista.setTipo(artistaDTO.getTipo());
	   artista.setUsuario(mmUtil.getUsuarioLogado());
	   artista.setDataAlteracao(LocalDateTime.now());
	   artista.setDataCadastro(LocalDateTime.now());
	   artista = artistaRepository.save(artista);
	   return new ArtistaDTO(artista);
   }
	
	
	@Transactional
	public ArtistaDTO update(Long id, ArtistaDTO artistaDTO) throws DataNotFoundException{
		Optional<Artista> optionalArtista = artistaRepository.findById(id);
		if (optionalArtista.isEmpty()) {
			throw new DataNotFoundException(Artista.class, id);
		}
		Artista artistaDB = optionalArtista.get();
		artistaDB.setNome(artistaDTO.getNome());
		artistaDB.setTipo(artistaDTO.getTipo());
		artistaDB.setUsuario(mmUtil.getUsuarioLogado());
		artistaDB.setDataAlteracao(LocalDateTime.now());
		return new ArtistaDTO(artistaRepository.save(artistaDB));
	}
	
	@Transactional
	public void delete(Long id) throws DataNotFoundException {
		Optional<Artista> optionalArtista = artistaRepository.findById(id);
		if (optionalArtista.isEmpty()) {
			throw new DataNotFoundException(Artista.class, id);
		}
		artistaRepository.delete(optionalArtista.get());
	}


	public List<ArtistaDTO> listAll() {
		return artistaRepository.findAll().stream().map(ArtistaDTO::new).collect(Collectors.toList());
	}

}
