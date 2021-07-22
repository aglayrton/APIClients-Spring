package com.devsuperiorcapitulo1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.devsuperiorcapitulo1.dto.ClientDto;
import com.devsuperiorcapitulo1.entities.Client;
import com.devsuperiorcapitulo1.repository.ClientRepository;

@Service
public class ClientService {
	
	@Autowired
	private ClientRepository repository;
	
	public Page<ClientDto> findAllPaged(PageRequest pageRequest){
		Page<Client> list = repository.findAll(pageRequest);
		return list.map(x -> new ClientDto(x));
	}
	
}
