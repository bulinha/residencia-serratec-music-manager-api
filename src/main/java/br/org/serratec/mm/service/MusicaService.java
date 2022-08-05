package br.org.serratec.mm.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.org.serratec.mm.dto.MusicaDTO;
import br.org.serratec.mm.exception.DataNotFoundException;
import br.org.serratec.mm.model.Musica;
import br.org.serratec.mm.repository.MusicaRepository;
import br.org.serratec.mm.util.MMUtil;

@Service
public class MusicaService {
	@Autowired
	private MusicaRepository musicaRepository;
	@Autowired 
	private MMUtil mmUtil;
	
	public MusicaDTO findById(Long id) throws DataNotFoundException {
		Optional<MusicaDTO> optionalMusica = musicaRepository.findById(id).map(MusicaDTO::new);
		return optionalMusica.orElseThrow(() -> new DataNotFoundException(Musica.class, id));
	}


	@Transactional
	public Musica insert(@Valid Musica musica) {
		musica.setUsuario(mmUtil.getUsuarioLogado());
		musica.setDataAlteracao(LocalDateTime.now());
		musica.setDataCadastro(LocalDateTime.now());
		return musicaRepository.save(musica);
	}
	
	@Transactional
	public MusicaDTO insert(@Valid MusicaDTO musicaDTO) {
		Musica musica = new Musica(musicaDTO);
		return new MusicaDTO(insert(musica));
	}
	
	@Transactional
	public MusicaDTO update(Long id, MusicaDTO musicaDTO) throws DataNotFoundException{
		return new MusicaDTO(update(id,new Musica(musicaDTO)));
	}
	
	@Transactional
	public Musica update(Long id, Musica musica) throws DataNotFoundException{
		Optional<Musica> optionalMusica = musicaRepository.findById(id);
		if (optionalMusica.isEmpty()) {
			throw new DataNotFoundException(Musica.class, id);
		}
		Musica musicaDB = optionalMusica.get();
		musicaDB.setTitulo(musica.getTitulo());
		musicaDB.setMinutos(musica.getMinutos());
		musicaDB.setUsuario(mmUtil.getUsuarioLogado());
		musicaDB.setDataAlteracao(LocalDateTime.now());
		
		return musicaRepository.save(musicaDB);
	}
	
	@Transactional
	public void delete(Long id) throws DataNotFoundException {
		Optional<Musica> optionalMusica = musicaRepository.findById(id);
		if (optionalMusica.isEmpty()) {
			throw new DataNotFoundException(Musica.class, id);
		}
		musicaRepository.delete(optionalMusica.get());
	}


	
	public List<MusicaDTO> listAll() {
		return musicaRepository.findAll().stream().map(MusicaDTO::new).collect(Collectors.toList());
	}

}
