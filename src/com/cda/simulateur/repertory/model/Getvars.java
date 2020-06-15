package com.cda.simulateur.repertory.model;

import com.cda.simulateur.menu.action.Command;
import com.cda.simulateur.tools.Output;
import com.cda.simulateur.tools.Utils;

public class Getvars extends Command {
	public static Getvars getvarsInstance = new Getvars();

	private Getvars() {
		super();
	}

	@Override
	public void executer() {
		Utils.environnement();
		System.out.print("\n");
		Utils.jvm();
	}

	@Override
	public void executer(String... pSaisie) {
		String parametre = Utils.stringCleaner(pSaisie);
		String[] test = parametre.split(" ");

		if (test.length == 2) {
			if (test[0].equals("-env") && test[1].equals("-prop")) {
				executer();
			} else if (test[0].equals("-prop") && test[1].equals("-env")) {
				Utils.jvm();
				System.out.print("\n");
				Utils.environnement();
			} else if (test[0].equals("-env") && !(test[1].equals("-prop"))) {
				Utils.environnement();
				System.out.println("\n'" + test[1] + "'  n'est pas reconnu.\n");
			} else if (!(test[0].equals("-prop")) && test[1].equals("-env")) {
				Utils.environnement();
				System.out.println("\n'" + test[0] + "'  n'est pas reconnu.\n");
			} else if (test[0].equals("-prop") && !(test[1].equals("-env"))) {
				Utils.jvm();
				System.out.println("\n'" + test[1] + "' n'est pas reconnu.\n");
			} else if (!(test[0].equals("-env")) && test[1].equals("-prop")) {
				Utils.jvm();
				System.out.println("\n'" + test[0] + "'  n'est pas reconnu.\n");
			} else {
				Output.syntaxeIncorrecte();
			}
		} else if (test.length == 1) {
			if (test[0].equals("-prop")) {
				Utils.jvm();
			} else if (test[0].equals("-env")) {
				Utils.environnement();
			} else {
				Output.syntaxeIncorrecte();
			}
		}
	}
}
