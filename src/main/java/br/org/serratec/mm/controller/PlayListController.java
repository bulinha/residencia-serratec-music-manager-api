package br.org.serratec.mm.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import br.org.serratec.mm.dto.MusicaDTO;
import br.org.serratec.mm.dto.PlayListDTO;
import br.org.serratec.mm.exception.DataNotFoundException;
import br.org.serratec.mm.service.PlayListService;

@RestController

@RequestMapping("/api/playlist")
public class PlayListController {
	
	@Autowired
	private PlayListService playListService;
	//swagger @ApiOperation("Retorna a lista de playLists")
	@GetMapping()
	public ResponseEntity<List<PlayListDTO>> listaPlayList(){
		return ResponseEntity.ok(playListService.listAll());
	}
	
	@GetMapping(path="/{id}")
	public ResponseEntity<PlayListDTO> getPlayList(@PathVariable Long id) throws DataNotFoundException{
		return ResponseEntity.ok(playListService.findById(id));
	}
	
	@PostMapping
	public ResponseEntity<PlayListDTO> insertPlayList( @Valid @RequestPart("playList") PlayListDTO playList) {
		return ResponseEntity.ok(playListService.insert(playList));
	}
	
	@PutMapping(path="/{id}")
	public ResponseEntity<PlayListDTO> atualizar(@PathVariable Long id, @RequestBody PlayListDTO playList) throws DataNotFoundException  {
		return ResponseEntity.ok(playListService.update(id, playList));
	}
	
	@DeleteMapping(path="/{id}")
	public ResponseEntity<Void> apagaPlayList(@PathVariable Long id){
		return ResponseEntity.noContent().build();
	}
	
	@PostMapping(path="/{id}/add-musica")
	public ResponseEntity<PlayListDTO> addMusica(@PathVariable Long id,  @RequestBody MusicaDTO musica) throws DataNotFoundException{
		return ResponseEntity.ok(playListService.addMusica(id, musica.getId()));
	}

}
