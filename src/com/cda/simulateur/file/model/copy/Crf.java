package com.cda.simulateur.file.model.copy;

import java.io.File;
import java.io.IOException;
import java.util.Random;

import com.cda.simulateur.menu.action.Command;
import com.cda.simulateur.repertory.model.Pwd;
import com.cda.simulateur.tools.Output;
import com.cda.simulateur.tools.Utils;

/**
 * Classe <b>Crf</b> qui crée un fichier dans le répertoire en cours et dont le
 * nom est spécifié en paramètre.
 * 
 * @return ne retourne rien.
 * @param prend en paramètre un String récupéré depuis "Ihm.lancerMenu()". Ce
 *              paramètre sera le nom du fichier créé. Si le fichier est un
 *              texte et qu'il existe déjà, la date de création du fichier sera
 *              la date de création de ce fichier et le contenu de l'ancien
 *              fichier sera conservé. Aucun contenu ne sera écrasé.
 * @exception lève une exception si le nom du fichier n'est pas spécifié avec la
 *                 commande.
 */

public class Crf extends Command {
	public static Crf crfInstance = new Crf();

	private Crf() {
		super();
	}

	@Override
	public void executer(String... pArg) {
		Random r = new Random();
		String vNomFichierComplet = pArg[0];
		String vNomFichierTemp = String.valueOf(Math.abs(r.nextInt())) + ".txt";
		String vCheminFichierComplet = Pwd.getAdressCourante() + vNomFichierComplet;
		String vCheminFichierTemp = Pwd.getAdressCourante() + vNomFichierTemp;
		File fichierACreer = new File(vCheminFichierComplet);
		File fichierTemp = new File(vCheminFichierTemp);

		if (vNomFichierComplet == "") {
			Output.syntaxeIncorrecte();
		} else if (fichierACreer.exists()) {
			try {
				Utils.copieContenu(vCheminFichierComplet, vCheminFichierTemp);
				fichierACreer.delete();
				Utils.copieContenu(vCheminFichierTemp, vCheminFichierComplet);
				fichierTemp.delete();
			} catch (IOException e) {
				Output.creationFichierEchec();
			} finally {
				fichierTemp.delete();
			}
		} else {
			try {
				fichierACreer.createNewFile();
				Output.creationFichierReussie();
			} catch (IOException e) {
				Output.creationFichierEchec();
			}
		}
	}

	@Override
	public void executer() {
		executer("");
	}
}
