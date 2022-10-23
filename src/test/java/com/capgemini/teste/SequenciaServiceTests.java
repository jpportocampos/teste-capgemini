package com.capgemini.teste;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.json.simple.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.capgemini.teste.entity.Sequencia;
import com.capgemini.teste.service.SequenciaService;

@SpringBootTest
class SequenciaServiceTests {

	@Autowired
	private SequenciaService sequenciaService;
	private Sequencia sequencia;

	@BeforeEach
	void setUp() {
		sequencia = new Sequencia();
	}

	@SuppressWarnings("unchecked")
	@Test
	@DisplayName("Testing example case")
	void testExample() {
		String[] letters = new String[]{"DUHBHB", "DUBUHD", "UBUUHU", "BHBDHH", "DDDDUB", "UDBDUH"};

		sequencia.setLetters(letters);

		JSONObject response = this.sequenciaService.validate(sequencia);
		JSONObject expected = new JSONObject();
		expected.put("is_valid", true);

		assertEquals(response, expected);
	}

	@SuppressWarnings("unchecked")
	@Test
	@DisplayName("Testing double horizontals")
	void testDoubleHorirontal() {
		String[] letters = new String[]{"DUHBHB", "DUBUHD", "UHUUHU", "BBBBDH", "DDDDUB", "UDBDUH"};

		sequencia.setLetters(letters);

		JSONObject response = this.sequenciaService.validate(sequencia);
		JSONObject expected = new JSONObject();
		expected.put("is_valid", true);

		assertEquals(response, expected);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	@DisplayName("Testing double verticals")
	void testDoubleVertical() {
		String[] letters = new String[]{"DUHBHB", "DUBUHD", "DHUUHU", "DBBBHH", "DDUDUB", "UDBDUH"};

		sequencia.setLetters(letters);

		JSONObject response = this.sequenciaService.validate(sequencia);
		JSONObject expected = new JSONObject();
		expected.put("is_valid", true);

		assertEquals(expected, response);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	@DisplayName("Testing double diagonals")
	void testDoubleDiagonal() {
		String[] letters = new String[]{"DUHBHB", "BDBUHD", "DBDUHU", "BBBDDH", "DDUBUB", "UDBDUH"};

		sequencia.setLetters(letters);

		JSONObject response = this.sequenciaService.validate(sequencia);
		JSONObject expected = new JSONObject();
		expected.put("is_valid", true);

		assertEquals(expected, response);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	@DisplayName("Testing one horizontal")
	void testOneHorizontal() {
		String[] letters = new String[]{"DDDDUB", "BDHUHD", "DBDUHU", "DUBUHH", "DDUHUB", "UDBDUH"};

		sequencia.setLetters(letters);

		JSONObject response = this.sequenciaService.validate(sequencia);
		JSONObject expected = new JSONObject();
		expected.put("is_valid", false);

		assertEquals(expected, response);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	@DisplayName("Testing one vertical")
	void testOneVertical() {
		String[] letters = new String[]{"DDDHHB", "BDHUHD", "DBDUHU", "DUBUHH", "DDUHUB", "UDBDUH"};

		sequencia.setLetters(letters);

		JSONObject response = this.sequenciaService.validate(sequencia);
		JSONObject expected = new JSONObject();
		expected.put("is_valid", false);

		assertEquals(expected, response);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	@DisplayName("Testing one diagonal")
	void testOneDiagonal() {
		String[] letters = new String[]{"DDDHHB", "HDHUHD", "DBDUHU", "DUBDDH", "DDUBUB", "UDBDUH"};

		sequencia.setLetters(letters);

		JSONObject response = this.sequenciaService.validate(sequencia);
		JSONObject expected = new JSONObject();
		expected.put("is_valid", false);

		assertEquals(expected, response);
	}

}
