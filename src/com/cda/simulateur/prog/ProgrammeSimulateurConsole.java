package com.cda.simulateur.prog;

import java.io.File;

import com.cda.simulateur.tools.Ihm;

public class ProgrammeSimulateurConsole {

	public static void main(String[] args) {

		String proprieteLancement = System.getProperty("cdi.default.folder");

		if (proprieteLancement != null) {
			File repertoire = new File(proprieteLancement);
			if (repertoire.isDirectory()) {
				System.setProperty("user.dir", proprieteLancement);
			}
		}
		Ihm.lancerMenu();
	}
}
