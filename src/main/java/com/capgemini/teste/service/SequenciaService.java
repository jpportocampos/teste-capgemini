package com.capgemini.teste.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.teste.entity.Sequencia;
import com.capgemini.teste.entity.SequenciaRepository;

@Service
public class SequenciaService {

	@Autowired
	private SequenciaRepository sequenciaRepository;

	public boolean validate(Sequencia sequencia) {
		return false;
	}

	public void saveToDatabase(Sequencia sequencia) {
		sequenciaRepository.save(sequencia);
	}
}
