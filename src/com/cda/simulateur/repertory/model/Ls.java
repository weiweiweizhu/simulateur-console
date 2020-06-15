package com.cda.simulateur.repertory.model;

import java.io.File;

import com.cda.simulateur.menu.action.Command;
import com.cda.simulateur.tools.Output;

public class Ls extends Command {

	public final static Ls lsInstance = new Ls();

	private static Ls getLsInstance() {
		return lsInstance;
	}

	@Override
	public void executer() {
		File vFolder = new File(Pwd.getAdressCourante());
		File[] vFilesList = vFolder.listFiles();
		if (vFilesList != null) {
			for (File f : vFilesList) {
				if (f.isDirectory() && f.canWrite() && f.canRead() && f.canExecute()) {
					System.out.println("<DIR>   " + f.getName());
				} else if (f.isFile()) {
					System.out.println("<FILE>  " + f.getName());
				}
			}
		}
		Output.sautLigne();
	}

	@Override
	public void executer(String... pSaisie) {
		// TODO Auto-generated method stub
	}
}