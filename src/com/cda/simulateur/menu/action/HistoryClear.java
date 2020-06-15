package com.cda.simulateur.menu.action;

public class HistoryClear extends Command {
	public static HistoryClear HistoryClearInstance = new HistoryClear();

	HistoryClear() {
		super();
	}

	@Override
	public void executer() {
		History.LISTCOMMAND.clear();
	}

	@Override
	public void executer(String... pSaisie) {
		History.LISTCOMMAND.clear();
	}

}
