package com.capgemini.teste.service;

import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.teste.entity.Sequencia;
import com.capgemini.teste.entity.SequenciaRepository;

@Service
public class SequenciaService {

	@Autowired
	private SequenciaRepository sequenciaRepository;

	// Método que valida as Sequencias e retorna um JSONObject para devolução de resposta como solicitada
	@SuppressWarnings("unchecked")
	public JSONObject validate(Sequencia sequencia) {
		JSONObject response = new JSONObject(); // JSONObject que será retornado
		String[] seqArray = sequencia.getLetters(); // Array de Strings que é preenchido com as Letters da Sequencia
		int contLetraHor = 1; // Contador utilizado para as Sequencias horizontais
		int contLetraVer = 1; // Contador utilizado para as Sequencias verticais
		int contLetraDiagDir = 1; // Contador utilizado para as Sequencias diagonais para a direita
		int contLetraDiagEsq = 1; // Contador utilizado para as Sequencias diagoanis para a esquerda
		int contSeq = 0; // Contador utilizado para o total de Sequencias

		// Condição if para verificar se uma Sequencia é inválida pelo seu tamanho (3x3 ou menor)
		if (seqArray[0].length() < 4) {
			sequencia.setValid(false);
			response.put("is_valid", false);
			return response;
		}

		// Loop for duplo para verificar as Sequencias horizontais, verticais e diagonais para a direita
		for (int i = 0; i < seqArray.length - 1; i++) {
			for (int j = 0; j < seqArray[i].length() - 1; j++) {
				// Condição if para verificar os caracteres de forma diagonal para a direita e para verificar se dois seguidos são iguais
				if (j + i + 1 < seqArray[j].length()
						&& seqArray[j + i].charAt(j) == seqArray[j + i + 1].charAt(j + 1)) {
					// Condição if para verificar se os caracteres a serem comparados são válidos e incrementar seu contador caso sejam
					if ((seqArray[j + i].charAt(j) == 'D' || seqArray[j + i].charAt(j) == 'B'
							|| seqArray[j + i].charAt(j) == 'U' || seqArray[j + i].charAt(j) == 'H')
							&& (seqArray[j + i + 1].charAt(j + 1) == 'D' || seqArray[j + i + 1].charAt(j + 1) == 'B'
									|| seqArray[j + i + 1].charAt(j + 1) == 'U'
									|| seqArray[j + i + 1].charAt(j + 1) == 'H')) {
						contLetraDiagDir++;
						// Condição if para incrementar o contador de sequencia se for o caso
						if (contLetraDiagDir == 4) {
							contSeq++;
							contLetraDiagDir = 1;
						}
					// Caso um caractere não seja válido, a Sequencia é invalidada
					} else {
						sequencia.setValid(false);
						response.put("is_valid", false);
						return response;
					}
				// Caso os caracteres não sejam iguais, o contador é reiniciado	
				} else {
					contLetraDiagDir = 1;
				}

				// Condição if para verificar os caracteres de forma horizontal e para verificar se dois seguidos são iguais
				if (seqArray[i].charAt(j) == seqArray[i].charAt(j + 1)) {
					// Condição if para verificar se os caracteres a serem comparados são válidos e incrementar seu contador caso sejam
					if ((seqArray[i].charAt(j) == 'D' || seqArray[i].charAt(j) == 'B' || seqArray[i].charAt(j) == 'U'
							|| seqArray[i].charAt(j) == 'H')
							&& (seqArray[i].charAt(j + 1) == 'D' || seqArray[i].charAt(j + 1) == 'B'
									|| seqArray[i].charAt(j + 1) == 'U' || seqArray[i].charAt(j + 1) == 'H')) {
						contLetraHor++;
						// Condição if para incrementar o contador de sequencia se for o caso
						if (contLetraHor == 4) {
							contSeq++;
							contLetraHor = 1;
						}
					// Caso um caractere não seja válido, a Sequencia é invalidada
					} else {
						sequencia.setValid(false);
						response.put("is_valid", false);
						return response;
					}
				// Caso os caracteres não sejam iguais, o contador é reiniciado	
				} else {
					contLetraHor = 1;
				}

				// Condição if para verificar os caracteres de forma vertical e para verificar se dois seguidos são iguais
				if (seqArray[j].charAt(i) == seqArray[j + 1].charAt(i)) {
					// Condição if para verificar se os caracteres a serem comparados são válidos e incrementar seu contador caso sejam
					if ((seqArray[j].charAt(i) == 'D' || seqArray[j].charAt(i) == 'B' || seqArray[j].charAt(i) == 'U'
							|| seqArray[j].charAt(i) == 'H')
							&& (seqArray[j + 1].charAt(i) == 'D' || seqArray[j + 1].charAt(i) == 'B'
									|| seqArray[j + 1].charAt(i) == 'U' || seqArray[j + 1].charAt(i) == 'H')) {
						contLetraVer++;
						// Condição if para incrementar o contador de sequencia se for o caso
						if (contLetraVer == 4) {
							contSeq++;
							contLetraVer = 1;
						}
					// Caso um caractere não seja válido, a Sequencia é invalidada
					} else {
						sequencia.setValid(false);
						response.put("is_valid", false);
						return response;
					}
				// Caso os caracteres não sejam iguais, o contador é reiniciado		
				} else {
					contLetraVer = 1;
				}

			}
			// Após uma volta completa no loop menor, os contadores são reiniciados
			contLetraHor = 1;
			contLetraVer = 1;
			contLetraDiagDir = 1;
			
			// Condição if para validar a Sequencia se pelo menos 2 sequências de 4 letras iguais foram encontradas
			if (contSeq >= 2) {
				sequencia.setValid(true);
				response.put("is_valid", true);
				return response;
			}
		}

		// Loop for duplo para verificar as Sequencias diagonais para a esquerda
		for (int i = seqArray.length - 1; i > -1; i--) {
			for (int j = 0; j < seqArray.length; j++) {
				// Condição if para verificar os caracteres de forma diagonal para a esquerda e para verificar se dois seguidos são iguais
				if (j + 1 < seqArray[j].length() && (i - j) + 1 < seqArray[j].length() && i >= j
						&& seqArray[i - j].charAt(j + 1) == seqArray[(i - j) + 1].charAt(j)) {
					// Condição if para verificar se os caracteres a serem comparados são válidos e incrementar seu contador caso sejam
					if ((seqArray[i - j].charAt(j + 1) == 'D' || seqArray[i - j].charAt(j + 1) == 'B'
							|| seqArray[i - j].charAt(j + 1) == 'U' || seqArray[i - j].charAt(j + 1) == 'H')
							&& (seqArray[(i - j) + 1].charAt(j) == 'D' || seqArray[(i - j) + 1].charAt(j) == 'B'
									|| seqArray[(i - j) + 1].charAt(j) == 'U'
									|| seqArray[(i - j) + 1].charAt(j) == 'H')) {
						contLetraDiagEsq++;
						// Condição if para incrementar o contador de sequencia se for o caso
						if (contLetraDiagEsq == 4) {
							contSeq++;
							contLetraDiagEsq = 1;
						}
					// Caso um caractere não seja válido, a Sequencia é invalidada	
					} else {
						sequencia.setValid(false);
						response.put("is_valid", false);
						return response;
					}
				// Caso os caracteres não sejam iguais, o contador é reiniciado	
				} else {
					contLetraDiagEsq = 1;
				}
				// Após uma volta completa no loop menor, o contador é reiniciado
				contLetraDiagEsq = 1;

				// Condição if para validar a Sequencia se pelo menos 2 sequências de 4 letras iguais foram encontradas
				if (contSeq >= 2) {
					sequencia.setValid(true);
					response.put("is_valid", true);
					return response;
				}
			}
		}

		// Condição if para verificar se não foi atingido o número necessário de sequências desejadas
		if (contSeq < 2) {
			sequencia.setValid(false);
			response.put("is_valid", false);
			return response;
		// Caso o contador de sequências não seja menor do que 2, a Sequencia é validada
		} else {
			sequencia.setValid(true);
			response.put("is_valid", true);
			return response;
		}
	}

	// Método para salvar uma sequencia na base de dados
	public void saveToDatabase(Sequencia sequencia) {
		sequenciaRepository.save(sequencia);
	}

	// Método que calcula e retorna um JSONObject com o número total de Sequencias válidas, inválidas e a proporção de Sequencias válidas em relação ao total.
	@SuppressWarnings("unchecked")
	public JSONObject getSequenciaInfo() {
		JSONObject info = new JSONObject(); //JSONObject que será retornado
		
		List<Sequencia> sequenciaIsValid = this.sequenciaRepository.getSequenciaIsValid(); // Lista de Sequencias válidas presentes no banco
		List<Sequencia> sequenciaIsNotValid = this.sequenciaRepository.getSequenciaNotValid(); // Lista de Sequencias inválidas presentes no banco

		int countValid = sequenciaIsValid.size(); // Número de Sequencias válidas
		int countInvalid = sequenciaIsNotValid.size(); // Número de Sequencias inválidas

		int total = countValid + countInvalid; // Total de Sequencias presentes no banco

		double ratio = (double) countValid / (double) total; // Proporção de Sequencias válidas em relação ao total

		// Adicionando os dados adquiridos no JSONObject e o retornando
		info.put("count_valid", countValid);
		info.put("count_invalid", countInvalid);
		info.put("ratio", ratio);

		return info;
	}
}
