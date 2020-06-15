package com.cda.simulateur.menu.action;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import com.cda.simulateur.tools.Output;
import com.cda.simulateur.tools.Utils;

public class Help extends Command {
	private static final HashMap<String, String> LISTCMD = new HashMap<>();

	public static Help help = new Help();

	private Help() {
		super();
		LISTCMD.put("cat", "Affiche le contenu d'un fichier texte.");
		LISTCMD.put("cd", "Change le répertoire en cours.");
		LISTCMD.put("count",
				"Affiche le nombre de fichiers et dossiers\r\n" + "             dans le répertoire en cours.");
		LISTCMD.put("copy", "Copie un fichier.");
		LISTCMD.put("crd", "Crée un répertoire.");
		LISTCMD.put("crf", "Crée un fichier.");
		LISTCMD.put("dir", "Affiche la liste des fichiers et sous-répertoires\r\n" + "             d'un répertoire.");
		LISTCMD.put("dirng", "Affiche le contenu du repertoire en cours\r\n"
				+ "             et affiche le nombre de fichiers et répertoire.");
		LISTCMD.put("exit", "Quitte l'interpréteur de commandes.");
		LISTCMD.put("find", "Recherche une chaîne de caractères dans un ou plusieurs\r\n" + "             fichiers.");
		LISTCMD.put("fline", "Affiche le contenu d'un fichier.");
		LISTCMD.put("getvars",
				"Affiche les variables d'environnement\r\n" + "             et les propriétés de la jvm");
		LISTCMD.put("help", "Affiche des informations sur les commandes.");
		LISTCMD.put("histclear", "Efface l'historique de commandes.");
		LISTCMD.put("history", "Affiche l'historique des commandes.");
		LISTCMD.put("isprime", "Affiche si le nombre est premier.");
		LISTCMD.put("ls", "Affiche le contenu du répertoire en cours.");
		LISTCMD.put("now", "Affiche l'heure et la date.");
		LISTCMD.put("pwd", "Affiche l'adresse du répertoire en cours.");
		LISTCMD.put("river", "Affiche la première intersection des rivières.");
	}

	@Override
	public void executer(String... pSaisie) {
		String arg = Utils.stringCleaner(pSaisie);
		String[] args = arg.split(" ");
		if (LISTCMD.get(args[0]) == null) {
			Output.commandePasPriseEnCharge();
		} else {
			System.out.println(LISTCMD.get(args[0]) + "\n" + HelpEnum.valueOf(args[0].toUpperCase()).getDescription());
		}
	}

	@Override
	public void executer() {
		Output.introHelp();
		LinkedHashMap<String, String> collect = LISTCMD.entrySet().stream()
				.collect(Collectors.toMap(k -> (String) k.getKey(), e -> (String) e.getValue())).entrySet().stream()
				.sorted(Map.Entry.comparingByKey()).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
						(oldValue, newValue) -> oldValue, LinkedHashMap::new));

		collect.forEach((k, e) -> System.out.format("%-12s %s%n", k.toUpperCase(), e));
		Output.sautLigne();
	}
}
