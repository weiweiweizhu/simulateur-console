package com.cda.simulateur.repertory.model;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;

import com.cda.simulateur.menu.action.Command;
import com.cda.simulateur.tools.Output;
import com.cda.simulateur.tools.Utils;

public class Cat extends Command {
	public final static Cat catInstance = new Cat();

	public Cat() {
		super();
	}

	@Override
	public void executer() {

	}

	@Override
	public void executer(String... pSaisie) {
		String result = Utils.stringCleaner(pSaisie);
		String[] test = result.split(" ");
		String strTempB = test[0];
		InputStreamReader flog = null;
		LineNumberReader llog = null;
		String myLine = null;

		try {
			flog = new InputStreamReader(new FileInputStream(Pwd.getAdressCourante() + strTempB));
			llog = new LineNumberReader(flog);
			while ((myLine = llog.readLine()) != null) {
				System.out.println(myLine);
			}
		} catch (Exception e) {
			Output.fichierIntrouvable();
		}
	}
}
