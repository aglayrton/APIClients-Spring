package com.devsuperiorcapitulo1;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

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
@Transactional
public class ClientService {
	
	@Autowired
	private ClientRepository repository;
	
	@Transactional(readOnly = true)
	public Page<ClientDto> findAllPaged(PageRequest pageRequest){
		Page<Client> list = repository.findAll(pageRequest);
		return list.map(x -> new ClientDto(x));
	}
	
	@Transactional(readOnly = true)
	public ClientDto findById(Long id) {
		Optional<Client> client = repository.findById(id);
		Client entity = client.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
		return new ClientDto(entity);
	}

	
	public ClientDto save(ClientDto clientDto) {
		Client entity = new Client();
		
		copy(entity, clientDto);
		
		entity = repository.save(entity);

		return new ClientDto(entity);
	}
	
	public ClientDto update(Long id, ClientDto clientDto) {
		try {
			Client client = repository.getById(id);
			copy(client, clientDto);
			client = repository.saveAndFlush(client);
			return new ClientDto(client);
		}catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id não encontrado");
		}
	}

	
	
	
	private void copy(Client entity, ClientDto clientDto) {
		entity.setName(clientDto.getName());
		entity.setCpf(clientDto.getCpf());
		entity.setIncome(clientDto.getIncome());
		entity.setBirthDate(clientDto.getBirthDate());
		entity.setChildren(clientDto.getChildren());
	}

	public void delete(Long id) {
		
		try {
			repository.deleteById(id);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id não encontrado");
		}
		
	}
	
	
}
