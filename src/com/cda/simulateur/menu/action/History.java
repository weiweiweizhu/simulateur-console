package com.cda.simulateur.menu.action;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import com.cda.simulateur.tools.Ihm;
import com.cda.simulateur.tools.Output;

public class History extends Command {

	public static final LinkedList<String> LISTCOMMAND = new LinkedList<String>();
	private static DateTimeFormatter vDateFormat = DateTimeFormatter.ofPattern("hh:mm:ss  dd-MM-yyyy");
	public static History HistoryInstance = new History();
	private static int cmp;

	private History() {

	}

	public static History getHistory() {
		return HistoryInstance;
	}

	public static LinkedList<String> getListcommand() {
		return LISTCOMMAND;
	}

	public static void ajouterCmd(String... pCmd) {

		if (!verifiList(pCmd)) {
			if (cmp < 10) {
				LISTCOMMAND.add(Ihm.commande + " " + LocalDateTime.now().format(vDateFormat));
				cmp++;
			} else {

				LISTCOMMAND.removeFirst();
				LISTCOMMAND.addLast(Ihm.commande + " " + LocalDateTime.now().format(vDateFormat));
			}
		}

	}

	private static boolean verifiList(String... pCmd) {
		String[] argSwitch = { "exit", "help", "history", "histclear", "pwd" };
		List<String> arrayArgs = Arrays.asList(argSwitch);
		String[] args = pCmd[0].split(" ");
		return arrayArgs.contains(pCmd[0]);
	}

	@Override
	public void executer() {
		for (String string : LISTCOMMAND) {
			System.out.println(string);
		}
	}

	@Override
	public void executer(String... pSaisie) {
		Output.syntaxeIncorrecte();
	}

	public int getCmp() {
		return cmp;
	}
}
