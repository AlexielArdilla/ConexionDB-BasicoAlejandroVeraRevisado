package ar.com.unpaz.vista;

import javax.swing.JOptionPane;


public class PopUpGenerico {
	

	public void mostrarPopUp(String frase) {

		JOptionPane.showMessageDialog(null, frase);

	}


public static void main(String[] args) {
	

	PopUpGenerico p = new PopUpGenerico();
	
	p.mostrarPopUp("Perrooo!!!");
	
}}