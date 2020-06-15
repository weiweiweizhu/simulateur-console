package com.cda.simulateur.minijeux;

import com.cda.simulateur.menu.action.Command;
import com.cda.simulateur.tools.Output;
import com.cda.simulateur.tools.Utils;

/**
 * Classe <b>River</b> qui teste à quel moment deux nombres entrés en paramètre
 * se rejoignent ou pas, en additionant à eux-mêmes, chacun de leurs chiffres
 * entre eux.
 * 
 * @return ne retourne rien.
 * @param prend en paramètre un String. Ce paramètre est splité puis chacun des
 *              deux Strings numériques sont convertis en Int.
 * @exception lève une exception si le var-args passé en argument est vide ou
 *                 n'est pas de type numérique.
 *
 */

public class River extends Command {
	public static River isRiverInstance = new River();

	private River() {
		super();
	}

	@Override
	public void executer(String... pArg) {
		String resultat = "";
		if (pArg[0].equals("")) {
			Output.nombreParamètres();
		} else {
			String result = Utils.stringCleaner(pArg);
			String[] test = result.split(" ");

			if (test.length != 2) {
				System.out.println(
						test.length < 2 ? "Nombre de paramètre incorrect.\n" : "Nombre de paramètres incorrects.\n");
			} else {
				String strTempA = test[0];
				String strTempB = test[1];
				String messErreur = "";
				String messErreurB = "Le nombre doit être compris entre 0 et " + Integer.MAX_VALUE + ".\n";
				int intTempA = 0;
				int intTempB = 0;
				int compteur = 0;
				boolean river = true;
				boolean erreur = false;
				boolean erreurB = false;

				try {
					Utils.verifSaisieNombre(strTempA);
					intTempA = Integer.parseInt(strTempA);
				} catch (NumberFormatException nfe) {
					if (Utils.verifSaisieNombre(strTempA)) {
						erreur = true;
						messErreur = strTempA + " n'est pas un entier.\n";
					} else {
						erreurB = true;
					}
					river = false;
				}

				try {
					Utils.verifSaisieNombre(strTempB);
					intTempB = Integer.parseInt(strTempB);
				} catch (NumberFormatException nfe) {
					if (Utils.verifSaisieNombre(strTempB)) {
						erreur = true;
						messErreur = messErreur.isEmpty() ? strTempB + " n'est pas un entier.\n"
								: strTempA + " et " + strTempB + " ne sont pas des entiers.\n";
					} else {
						erreurB = true;
					}
					river = false;
				}

				if (erreur) {
					System.out.println(messErreur);
				}

				if (erreurB) {
					System.out.println(messErreurB);
				}

				while (intTempA != intTempB && river) {
					if (intTempA > intTempB) {
						for (char i : strTempB.toCharArray()) {
							intTempB += Character.getNumericValue(i);
						}
					} else {
						for (char i : strTempA.toCharArray()) {
							intTempA += Character.getNumericValue(i);
						}
					}
					strTempA = String.valueOf(intTempA);
					strTempB = String.valueOf(intTempB);
					compteur++;
					if (compteur == 5000000) {
						System.out.println("Délai expiré.\n");
						return;
					}

					if (intTempA == intTempB) {
						river = false;
						System.out.println(strTempA + "\n");
					}
				}
			}
		}
	}

	@Override
	public void executer() {
		executer("");
	}

}