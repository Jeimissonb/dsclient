package com.person.client.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.person.client.dto.ClientDTO;
import com.person.client.entity.Client;
import com.person.client.repositories.ClientRepository;
import com.person.client.services.exceptions.DatabaseIntegrityException;
import com.person.client.services.exceptions.ResourceNotFoundException;


@Service
public class ClientService {
	
	@Autowired
	ClientRepository repository;

	public Page<ClientDTO> findAllPaged(PageRequest pageRequest) {
		Page<Client> list = repository.findAll(pageRequest);
		return list.map(x -> new ClientDTO(x));
	}

	
	public ClientDTO insert(ClientDTO dto) {
		Client entity = new Client(dto);
		copyDtoToEntity(dto, entity);
		entity = repository.save(entity);
		return new ClientDTO(entity);
		
	}


	@Transactional
	public ClientDTO findById(Long id) {
		Optional<Client> obj = repository.findById(id);
		Client entity = obj.orElseThrow(()-> new ResourceNotFoundException("Entity not found"));
		return new ClientDTO(entity);
	}

	@Transactional
	public ClientDTO update(Long id, ClientDTO dto) {
		try {
		Client entity = repository.getOne(id);
		copyDtoToEntity(dto, entity);
		entity = repository.save(entity);
		return new ClientDTO(entity);
		} catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException("Entity with id: "+id+" not found");
		}
	}

	private void copyDtoToEntity(ClientDTO dto, Client entity) {
		entity.setName(dto.getName());
		entity.setCpf(dto.getCpf());
		entity.setBirthDate(dto.getBirthDate());
		entity.setIncome(dto.getIncome());
		entity.setChildren(dto.getChildren());
		
	}


	public void delete(Long id) {
		try {
		repository.deleteById(id);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Entity not found");
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseIntegrityException("Database integrity error");
		}
	}
}
