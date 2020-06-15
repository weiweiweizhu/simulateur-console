package com.cda.simulateur.repertory.model;

import java.io.File;

import com.cda.simulateur.menu.action.Command;
import com.cda.simulateur.tools.Output;
import com.cda.simulateur.tools.Utils;

public class Find extends Command {
	public static Find findInstance = new Find();
	private static int counter = 0;

	private Find() {
	}

	@Override
	public void executer() {
		Output.nombreParamètres();
	}

	@Override
	public void executer(String... pSaisie) {
		boolean verif = false;
		counter = 0;
		String arg = Utils.stringCleaner(pSaisie);
		String[] args = arg.split(" ");
		Pwd.setAdressCourante(Pwd.getAdressCourante());
		File srcFile = new File(Pwd.vAdressCourante);

		if (args.length > 4) {
			Output.nombreParamètres();
			return;
		}
		if (args.length > 2) {
			if ("-starts".equals(args[0]) && "-ends".equals(args[2])) {
				findFileStartsEnds(srcFile, args[1], args[3]);
				String print = (counter <= 1) ? "fichier trouvé\n" : "fichers trouvés\n";
				System.out.println(counter + " " + print);
				return;
			}
		}
		if ("-ends".equals(args[0]) && args.length > 1) {
			findFileEndsWith(srcFile, args[1]);
			String print = (counter <= 1) ? "fichier trouvé\n" : "fichers trouvés\n";
			System.out.println(counter + " " + print);
			return;
		}
		if ("-starts".equals(args[0]) && args.length > 1) {
			findFileStartsWith(srcFile, args[1]);
			String print = (counter <= 1) ? "fichier trouvé\n" : "fichers trouvés\n";
			System.out.println(counter + " " + print);
			return;
		}

		findFile(srcFile, args[0]);
		String print = (counter <= 1) ? "fichier trouvé\n" : "fichers trouvés\n";
		System.out.println(counter + " " + print);
		if (counter == 0) {
			Output.syntaxeIncorrecte();

		}

	}

	public static void findFileEndsWith(File dir, String str) {
		File[] subfiles = dir.listFiles();
		for (File f : subfiles) {
			if (f.isDirectory()) {
				findFileEndsWith(f, str);
			} else if (f.getName().endsWith(str)) {
				counter++;
				System.out.println(f.getAbsolutePath());
			}
		}
	}

	public static void findFileStartsWith(File dir, String str) {
		File[] subfiles = dir.listFiles();
		for (File f : subfiles) {
			if (f.isDirectory()) {
				findFileStartsWith(f, str);
			} else if (f.getName().startsWith(str)) {
				counter++;
				System.out.println(f.getAbsolutePath());
			}
		}
	}

	public static void findFileStartsEnds(File dir, String p1, String p2) {
		File[] subfiles = dir.listFiles();
		for (File f : subfiles) {
			if (f.isDirectory()) {
				findFileStartsEnds(f, p1, p2);
			} else if (f.getName().startsWith(p1) && f.getName().endsWith(p2)) {
				counter++;
				System.out.println(f.getAbsolutePath());
			}
		}
	}

	public static void findFile(File dir, String p1) {
		File[] subfiles = dir.listFiles();
		for (File f : subfiles) {
			if (f.isDirectory()) {
				findFile(f, p1);
			} else if (f.getName().contains(p1)) {
				counter++;
				System.out.println(f.getAbsolutePath());
			}
		}
	}

}
