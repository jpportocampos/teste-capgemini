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
		String[] seqArray = sequencia.getLetters();
		int contLetraHor = 1;
		int contLetraVer = 1;
		int contLetraDiagDir = 1;
		int contLetraDiagEsq = 1;
		int contSeq = 0;

		if (seqArray[0].length() < 4) {
			sequencia.setValid(false);
			return false;
		}

		for (int i = 0; i < seqArray.length - 1; i++) {
			for (int j = 0; j < seqArray[i].length() - 1; j++) {
				if (seqArray[i].charAt(j) == seqArray[i].charAt(j + 1)) {
					contLetraHor++;
					if (contLetraHor == 4) {
						contSeq++;
						contLetraHor = 1;
						System.out.println(contSeq + " horizontal");
					}
				} else {
					contLetraHor = 1;
				}

				if (seqArray[j].charAt(i) == seqArray[j + 1].charAt(i)) {
					contLetraVer++;
					if (contLetraVer == 4) {
						contSeq++;
						contLetraVer = 1;
						System.out.println(contSeq + " vertical");
					}
				} else {
					contLetraVer = 1;
				}
				
				if (j + 1 < seqArray.length - 1 && seqArray[i].charAt(j) == seqArray[i + 1].charAt(j + 1)) {
					contLetraDiagDir++;
					if (contLetraDiagDir == 4) {
						contSeq++;
						contLetraDiagDir = 1;
						System.out.println(contSeq + " diagonal direita");
					}
				} else {
					contLetraDiagDir = 1;
				}

			}
			contLetraHor = 1;
			contLetraVer = 1;
			contLetraDiagDir = 1;
			
			if (contSeq >= 2) {
				sequencia.setValid(true);
				return true;
			}
		}

		for (int i = seqArray.length; i > seqArray.length / 2; i--) {
			for (int j = 0; j < seqArray[i - 1].length() / 2; j++) {
				System.out.println(seqArray[i - 2].charAt(j + 1) + " " + seqArray[i - 1].charAt(j));
				if (seqArray[i - 2].charAt(j + 1) == seqArray[i - 1].charAt(j)) {
					contLetraDiagEsq++;
					if (contLetraDiagEsq == 4) {
						contSeq++;
						contLetraDiagEsq = 1;
					}
				} else {
					contLetraDiagEsq = 1;
				}
			}
			if (contSeq >= 2) {
				sequencia.setValid(true);
				return true;
			}
		}
		
		System.out.println(contSeq + " final");
		if (contSeq >= 2) {
			sequencia.setValid(true);
			return true;
		} else {
			sequencia.setValid(false);
			return false;
		}
	}

	public void saveToDatabase(Sequencia sequencia) {
		sequenciaRepository.save(sequencia);
	}
}
