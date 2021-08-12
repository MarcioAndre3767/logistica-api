package com.devmam.logistica.domain.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devmam.logistica.domain.exception.NegocioException;
import com.devmam.logistica.domain.model.Cliente;
import com.devmam.logistica.domain.repository.ClienteRepository;

import lombok.AllArgsConstructor;


@AllArgsConstructor
@Service
public class CatalogoClienteService {
	
	private ClienteRepository clienteRepository;
	
	
	@Transactional
	public Cliente salvar(Cliente cliente) {
		
		//criando a regra de negócio(email não pode ser duplicados)		
		boolean emailEmUso = clienteRepository.findByEmail(cliente.getEmail())
				.stream()
				.anyMatch( clienteExistente -> !clienteExistente.equals(cliente) );
		
		if ( emailEmUso ) {
			throw new NegocioException("Ja existe um cliente cadastrado com este e-mail.");
		}		
		//fim regra de serviço
		
		return clienteRepository.save(cliente);
	}
	
	
	@Transactional
	public void excluir(Long clienteId) {
		clienteRepository.deleteById(clienteId);
	}

}





