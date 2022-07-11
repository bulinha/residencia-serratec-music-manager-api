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
import org.springframework.web.bind.annotation.RestController;

import br.org.serratec.mm.dto.MusicaDTO;
import br.org.serratec.mm.exception.DataNotFoundException;
import br.org.serratec.mm.service.MusicaService;

@RestController

@RequestMapping("/api/musica")
public class MusicaController {
	
	@Autowired
	private MusicaService musicaService;
	
	//swagger @ApiOperation("Retorna a lista de musicas")
	@GetMapping()
	public ResponseEntity<List<MusicaDTO>> listaMusica(){
		return ResponseEntity.ok(musicaService.listAll());
	}
	
	@GetMapping(path="/{id}")
	public ResponseEntity<MusicaDTO> getMusica(@PathVariable Long id) throws DataNotFoundException{
		return ResponseEntity.ok(musicaService.findById(id));
	}
	
	@PostMapping
	public ResponseEntity<MusicaDTO> insertMusica(@Valid @RequestBody MusicaDTO musica) {
		return ResponseEntity.ok(musicaService.insert(musica));
	}
	
	@PutMapping(path ="/{id}")
	public ResponseEntity<MusicaDTO> atualizaMusica(@PathVariable Long id, @Valid @RequestBody MusicaDTO musica) throws DataNotFoundException{
		return ResponseEntity.ok(musicaService.update(id, musica));
	}
	
	@DeleteMapping(path="/{id}")
	public ResponseEntity<Void> apagaMusica(@PathVariable Long id) throws DataNotFoundException{
		musicaService.delete(id);
		return ResponseEntity.noContent().build();
	}

}
