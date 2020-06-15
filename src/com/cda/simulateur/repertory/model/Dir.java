package com.cda.simulateur.repertory.model;

import java.io.File;

import com.cda.simulateur.menu.action.Command;
import com.cda.simulateur.tools.Output;
import com.cda.simulateur.tools.Utils;

public class Dir extends Command {

	public final static Dir dirInstance = new Dir();
	protected int vNombreDossier;
	protected int vNombreFichier;

	Dir() {
		super();
	}

	private void afficheDossier(File pFile) {
		if (pFile.isDirectory()) {
			this.vNombreDossier++;
			System.out.println("<DIR> " + pFile.getName());
		} else {
			this.vNombreFichier++;
			System.out.println("      " + pFile.getName());
		}
	}

	public int getvNombreDossier() {
		return vNombreDossier;
	}

	public int getvNombreFichier() {
		return vNombreFichier;
	}

	@Override
	public void executer() {
		String vUrl = Pwd.getAdressCourante();
		vNombreDossier = 0;
		vNombreFichier = 0;
		File[] vFiles = new File(vUrl).listFiles();

		if (vFiles != null) {
			for (File f : vFiles) {
				afficheDossier(f);
			}
		}
	}

	@Override
	public void executer(String... pSaisie) {
		String vUrl = Utils.stringCleaner(pSaisie[0]);
		vNombreDossier = 0;
		vNombreFichier = 0;
		if (vUrl != null) {
			if (Utils.existAbsolute(vUrl) && vUrl.contains(":")) {
				File[] vFiles = new File(vUrl + "/").listFiles();
				if (vFiles != null) {
					for (File f : vFiles) {
						afficheDossier(f);
					}
				} else {
					Output.repertoireIntrouvable();
				}
			} else if (Utils.existInRepertory(vUrl)) {
				File[] vFiles = new File(Pwd.getAdressCourante() + vUrl).listFiles();
				if (vFiles != null) {
					for (File f : vFiles) {
						afficheDossier(f);
					}
				}
			} else {
				Output.repertoireIntrouvable();
			}
		} else {
			Output.repertoireIntrouvable();
		}
	}
}
