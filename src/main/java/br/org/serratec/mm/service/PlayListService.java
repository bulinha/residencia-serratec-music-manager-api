package br.org.serratec.mm.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.org.serratec.mm.dto.MusicaDTO;
import br.org.serratec.mm.dto.PlayListDTO;
import br.org.serratec.mm.exception.DataNotFoundException;
import br.org.serratec.mm.model.Album;
import br.org.serratec.mm.model.Musica;
import br.org.serratec.mm.model.PlayList;
import br.org.serratec.mm.model.Usuario;
import br.org.serratec.mm.repository.PlayListRepository;
import br.org.serratec.mm.util.MMUtil;

@Service
public class PlayListService {
	@Autowired
	private PlayListRepository playListRepository;
	@Autowired 
	private MMUtil mmUtil;
	@Autowired MusicaService musicaService;
	
	
	
	public PlayListDTO findById(Long id) throws DataNotFoundException {
		Optional<PlayList> optionalPlayList = playListRepository.findById(id);
		
		Usuario usuario = mmUtil.getUsuarioLogado();
		if (optionalPlayList.isEmpty() || !optionalPlayList.get().getUsuario().equals(usuario) ) {
			throw new DataNotFoundException(PlayList.class, id);
		}
		return new PlayListDTO(optionalPlayList.get());
	}



	@Transactional
	public PlayListDTO insert(@Valid PlayListDTO playListDTO) {
		PlayList playList = new PlayList();
		playList.setNome(playListDTO.getNome());
		playList.setMusicas(playListDTO.getMusicas().stream().map(m -> {
			if (m.getId()==null) {
				return new Musica(musicaService.insert(m));
			} else {
				try {
					return new Musica(musicaService.update(m.getId(), m));
				} catch (DataNotFoundException e) {
					return new Musica(musicaService.insert(m));
				}
			}
		}).collect(Collectors.toList()));
		playList.setDataCadastro(LocalDateTime.now());
		playList.setDataAlteracao(LocalDateTime.now());
		playList.setUsuario(mmUtil.getUsuarioLogado());
		
		return new PlayListDTO(playListRepository.save(playList));
	}
	
	@Transactional
	public PlayListDTO update(Long id, PlayListDTO playListDTO) throws DataNotFoundException{
		Optional<PlayList> optionalPlayList = playListRepository.findById(id);
		Usuario usuario = mmUtil.getUsuarioLogado();
		if (optionalPlayList.isEmpty() || !optionalPlayList.get().getUsuario().equals(usuario) ) {
			throw new DataNotFoundException(PlayList.class, id);
		}
		PlayList playListDB = optionalPlayList.get();
		
		playListDB.setNome(playListDTO.getNome());
		playListDB.setMusicas(playListDTO.getMusicas().stream().map(m -> {
			if (m.getId()==null) {
				return new Musica(musicaService.insert(m));
			} else {
				try {
					return new Musica(musicaService.update(m.getId(), m));
				} catch (DataNotFoundException e) {
					return new Musica(musicaService.insert(m));
				}
			}
		}).collect(Collectors.toList()));
		playListDB.setDataAlteracao(LocalDateTime.now());
		
		return new PlayListDTO(playListRepository.save(playListDB));
	}
	
	@Transactional
	public void delete(Long id) throws DataNotFoundException {
		Optional<PlayList> optionalPlayList = playListRepository.findById(id);
		if (optionalPlayList.isEmpty()) {
			throw new DataNotFoundException(Album.class, id);
		}
		playListRepository.delete(optionalPlayList.get());
	}


	public List<PlayListDTO> listAll() {
		Usuario usuario = mmUtil.getUsuarioLogado();
		return playListRepository.findAllList(usuario).stream().map(PlayListDTO::new).collect(Collectors.toList());
	}
	
	public PlayListDTO addMusica(Long playListId, Long musicId) throws DataNotFoundException {
		Optional<PlayList> optionalPlayList = playListRepository.findById(playListId);
		Usuario usuario = mmUtil.getUsuarioLogado();
		if (optionalPlayList.isEmpty() || !optionalPlayList.get().getUsuario().equals(usuario) ) {
			throw new DataNotFoundException(PlayList.class, playListId);
		}
		MusicaDTO musicaDTO = musicaService.findById(musicId);
		PlayList playlist = optionalPlayList.get();
		playlist.getMusicas().add(new Musica(musicaDTO));
		playlist.setDataAlteracao(LocalDateTime.now());
		return new PlayListDTO(playListRepository.save(playlist));
	}
	
	

}
