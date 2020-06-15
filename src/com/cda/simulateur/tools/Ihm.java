package com.cda.simulateur.tools;

import java.util.HashMap;
import java.util.Scanner;

import com.cda.simulateur.file.model.copy.Copy;
import com.cda.simulateur.file.model.copy.Crd;
import com.cda.simulateur.file.model.copy.Crf;
import com.cda.simulateur.file.model.copy.Fline;
import com.cda.simulateur.menu.action.Command;
import com.cda.simulateur.menu.action.Exit;
import com.cda.simulateur.menu.action.Help;
import com.cda.simulateur.menu.action.History;
import com.cda.simulateur.menu.action.HistoryClear;
import com.cda.simulateur.menu.action.Now;
import com.cda.simulateur.minijeux.IsPrime;
import com.cda.simulateur.minijeux.River;
import com.cda.simulateur.minijeux.Secret;
import com.cda.simulateur.repertory.model.Cat;
import com.cda.simulateur.repertory.model.Cd;
import com.cda.simulateur.repertory.model.Count;
import com.cda.simulateur.repertory.model.Dir;
import com.cda.simulateur.repertory.model.Dirng;
import com.cda.simulateur.repertory.model.Find;
import com.cda.simulateur.repertory.model.Getvars;
import com.cda.simulateur.repertory.model.Ls;
import com.cda.simulateur.repertory.model.Pwd;

public class Ihm {
	public static HashMap<String, Command> listCmd = new HashMap<>();
	private final static Ihm ihmInstance = new Ihm();
	public static String commande;

	private Ihm() {
		listCmd.put("exit", Exit.exitInstance);
		listCmd.put("quit", Exit.exitInstance);
		listCmd.put("help", Help.help);
		listCmd.put("history", History.HistoryInstance);
		listCmd.put("pwd", Pwd.vPwdInstance);
		listCmd.put("histclear", HistoryClear.HistoryClearInstance);
		listCmd.put("cd", Cd.cdInstance);
		listCmd.put("river", River.isRiverInstance);
		listCmd.put("isprime", IsPrime.isPrimeInstance);
		listCmd.put("ls", Ls.lsInstance);
		listCmd.put("dir", Dir.dirInstance);
		listCmd.put("dirng", Dirng.dirngInstance);
		listCmd.put("copy", Copy.copyInstance);
		listCmd.put("cat", Cat.catInstance);
		listCmd.put("find", Find.findInstance);
		listCmd.put("crf", Crf.crfInstance);
		listCmd.put("crd", Crd.crdInstance);
		listCmd.put("count", Count.countInstance);
		listCmd.put("fline", Fline.flineInstance);
		listCmd.put("getvars", Getvars.getvarsInstance);
		listCmd.put("now", Now.nowInstance);
		listCmd.put("cda", Secret.secretInstance);
	}

	public static HashMap<String, Command> getListCmd() {
		return listCmd;
	}

	public static void setListCmd(HashMap<String, Command> listCmd) {
		Ihm.listCmd = listCmd;
	}

	public static Ihm getIhmInstance() {
		return ihmInstance;
	}

	public static Command getAllCommand(String pCmd) {
		return listCmd.get(pCmd);
	}

	public static void lancerMenu() {
		Scanner sc = new Scanner(System.in);
		String cmd;

		do {
			Output.adresseCourante(Pwd.getAdressCourante());

			cmd = sc.nextLine().toLowerCase().trim();
			commande = cmd;

			if (cmd.startsWith("cd.")) {
				String commande = cmd.substring(0, 2);
				String argument = cmd.substring(2);
				Ihm.getAllCommand(commande).executer(argument);
				History.ajouterCmd(commande);
			} else if (cmd.indexOf(" ") == -1) {
				if (verifierCmd(cmd)) {
					Ihm.getAllCommand(cmd).executer();
					History.ajouterCmd(cmd);
				}
			} else {
				String commande = cmd.substring(0, cmd.indexOf(" "));
				String arguments = cmd.substring(cmd.indexOf(" ") + 1).trim();
				if (verifierCmd(commande)) {
					Ihm.getAllCommand(commande).executer(arguments);
					History.ajouterCmd(commande);
				}
			}
		} while (!Exit.exit);
		sc.close();
	}

	private static boolean verifierCmd(String pCmd) {
		if (listCmd.containsKey(pCmd)) {
			return true;
		} else {
			if (pCmd.equals("")) {
				return false;
			} else {
				System.out.println("'" + pCmd + "' n'est pas reconnu en tant que commande interne\r\n"
						+ "ou externe, un programme exécutable ou un fichier de commandes.\r\n");
				return false;
			}
		}
	}
}
