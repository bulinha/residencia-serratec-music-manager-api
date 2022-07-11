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

import br.org.serratec.mm.dto.UsuarioDTO;
import br.org.serratec.mm.dto.UsuarioInsertDTO;
import br.org.serratec.mm.dto.UsuarioUpdateDTO;
import br.org.serratec.mm.exception.DataNotFoundException;
import br.org.serratec.mm.service.UsuarioService;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController

@RequestMapping("/api/usuario")
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	//swagger @ApiOperation("Retorna a lista de usuarios")
	@GetMapping()
	public ResponseEntity<List<UsuarioDTO>> listaUsuario(){
		return  ResponseEntity.ok(usuarioService.listAll());
	}
	
	@GetMapping(path="/{id}")
	public ResponseEntity<UsuarioDTO> getUsuario(@PathVariable Long id) throws DataNotFoundException{
		return ResponseEntity.ok(usuarioService.findById(id));
	}
	
	@ApiOperation("Insere um novo usuario")
	@PostMapping
	public ResponseEntity<UsuarioDTO> insertUsuario(@Valid @RequestBody UsuarioInsertDTO usuario) {
		return ResponseEntity.ok(usuarioService.insert(usuario));
	}
	
	@PutMapping(path ="/{id}")
	public ResponseEntity<UsuarioDTO> atualizaUsuario(@PathVariable Long id, @Valid @RequestBody UsuarioUpdateDTO usuario) throws DataNotFoundException{
		return ResponseEntity.ok(usuarioService.update(id, usuario));
	}
	
	@DeleteMapping(path="/{id}")
	public ResponseEntity<Void> apagaUsuario(@PathVariable Long id) throws DataNotFoundException{
		usuarioService.delete(id);
		return ResponseEntity.noContent().build();
	}

}
