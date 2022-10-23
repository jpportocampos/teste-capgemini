package com.capgemini.teste.service;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.teste.entity.Sequencia;
import com.capgemini.teste.entity.SequenciaRepository;

@Service
public class SequenciaService {

	@Autowired
	private SequenciaRepository sequenciaRepository;

	@SuppressWarnings("unchecked")
	public JSONObject validate(Sequencia sequencia) {
		JSONObject response = new JSONObject();
		String[] seqArray = sequencia.getLetters();
		int contLetraHor = 1;
		int contLetraVer = 1;
		int contLetraDiagDir = 1;
		int contLetraDiagEsq = 1;
		int contSeq = 0;

		if (seqArray[0].length() < 4) {
			sequencia.setValid(false);
			response.put("is_valid", false);
			return response;
		}

		for (int i = 0; i < seqArray.length - 1; i++) {
			for (int j = 0; j < seqArray[i].length() - 1; j++) {
				if (j + i + 1 < seqArray[j].length()
						&& seqArray[j + i].charAt(j) == seqArray[j + i + 1].charAt(j + 1)) {
					if ((seqArray[j + i].charAt(j) == 'D' || seqArray[j + i].charAt(j) == 'B'
							|| seqArray[j + i].charAt(j) == 'U' || seqArray[j + i].charAt(j) == 'H')
							&& (seqArray[j + i + 1].charAt(j + 1) == 'D' || seqArray[j + i + 1].charAt(j + 1) == 'B'
									|| seqArray[j + i + 1].charAt(j + 1) == 'U'
									|| seqArray[j + i + 1].charAt(j + 1) == 'H')) {
						contLetraDiagDir++;
						if (contLetraDiagDir == 4) {
							contSeq++;
							contLetraDiagDir = 1;
						}
					} else {
						sequencia.setValid(false);
						response.put("is_valid", false);
						return response;
					}
				} else {
					contLetraDiagDir = 1;
				}

				if (seqArray[i].charAt(j) == seqArray[i].charAt(j + 1)) {
					if ((seqArray[i].charAt(j) == 'D' || seqArray[i].charAt(j) == 'B' || seqArray[i].charAt(j) == 'U'
							|| seqArray[i].charAt(j) == 'H')
							&& (seqArray[i].charAt(j + 1) == 'D' || seqArray[i].charAt(j + 1) == 'B'
									|| seqArray[i].charAt(j + 1) == 'U' || seqArray[i].charAt(j + 1) == 'H')) {
						contLetraHor++;
						if (contLetraHor == 4) {
							contSeq++;
							contLetraHor = 1;
						}
					} else {
						sequencia.setValid(false);
						response.put("is_valid", false);
						return response;
					}
				} else {
					contLetraHor = 1;
				}

				if (seqArray[j].charAt(i) == seqArray[j + 1].charAt(i)) {
					if ((seqArray[j].charAt(i) == 'D' || seqArray[j].charAt(i) == 'B' || seqArray[j].charAt(i) == 'U'
							|| seqArray[j].charAt(i) == 'H')
							&& (seqArray[j + 1].charAt(i) == 'D' || seqArray[j + 1].charAt(i) == 'B'
									|| seqArray[j + 1].charAt(i) == 'U' || seqArray[j + 1].charAt(i) == 'H')) {
						contLetraVer++;
						if (contLetraVer == 4) {
							contSeq++;
							contLetraVer = 1;
						}
					} else {
						sequencia.setValid(false);
						response.put("is_valid", false);
						return response;
					}
				} else {
					contLetraVer = 1;
				}

			}
			contLetraHor = 1;
			contLetraVer = 1;
			contLetraDiagDir = 1;

			if (contSeq >= 2) {
				sequencia.setValid(true);
				response.put("is_valid", true);
				return response;
			}
		}

		for (int i = seqArray.length - 1; i > -1; i--) {
			for (int j = 0; j < seqArray.length; j++) {
				if (j + 1 < seqArray[j].length() && (i - j) + 1 < seqArray[j].length() && i >= j
						&& seqArray[i - j].charAt(j + 1) == seqArray[(i - j) + 1].charAt(j)) {
					System.out.println("indice i: " + i);
					System.out.println("indice j: " + j);
					System.out.println(seqArray[i - j].charAt(j + 1) + " " + seqArray[(i - j) + 1].charAt(j));
					if ((seqArray[i - j].charAt(j + 1) == 'D' || seqArray[i - j].charAt(j + 1) == 'B'
							|| seqArray[i - j].charAt(j + 1) == 'U' || seqArray[i - j].charAt(j + 1) == 'H')
							&& (seqArray[(i - j) + 1].charAt(j) == 'D' || seqArray[(i - j) + 1].charAt(j) == 'B'
									|| seqArray[(i - j) + 1].charAt(j) == 'U' || seqArray[(i - j) + 1].charAt(j) == 'H')) {
						contLetraDiagEsq++;
						if (contLetraDiagEsq == 4) {
							contSeq++;
							contLetraDiagEsq = 1;
						}
					} else {
						sequencia.setValid(false);
						response.put("is_valid", false);
						return response;
					}
				} else {
					contLetraDiagEsq = 1;
				}

				if (contSeq >= 2) {
					sequencia.setValid(true);
					response.put("is_valid", true);
					return response;
				}
			}
		}

		if (contSeq >= 2) {
			sequencia.setValid(true);
			response.put("is_valid", true);
			return response;
		} else {
			sequencia.setValid(false);
			response.put("is_valid", false);
			return response;
		}
	}

	public void saveToDatabase(Sequencia sequencia) {
		sequenciaRepository.save(sequencia);
	}

	@SuppressWarnings("unchecked")
	public JSONObject getSequenciaInfo() {
		JSONObject info = new JSONObject();
		int countValid = 0;
		int countInvalid = 0;
		double ratio;
		int total;

		for (int i = 1; i <= sequenciaRepository.count(); i++) {
			Sequencia sequencia = sequenciaRepository.findById((long) i).get();
			if (sequencia.isValid()) {
				countValid++;
			} else {
				countInvalid++;
			}
		}

		total = countValid + countInvalid;

		ratio = (double) countValid / (double) total;

		info.put("count_valid", countValid);
		info.put("count_invalid", countInvalid);
		info.put("ratio", ratio);

		return info;
	}
}
