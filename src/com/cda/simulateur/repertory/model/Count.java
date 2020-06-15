package com.cda.simulateur.repertory.model;

import java.io.File;

import com.cda.simulateur.menu.action.Command;
import com.cda.simulateur.tools.Output;
import com.cda.simulateur.tools.Utils;

public class Count extends Command {

	public static Count countInstance = new Count();
	private static int[] nombreFichier = countFichier();
	private static int counterFile;
	private static int counterFolder;

	// la sortie int[] de count =>
	// int[0] = nombre repertoire / int[1] = nombre fichier
	private static int[] countFichier() {
		int vNombreDossier = 0;
		int vNombreFichier = 0;
		int[] vArrayNombreElement = new int[2];

		File[] vFiles = new File(Pwd.getAdressCourante()).listFiles();

		for (File f : vFiles) {
			if (f.isDirectory()) {
				vNombreDossier++;
			} else {
				vNombreFichier++;
			}
		}
		vArrayNombreElement[0] = vNombreDossier;
		vArrayNombreElement[1] = vNombreFichier;
		return vArrayNombreElement;
	}

	public void optionP(String[] strs) {
		counterFolder = 0;
		counterFile = 0;

		if (strs.length < 2) {
			Output.syntaxeIncorrecte();
			return;
		}

		String regex = strs[1];
		File vFiles = new File(Pwd.getAdressCourante());

		matchFile(vFiles, regex);

		if (strs.length == 2) {
			afficheNombreFichier(counterFile);
			afficheNombreRepertoire(counterFolder);
			return;
		}

		if (strs.length > 2) {
			for (int i = 2; i < strs.length; i++) {
				strs[i] = Utils.removeDuplicateCharacter(strs[i]);
				if (strs[i].charAt(0) != '-') {
					Output.syntaxeIncorrecte();
				} else {
					for (int j = 1; j < strs[i].length(); j++) {
						switch (strs[i].charAt(j)) {
						case 'f':
							afficheNombreFichier(counterFile);
							break;
						case 'd':
							afficheNombreRepertoire(counterFolder);
							break;
						case 't':
							optionT();
							break;
						default:
							Output.syntaxeIncorrecte();
							break;
						}
					}
				}
			}
		}
	}

	public void optionT() {

	}

	public static void matchFile(File dir, String regex) {

		File[] subfiles = dir.listFiles();
		for (File f : subfiles) {
			if (f.isDirectory()) {
				if (f.getName().matches(regex)) {
					counterFolder++;
				}
				matchFile(f, regex);
			} else if (f.getName().matches(regex)) {
				counterFile++;
			}
		}
	}

	private void checkArgument(String pArgument) {
		String vNonValidArgument = "";
		pArgument = Utils.removeDuplicateCharacter(pArgument);

		for (int i = 0; i < pArgument.length() - 1; i++) {

			if (pArgument.charAt(0) != '-' || pArgument.length() > 3) {
				vNonValidArgument += pArgument.charAt(0);
			} else if (pArgument.charAt(i + 1) != 'd' && pArgument.charAt(i + 1) != 'f'
					&& pArgument.charAt(i + 1) != 'r') {
				vNonValidArgument += pArgument.charAt(i);
			}

		}

		if (vNonValidArgument.length() == 0) {
			for (int i = 1; i < pArgument.length(); i++) {
				switch (pArgument.charAt(i)) {

				case '-':
					executer();
					break;

				case 'f':
					afficheNombreFichier(nombreFichier[1]);
					break;

				case 'd':
					afficheNombreRepertoire(nombreFichier[0]);
					break;

				case 'r':
					recursivePath(new File(Pwd.getAdressCourante()));
					System.out.println();

				default:
					Output.syntaxeIncorrecte();
					break;

				}
			}

		} else {
			Output.syntaxeIncorrecte();
		}
	}

	private int recupereNombreRepertoire(String pUrl) {
		File vFile = new File(pUrl);
		File[] vFileList = vFile.listFiles();
		int count = 0;
		for (File f : vFileList) {
			if (f.isDirectory()) {
				count++;
			}
		}
		return count;
	}

	private int recupereNombreFichier(String pUrl) {
		File vFile = new File(pUrl);
		File[] vFileList = vFile.listFiles();
		int count = 0;
		for (File f : vFileList) {
			if (f.isFile()) {
				count++;
			}
		}
		return count;
	}

	private void afficheNombreRepertoire(int pNombreElement) {
		System.out.println(pNombreElement + " dossier(s).");
	}

	private void afficheNombreFichier(int pNombreElement) {
		System.out.println(pNombreElement + " fichier(s).");
	}

	private void recursivePath(File pPath) {
		File[] listDossier = pPath.listFiles();

		if (listDossier != null) {
			for (File f : listDossier) {
				if (f.isDirectory()) {
					recursivePath(f.getAbsoluteFile());
					System.out.println("<DIR>  " + f.getAbsolutePath());
					System.out.println("Répertoire(s) : " + recupereNombreRepertoire(f.getAbsolutePath()));
					System.out.println("Fichier(s)    : " + recupereNombreFichier(f.getAbsolutePath()));
				}
			}
		}
	}

	@Override
	public void executer() {
		nombreFichier = countFichier();
		afficheNombreRepertoire(nombreFichier[0]);
		afficheNombreFichier(nombreFichier[1]);
	}

	@Override
	public void executer(String... pSaisie) {
		String arg = Utils.stringCleaner(pSaisie);

		nombreFichier = countFichier();
		String[] vArguments = arg.split(" ");

		if ("-p".equals(vArguments[0])) {
			optionP(vArguments);
			return;
		}

		for (String s : vArguments) {
			checkArgument(s);
		}
	}
}
