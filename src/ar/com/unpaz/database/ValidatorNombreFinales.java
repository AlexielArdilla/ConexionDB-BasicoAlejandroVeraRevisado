package ar.com.unpaz.database;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.StringTokenizer;

import ar.com.unpaz.vista.PopUpGenerico;

public class ValidatorNombreFinales {

	private int id_alumno;
	private int id_materia;
	BigDecimal nota;

	public ValidatorNombreFinales(int id_alumno){
		
		this.id_alumno = id_alumno;
		
	}
	
	public LinkedList<String> separaEnTokens(String comando) {// ************Bien!!

		LinkedList<String> resultado = new LinkedList<>();

		StringTokenizer miSt = new StringTokenizer(comando, " ,[];<>()");

		while (miSt.hasMoreTokens()) {

			resultado.addLast(miSt.nextToken());

		}

		return resultado;
	}

	public boolean agregaFinal(String input) {

		boolean result = false;

		PopUpGenerico miPopUpGenerico = new PopUpGenerico();

		FinalNotaDAO mi_dao = new FinalNotaDAO();

		LinkedList<String> mis_tokens = separaEnTokens(input);

		if (mis_tokens.size() != 2) {//*****

			miPopUpGenerico.mostrarPopUp("No ingresó valores correctos");
			return false;

		}

		try {
			id_materia = Integer.parseInt(mis_tokens.get(0));

		} catch (NumberFormatException e) {

			miPopUpGenerico.mostrarPopUp("Ingresó un valor incorrecto");

			e.printStackTrace();
		}

	
		try {

			if (Integer.parseInt(mis_tokens.get(1)) > 10) {

				miPopUpGenerico.mostrarPopUp("La nota no puede ser mayor a 10");

				return false;
			}

			if (Integer.parseInt(mis_tokens.get(1)) < 1) {

				miPopUpGenerico.mostrarPopUp("La nota no puede ser menor a 1");

				return false;
			}

			nota = new BigDecimal(mis_tokens.get(1));

		} catch (Exception e) {

			miPopUpGenerico.mostrarPopUp("Ingresó un valor incorrecto");
			e.printStackTrace();
		}

		mi_dao.agregarFinal(id_alumno, id_materia, nota);

		return result;
	}

	public static void main(String[] args) {

		ValidatorNombreFinales mv = new ValidatorNombreFinales(1);

		mv.agregaFinal("2,10");

	}

}
