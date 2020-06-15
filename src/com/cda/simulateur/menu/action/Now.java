package com.cda.simulateur.menu.action;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import com.cda.simulateur.tools.Output;
import com.cda.simulateur.tools.Utils;

public class Now extends Command {
	private static DateTimeFormatter vDateFormatHeure = DateTimeFormatter.ofPattern("hh:mm:ss");
	private static DateTimeFormatter vDateFormatDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	public static Now nowInstance = new Now();

	private Now() {
		super();
	}

	@Override
	public void executer() {
		System.out.println(
				LocalDateTime.now().format(vDateFormatDate) + " " + LocalDateTime.now().format(vDateFormatHeure));
		Output.sautLigne();
	}

	@Override
	public void executer(String... pSaisie) {

		if (pSaisie[0].startsWith("-")) {
			String encours = "";
			String test = pSaisie[0];
			Character[] argSwitch = { 'd', 't', '-' };
			List<Character> arrayArgs = Arrays.asList(argSwitch);
			test = Utils.removeDuplicateCharacter(test).replace("-", "");
			test = test.replace(" ", "");
			for (int i = 0; i < test.length(); i++) {
				encours += arrayArgs.contains(test.charAt(i)) ? "" : test.charAt(i);
			}
			if (test.equals("")) {
				executer();
			}
			if (encours.equals("")) {
				for (int i = 0; i < test.length(); i++) {
					char charEncour = test.charAt(i);
					switch (arrayArgs.indexOf(charEncour)) {
					case 0:
						System.out.print(LocalDateTime.now().format(vDateFormatDate) + " ");
						break;
					case 1:
						System.out.print(LocalDateTime.now().format(vDateFormatHeure) + " ");
						break;
					default:
						break;
					}
				}
				Output.sautLigne();
			} else {
				Output.syntaxeIncorrecte();
			}
		} else {
			Output.syntaxeIncorrecte();
		}
	}
}