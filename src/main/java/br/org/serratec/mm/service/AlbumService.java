package br.org.serratec.mm.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import br.org.serratec.mm.dto.AlbumDTO;
import br.org.serratec.mm.dto.AlbumListDTO;
import br.org.serratec.mm.dto.ArtistaDTO;
import br.org.serratec.mm.dto.LyricsDTO;
import br.org.serratec.mm.dto.MusicaLetraDTO;
import br.org.serratec.mm.exception.DataNotFoundException;
import br.org.serratec.mm.model.Album;
import br.org.serratec.mm.model.Artista;
import br.org.serratec.mm.model.Capa;
import br.org.serratec.mm.model.Musica;
import br.org.serratec.mm.repository.AlbumRepository;
import br.org.serratec.mm.repository.CapaRepository;
import br.org.serratec.mm.util.MMUtil;

@Service
public class AlbumService {
	@Autowired
	private AlbumRepository albumRepository;
	
	@Autowired
	private CapaRepository capaRepository;
	@Autowired 
	private MMUtil mmUtil;
	@Autowired 
	private MusicaService musicaService;
	
	@Autowired
	private ArtistaService artistaService;
	
	
	
	public AlbumDTO findById(Long id) throws DataNotFoundException {
		Optional<AlbumDTO> optionalAlbum = albumRepository.findById(id).map(AlbumDTO::new);
		return optionalAlbum.orElseThrow(() -> new DataNotFoundException(Album.class, id));
	}



	@Transactional
	public AlbumDTO insert(@Valid AlbumDTO albumDTO) throws DataNotFoundException {
		return insert(albumDTO, null);
	}
	@Transactional
	public AlbumDTO insert(@Valid AlbumDTO albumDTO, MultipartFile file) throws DataNotFoundException {
		Album album = new Album();
		
		
		album.setArtista(new Artista(artistaService.findById(albumDTO.getIdArtista())));
		album.getArtista().setId(albumDTO.getIdArtista());
		album.setTitulo(albumDTO.getTitulo());
		
		if (albumDTO.getMusicas()!=null) {
		
			album.setMusicas(albumDTO.getMusicas().stream().map(m -> {
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
		}
		album.setDataCadastro(LocalDateTime.now());
		album.setDataAlteracao(LocalDateTime.now());
		album.setUsuario(mmUtil.getUsuarioLogado());
		Capa capa = new Capa();
		capa.setMimetype(file.getContentType());
		try {
			capa.setData(file.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		capa.setDataCadastro(LocalDateTime.now());
		capa.setDataAlteracao(LocalDateTime.now());
		capa.setUsuario(mmUtil.getUsuarioLogado());

		album = albumRepository.save(album);

		capa.setAlbum(album);
		capaRepository.save(capa);
		
		
		return new AlbumDTO(albumRepository.save(album));
	}
	
	@Transactional
	public AlbumDTO update(Long id, AlbumDTO albumDTO) throws DataNotFoundException{
		Optional<Album> optionalAlbum = albumRepository.findById(id);
		if (optionalAlbum.isEmpty()) {
			throw new DataNotFoundException(Album.class, id);
		}
		Album albumDB = optionalAlbum.get();
		if (!albumDB.getArtista().getId().equals(albumDTO.getIdArtista())){
			albumDB.setArtista(new Artista());
			albumDB.getArtista().setId(albumDTO.getIdArtista());
		}
		albumDB.setTitulo(albumDTO.getTitulo());
		albumDB.setMusicas(albumDTO.getMusicas().stream().map(m -> {
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
		albumDB.setDataAlteracao(LocalDateTime.now());
		albumDB.setUsuario(mmUtil.getUsuarioLogado());

		return new AlbumDTO(albumRepository.save(albumDB));
	}
	
	@Transactional
	public void delete(Long id) throws DataNotFoundException {
		Optional<Album> optionalAlbum = albumRepository.findById(id);
		if (optionalAlbum.isEmpty()) {
			throw new DataNotFoundException(Album.class, id);
		}
		albumRepository.delete(optionalAlbum.get());
	}


	public List<AlbumListDTO> listAll() {
		return albumRepository.listaAlbunsComMinutos();
	}
	
	@Transactional
	public MusicaLetraDTO buscaLetraMusica(Long idAlbum, Long idMusica) throws DataNotFoundException {
		Optional<Album> opAlbum = albumRepository.findById(idAlbum);
		if (opAlbum.isEmpty()) {
			throw new DataNotFoundException(Album.class, idAlbum);
		}
		String artista = opAlbum.get().getArtista().getNome();
		Optional<Musica> opMusica = opAlbum.get().getMusicas().stream().filter(m->m.getId().equals(idMusica)).findAny();
		if (opMusica.isEmpty()) {
			throw new DataNotFoundException(Musica.class, idMusica);
		}
		String musica = opMusica.get().getTitulo().replaceAll("'", "");
		RestTemplate restTemplate = new RestTemplate();
		String url = String.format("https://api.lyrics.ovh/v1/%s/%s", MMUtil.encode(artista), MMUtil.encode(musica));
		ResponseEntity<LyricsDTO> response = restTemplate.getForEntity(url, LyricsDTO.class);
		if (response.getStatusCode()!=HttpStatus.OK) {
			throw new DataNotFoundException("Letra não encontrada!");
		}
		LyricsDTO lyrics=response.getBody();

		return new MusicaLetraDTO(opMusica.get(), lyrics.getLyrics().replaceAll("\r\n", "<br>").replaceAll("\n", "<br>"));
		
	}


	public Capa getCapa(Long id) throws DataNotFoundException {
		Optional<Album> optionalAlbum = albumRepository.findById(id);
		if (optionalAlbum.isEmpty()) {
			throw new DataNotFoundException(Album.class, id);
		}
		return optionalAlbum.get().getCapa();
	}

}
