package com.capgemini.teste.entity;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SequenciaRepository extends CrudRepository<Sequencia, Long> {}