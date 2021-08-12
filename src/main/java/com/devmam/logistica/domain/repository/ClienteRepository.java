package com.devmam.logistica.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devmam.logistica.domain.model.Cliente;


@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {



}



/*
 		List<Cliente> findByNome(String nome);
		
		List<Cliente> findByNomeContaining(String nome);
		
		
		List<Cliente> findByEmail(String email);
		
		
		List<Cliente> findByTelefoneContaining(String telefone);
 */
