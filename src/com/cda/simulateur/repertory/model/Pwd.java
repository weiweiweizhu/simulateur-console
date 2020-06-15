package com.cda.simulateur.repertory.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.cda.simulateur.menu.action.Command;

public final class Pwd extends Command {
	private static final String vCheminEnCours = System.getProperty("user.dir").toString().replace("\\", "/");
	protected static List<String> vCheminArray = new ArrayList<>(Arrays.asList(vCheminEnCours.split("/")));
	public static String vAdressCourante = vCheminEnCours;
	public final static Pwd vPwdInstance = new Pwd();

	private Pwd() {
		super();
	}

	public static List<String> getvCheminArray() {
		return vCheminArray;
	}

	// retourne le chemin du repertoire courant
	public static Pwd getPwdinstance() {
		return vPwdInstance;
	}

	@Override
	public void executer() {
		Pwd.setAdressCourante(Pwd.getAdressCourante());
	}

	public static String getAdressCourante() {
		StringBuilder sb = new StringBuilder();
		for (String s : vCheminArray) {
			sb.append(s);
			sb.append("/");
		}
		return sb.toString();
	}

	public static void setAdressCourante(String pAdressCourante) {
		Pwd.vAdressCourante = pAdressCourante;
	}

	@Override
	public void executer(String... pSaisie) {
	}
}