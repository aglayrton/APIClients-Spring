package com.devsuperiorcapitulo1.resource;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.devsuperiorcapitulo1.ClientService;
import com.devsuperiorcapitulo1.dto.ClientDto;

@RestController
@RequestMapping("/clients")
public class ClientResource {
	
	@Autowired
	private ClientService service;
	
	@GetMapping
	public ResponseEntity<Page<ClientDto>> findAll(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linePerPage", defaultValue = "10") Integer linePerPage,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction,
			@RequestParam(value = "orderBy", defaultValue = "name") String orderBy
	){
		PageRequest pageRequest = PageRequest.of(page, linePerPage, Direction.valueOf(direction), orderBy);
		Page<ClientDto> clientDto = service.findAllPaged(pageRequest);
		return ResponseEntity.ok().body(clientDto);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ClientDto> findById(@PathVariable("id") Long id){
		ClientDto clientDto = service.findById(id);
		return ResponseEntity.ok().body(clientDto);
	}
	
	@PostMapping()
	public ResponseEntity<ClientDto> insert(@RequestBody ClientDto clientDto){
		
		clientDto = service.save(clientDto);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(clientDto.getId()).toUri();
		
		return ResponseEntity.created(uri).body(clientDto);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ClientDto> update(@PathVariable("id") Long id, @RequestBody ClientDto clientDto){
		
		clientDto = service.update(id, clientDto);
		
		return ResponseEntity.ok().body(clientDto);
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	

	
}
