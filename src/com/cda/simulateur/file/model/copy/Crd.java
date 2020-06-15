package com.cda.simulateur.file.model.copy;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.cda.simulateur.menu.action.Command;
import com.cda.simulateur.repertory.model.Pwd;
import com.cda.simulateur.tools.Output;

/**
 * Classe <b>Crd</b> qui crée le répertoire dans le répertoire en cours, dont le
 * nom est passé en paramètre.
 * 
 * @return ne retourne rien.
 * @param prend en paramètre un String qui représente le nom du répertoire crée.
 * @exception lève une exception si le nom du répertoire est vide.
 * @exception lève une exception si le nom du répertoire existe déjà.
 */

public class Crd extends Command {
	public static Crd crdInstance = new Crd();

	private Crd() {
		super();
	}

	@Override
	public void executer(String... pArg) {
		String vNomFichierComplet = pArg[0];
		String vCheminFichierComplet = Pwd.getAdressCourante() + vNomFichierComplet;
		Path path = Paths.get(vCheminFichierComplet);

		if (vNomFichierComplet == "") {
			Output.syntaxeIncorrecte();
			;
		} else if (!Files.exists(path)) {
			try {
				Files.createDirectories(path);
			} catch (IOException e) {
				Output.creationRepertoireEchec();
			}
		} else if (Files.exists(path)) {
			Output.fichierExisteDeja(vNomFichierComplet);
		}
	}

	@Override
	public void executer() {
		executer("");
	}
}
