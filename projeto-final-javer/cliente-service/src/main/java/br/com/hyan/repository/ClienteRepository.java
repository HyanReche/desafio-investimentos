package br.com.hyan.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.hyan.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{

}
