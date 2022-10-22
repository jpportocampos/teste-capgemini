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
				if (seqArray[i].charAt(j) == seqArray[i].charAt(j + 1)) {
					contLetraHor++;
					if (contLetraHor == 4) {
						contSeq++;
						contLetraHor = 1;
					}
				} else {
					contLetraHor = 1;
				}

				if (seqArray[j].charAt(i) == seqArray[j + 1].charAt(i)) {
					contLetraVer++;
					if (contLetraVer == 4) {
						contSeq++;
						contLetraVer = 1;
					}
				} else {
					contLetraVer = 1;
				}

			}
			contLetraHor = 1;
			contLetraVer = 1;

			if (contSeq >= 2) {
				sequencia.setValid(true);
				response.put("is_valid", true);
				return response;
			}
		}

		for (int i = 0; i < seqArray.length; i++) {
			if (i + 1 < seqArray[i].length() && seqArray[i].charAt(i) == seqArray[i + 1].charAt(i + 1)) {
				contLetraDiagDir++;
				if (contLetraDiagDir == 4) {
					contSeq++;
					contLetraDiagDir = 1;
				}
			} else {
				contLetraDiagDir = 1;
			}

			if (contSeq >= 2) {
				sequencia.setValid(true);
				response.put("is_valid", true);
				return response;
			}
		}
		
		for (int i = seqArray.length - 1; i > 0; i--) {
			if (i - 1 < seqArray[i].length() && seqArray[i].charAt(i) == seqArray[i - 1].charAt(i - 1)) {
				contLetraDiagEsq++;
				if (contLetraDiagEsq == 4) {
					contSeq++;
					contLetraDiagEsq = 1;
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
				countValid ++;
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
