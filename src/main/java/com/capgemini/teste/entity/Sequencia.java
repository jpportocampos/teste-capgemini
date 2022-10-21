package com.capgemini.teste.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sequencia")
public class Sequencia {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String[] letters;
	@Column(name = "is_valid")
	private boolean isValid;

	public String[] getLetters() {
		return letters;
	}

	public void setLetters(String[] letters) {
		this.letters = letters;
	}

	public boolean isValid() {
		return isValid;
	}

	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}
}
