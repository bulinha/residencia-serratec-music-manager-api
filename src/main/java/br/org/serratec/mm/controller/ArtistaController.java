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

import br.org.serratec.mm.dto.ArtistaDTO;
import br.org.serratec.mm.exception.DataNotFoundException;
import br.org.serratec.mm.service.ArtistaService;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController

@RequestMapping("/api/artista")
public class ArtistaController {
	
	@Autowired
	private ArtistaService artistaService;
	
	//swagger @ApiOperation("Retorna a lista de artistas")
	@GetMapping()
	public ResponseEntity<List<ArtistaDTO>> listaArtista(){
		return  ResponseEntity.ok(artistaService.listAll());
	}
	
	@GetMapping(path="/{id}")
	public ResponseEntity<ArtistaDTO> getArtista(@PathVariable Long id) throws DataNotFoundException{
		return ResponseEntity.ok(artistaService.findById(id));
	}
	
	@ApiOperation("Insere um novo artista")
	@PostMapping
	public ResponseEntity<ArtistaDTO> insertArtista(@Valid @RequestBody ArtistaDTO artista) {
		return ResponseEntity.ok(artistaService.insert(artista));
	}
	
	@PutMapping(path ="/{id}")
	public ResponseEntity<ArtistaDTO> atualizaArtista(@PathVariable Long id, @Valid @RequestBody ArtistaDTO artista) throws DataNotFoundException{
		return ResponseEntity.ok(artistaService.update(id, artista));
	}
	
	@DeleteMapping(path="/{id}")
	public ResponseEntity<Void> apagaArtista(@PathVariable Long id) throws DataNotFoundException{
		artistaService.delete(id);
		return ResponseEntity.noContent().build();
	}

}
