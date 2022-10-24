package com.capgemini.teste.entity;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SequenciaRepository extends CrudRepository<Sequencia, Long> {
	
	@Query(value = "SELECT * FROM sequencia s WHERE s.is_valid = 0", 
			  nativeQuery = true)
	public List<Sequencia> getSequenciaNotValid();
	
	@Query(value = "SELECT * FROM sequencia s WHERE s.is_valid = 1", 
			  nativeQuery = true)
	public List<Sequencia> getSequenciaIsValid();
}