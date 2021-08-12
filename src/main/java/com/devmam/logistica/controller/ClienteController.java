package com.devmam.logistica.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.devmam.logistica.domain.model.Cliente;
import com.devmam.logistica.domain.repository.ClienteRepository;
import com.devmam.logistica.domain.service.CatalogoClienteService;

import lombok.AllArgsConstructor;



@AllArgsConstructor
@RestController
@RequestMapping("/clientes")
public class ClienteController {
	
	
	private ClienteRepository clienteRepository;
	
	private CatalogoClienteService catalogoClienteService;
	
	
	@GetMapping
	public List<Cliente> listar() {
		return clienteRepository.findAll();
	}
	
	
	@GetMapping("/{clienteId}")
	public ResponseEntity<Cliente> buscar( @PathVariable Long clienteId ) {
		return clienteRepository.findById(clienteId)
				.map(ResponseEntity::ok)
				.orElse( ResponseEntity.notFound().build() );
	}
	
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente adicionar(@Valid @RequestBody Cliente cliente ) {
		return catalogoClienteService.salvar(cliente);
		//return clienteRepository.save(cliente);
	}
	
	
	@PutMapping("/{clienteId}")
	public ResponseEntity<Cliente> atualizar(@PathVariable Long clienteId, 
			@Valid @RequestBody Cliente cliente ) {
		if( !clienteRepository.existsById(clienteId) ) {
			return ResponseEntity.notFound().build();
		}
		
		
		cliente.setId(clienteId);
		cliente = catalogoClienteService.salvar(cliente);
		//cliente =  clienteRepository.save(cliente);
		 
		return ResponseEntity.ok(cliente);
	}
	
	
	@DeleteMapping("/{clienteId}")
	public ResponseEntity<Void> remover(@PathVariable Long clienteId) {
		if( !clienteRepository.existsById(clienteId) ) {
			return ResponseEntity.notFound().build();
		}
		
		catalogoClienteService.excluir(clienteId);
		//clienteRepository.deleteById(clienteId);
		
		return ResponseEntity.noContent().build();
			
	}
	

	
	
	
	

}


//Imprementando o CRUD



/*
 * 
 * 	@GetMapping("/clientes/{clienteId}")
	public ResponseEntity<Cliente> buscar( @PathVariable Long clienteId ) {
		return clienteRepository.findById(clienteId)
				.map(cliente -> ResponseEntity.ok(cliente))
				.orElse( ResponseEntity.notFound().build() );
	}
	
 * 
 * 	@GetMapping("/clientes/{clienteId}")
	public ResponseEntity<Cliente> buscar(@PathVariable Long clienteId) {
		Optional<Cliente> cliente =  clienteRepository.findById(clienteId);
		
		if( cliente.isPresent() ) {
			return ResponseEntity.ok( cliente.get() );
		}
		
		return ResponseEntity.notFound().build();
	}
 * 
 * 
 * 
 	@GetMapping("/clientes/partenome")
	public List<Cliente> listarParteNome() {
		return clienteRepository.findByNomeContaining("a");

	}
	
	
	@GetMapping("/clientes/email")
	public List<Cliente> listarEmail() {
		return clienteRepository.findByEmail("joao@gmail.com");

	}
	
	
	@GetMapping("/clientes/telefone")
	public List<Cliente> listarTelefoneContaining() {
		return clienteRepository.findByTelefoneContaining("8181");
	}
 * */






