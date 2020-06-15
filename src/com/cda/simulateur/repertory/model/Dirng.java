package com.cda.simulateur.repertory.model;

import com.cda.simulateur.menu.action.Command;

public class Dirng extends Command {

	public final static Dirng dirngInstance = new Dirng();

	Dirng() {
		super();
	}

	@Override
	public void executer() {
		Dir.dirInstance.executer();
		System.out.println("Nombre de dossier(s) = " + Dir.dirInstance.vNombreDossier);
		System.out.println("Nombre de fichier(s) = " + Dir.dirInstance.vNombreFichier);
	}

	@Override
	public void executer(String... pSaisie) {
		Dir.dirInstance.executer(pSaisie);
		System.out.println("Nombre de dossier(s) = " + Dir.dirInstance.vNombreDossier);
		System.out.println("Nombre de fichier(s) = " + Dir.dirInstance.vNombreFichier);
	}
}
