package com.capgemini.teste.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.teste.entity.Sequencia;
import com.capgemini.teste.service.SequenciaService;

@RestController
@RequestMapping("/sequence")
public class SequenciaController {
 
    @Autowired
    private SequenciaService sequenciaService;

    @PostMapping
    public ResponseEntity<Boolean> saveSequencia(@Validated @RequestBody Sequencia sequencia) {
    	boolean isValid = this.sequenciaService.validate(sequencia);
    	this.sequenciaService.saveToDatabase(sequencia);
		return ResponseEntity.ok().body(isValid);
    }
}
