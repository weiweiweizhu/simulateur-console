package com.cda.simulateur.menu.action;

public class Exit extends Command {
	public static boolean exit = false;
	public static Exit exitInstance = new Exit();

	public Exit() {
		super();
	}

	@Override
	public void executer() {
		exit = true;
	}

	@Override
	public void executer(String... pSaisie) {
		executer();
	}
}
