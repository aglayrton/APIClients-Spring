package com.devsuperiorcapitulo1;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperiorcapitulo1.dto.ClientDto;
import com.devsuperiorcapitulo1.entities.Client;
import com.devsuperiorcapitulo1.repository.ClientRepository;
import com.devsuperiorcapitulo1.service.exception.ResourceNotFoundException;

@Service
public class ClientService {
	
	@Autowired
	private ClientRepository repository;
	
	@Transactional(readOnly = true)
	public Page<ClientDto> findAllPaged(PageRequest pageRequest){
		Page<Client> list = repository.findAll(pageRequest);
		return list.map(x -> new ClientDto(x));
	}

	public ClientDto findById(Long id) {
		Optional<Client> client = repository.findById(id);
		Client entity = client.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
		return new ClientDto(entity);
	}
	
	
}
