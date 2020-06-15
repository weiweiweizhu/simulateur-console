package com.cda.simulateur.tools;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import com.cda.simulateur.repertory.model.Pwd;

public class Utils {
	public static void executer() {
		File curDir = new File(Pwd.getAdressCourante());
		File[] filesList = curDir.listFiles();
		for (File f : filesList) {
			System.out.println(f.getName());
		}
		Output.sautLigne();
	}

	public static String stringCleaner(String[] pArg) {
		String commande = pArg[0];
		String regex = "\\s+";
		String result = commande.replaceAll(regex, " ").trim();
		return result;
	}

	public static String stringCleaner(String pArg) {
		String commande = pArg;
		String regex = "\\s+";
		String result = commande.replaceAll(regex, " ").trim();
		return result;
	}

	public static void copieContenu(String pIn, String pOut) throws IOException {
		int c;
		FileReader in = new FileReader(pIn);
		FileWriter out = new FileWriter(pOut);
		while ((c = in.read()) != -1) {
			out.write(c);
		}
		in.close();
		out.close();
	}

	public static boolean verifSaisieNombre(String pSaisie) {
		Pattern p = Pattern.compile("\\D");
		Matcher m = p.matcher(pSaisie);
		if (m.find()) {
			return true;
		} else {
			return false;
		}
	}

	public static void environnement() {
		Map<String, String> properties = System.getenv();

		LinkedHashMap<String, String> collect = properties.entrySet().stream()
				.collect(Collectors.toMap(k -> k.getKey(), e -> e.getValue())).entrySet().stream()
				.sorted(Map.Entry.comparingByKey()).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
						(oldValue, newValue) -> oldValue, LinkedHashMap::new));

		collect.forEach((k, e) -> System.out.format("%-32s = %s%n", k, e));
	}

	public static void jvm() {
		Properties properties = System.getProperties();

		LinkedHashMap<String, String> collect = properties.entrySet().stream()
				.collect(Collectors.toMap(k -> (String) k.getKey(), e -> (String) e.getValue())).entrySet().stream()
				.sorted(Map.Entry.comparingByKey()).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
						(oldValue, newValue) -> oldValue, LinkedHashMap::new));

		collect.forEach((k, e) -> System.out.format("%-32s = %s%n", k, e));
	}

	public static String removeDuplicateCharacter(String pSaisie) {
		char[] chars = pSaisie.toCharArray();
		Set<Character> charSet = new LinkedHashSet<Character>();
		for (char c : chars) {
			charSet.add(c);
		}

		StringBuilder sb = new StringBuilder();
		for (Character character : charSet) {
			sb.append(character);
		}
		return sb.toString();
	}

	// verifie le chemin absolue
	public static boolean existAbsolute(String pSaisie) {
		File vFile = new File(pSaisie);
		if (vFile.isDirectory()) {
			return true;
		}
		return false;
	}

	// verifie si le document existe dans le repertoire courant
	public static boolean existInRepertory(String pSaisie) {
		String vTempChemin = Pwd.getAdressCourante() + pSaisie;
		File file = new File(vTempChemin);
		if (file.isDirectory()) {
			return true;
		}
		return false;
	}

	public static boolean existFile(String pSaisie) {
		String vTempChemin = Pwd.getAdressCourante() + pSaisie;
		File file = new File(vTempChemin);
		if (file.exists()) {
			return true;
		}
		return false;

	}

}