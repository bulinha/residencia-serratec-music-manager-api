package br.org.serratec.mm.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.org.serratec.mm.dto.AlbumDTO;
import br.org.serratec.mm.dto.AlbumListDTO;
import br.org.serratec.mm.dto.MusicaLetraDTO;
import br.org.serratec.mm.exception.DataNotFoundException;
import br.org.serratec.mm.model.Capa;
import br.org.serratec.mm.service.AlbumService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController

@RequestMapping("/api/album")
public class AlbumController {
	
	@Autowired
	private AlbumService albumService;
	//swagger @ApiOperation("Retorna a lista de albums")
	@GetMapping()
	public ResponseEntity<List<AlbumListDTO>> listaAlbum(){
		return ResponseEntity.ok(albumService.listAll());
	}
	
	@GetMapping(path="/{id}")
	public ResponseEntity<AlbumDTO> getAlbum(@PathVariable Long id) throws DataNotFoundException{
		return ResponseEntity.ok(albumService.findById(id));
	}
	
	@GetMapping(path="/{id}/capa")
	public ResponseEntity<byte[]> getCapa(@PathVariable Long id) throws DataNotFoundException{
		AlbumDTO album = albumService.findById(id);
		Capa capa = albumService.getCapa(id);
		HttpHeaders headers = new HttpHeaders();
		headers.add("content-type", capa.getMimetype());
		headers.add("content-length", String.valueOf(capa.getData().length));
		MimeType mimeType = MimeType.valueOf(capa.getMimetype());
		headers.add(HttpHeaders.CONTENT_DISPOSITION,
                "inline; filename="+album.getTitulo()+"."+mimeType.getSubtype());
		headers.setCacheControl(CacheControl.noCache().getHeaderValue());

		return new ResponseEntity<>(capa.getData(), headers, HttpStatus.OK);
	}
	
	
	@SecurityRequirement(name = "Bearer Authentication")
	@PostMapping
	public ResponseEntity<AlbumDTO> insertAlbum(@RequestParam("file") MultipartFile file, @Valid @RequestPart("album") AlbumDTO album) {
		return ResponseEntity.ok(albumService.insert(album, file));
	}
	
	@SecurityRequirement(name = "Bearer Authentication")
	@PutMapping(path="/{id}")
	public ResponseEntity<AlbumDTO> atualizar(@PathVariable Long id, @RequestBody AlbumDTO album) throws DataNotFoundException  {
		return ResponseEntity.ok(albumService.update(id, album));
	}
	
	@SecurityRequirement(name = "Bearer Authentication")
	@DeleteMapping(path="/{id}")
	public ResponseEntity<Void> apagaAlbum(@PathVariable Long id){
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping(path="/{id}/musica/{idMusica}/letra")
	public ResponseEntity<MusicaLetraDTO> getLetraMusica(@PathVariable Long id, @PathVariable Long idMusica) throws DataNotFoundException{
		return ResponseEntity.ok(albumService.buscaLetraMusica(id, idMusica));
	}

}
