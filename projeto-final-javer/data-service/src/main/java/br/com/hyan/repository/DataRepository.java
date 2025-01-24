package br.com.hyan.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.hyan.model.Data;

public interface DataRepository extends JpaRepository<Data, Long>{

}
