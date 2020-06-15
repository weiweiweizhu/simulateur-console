package com.cda.simulateur.file.model.copy;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.cda.simulateur.menu.action.Command;
import com.cda.simulateur.repertory.model.Pwd;
import com.cda.simulateur.tools.Output;
import com.cda.simulateur.tools.Utils;

public class Fline extends Command {
	public static Fline flineInstance = new Fline();

	enum FlineOption {
		N, S, D, F
	}

	public Fline() {
		super();

	}

	@Override
	public void executer() {

	}

	class Option {
		String nom;
		String valeur;

		@Override
		public boolean equals(Object obj) {
			if (!(obj instanceof Option)) {
				return false;
			}
			return this.nom.equalsIgnoreCase(((Option) obj).nom);
		}
	}

	public void executer(String... pSaisie) {
		String[] vCmdArgs = pSaisie[0].toUpperCase().trim().split(" ");

		if (Utils.existFile(vCmdArgs[0])) {
			List<Option> vOptions = new ArrayList<Fline.Option>();

			for (int i = 1; i < vCmdArgs.length; i++) {
				Option vOption = new Option();
				if (vCmdArgs[i].length() != 2) {
					System.out.println("Erreur de syntaxe.");
					return;
				}
				if (vCmdArgs[i].charAt(0) != '-') {
					System.out.println("Erreur de syntaxe.");
					return;
				}
				vOption.nom = vCmdArgs[i].substring(1);
				if (FlineOption.valueOf(vOption.nom) == null) {
					System.out.println("Erreur de syntaxe.");
					return;
				}

				if (i < vCmdArgs.length - 1) {
					vOption.valeur = vCmdArgs[++i];
				}
				if (!vOptions.contains(vOption)) {
					vOptions.add(vOption);
				}
				if (vOption.nom.equalsIgnoreCase(FlineOption.N.name()) && vOptions.size() > 1) {
					System.out.println("L'option N s'utilise toute seule.");
					return;
				}
			}

			int fin = Integer.MAX_VALUE;
			int debut = -1;
			String recherche = null;
			boolean modeCompteur = false;

			for (Option vOption : vOptions) {
				if (vOption.nom.equalsIgnoreCase(FlineOption.D.name())) {
					if (vOption.valeur != null && vOption.valeur.matches("^[0-9]+$")) {
						debut = Integer.parseInt(vOption.valeur);
					} else {
						System.out.println("Erreur de syntaxe valeur non numérique pour l'option D.");
						return;
					}
				} else if (vOption.nom.equalsIgnoreCase(FlineOption.F.name())) {
					if (vOption.valeur != null && vOption.valeur.matches("^[0-9]+$")) {
						fin = Integer.parseInt(vOption.valeur);
					} else {
						System.out.println("Erreur de syntaxe valeur non numérique pour l'option F.");
						return;
					}
				} else if (vOption.nom.equalsIgnoreCase(FlineOption.S.name())) {
					recherche = vOption.valeur;

				} else if (vOption.nom.equalsIgnoreCase(FlineOption.N.name())) {
					modeCompteur = true;
				}
			}

			if (modeCompteur) {
				System.out.println(nbreLigne(vCmdArgs[0]) + " lignes.");
			} else {
				String uneLigne;
				try (BufferedReader br = new BufferedReader(
						new FileReader(Pwd.getAdressCourante() + "/" + vCmdArgs[0]))) {
					int nbreLigne = 1;
					while ((uneLigne = br.readLine()) != null) {
						if (nbreLigne >= debut && nbreLigne <= fin) {
							if (recherche != null && uneLigne.toUpperCase().contains(recherche) || recherche == null) {
								System.out.println(nbreLigne + ">" + uneLigne);
							}
						}
						nbreLigne++;
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} else {
			Output.fichierIntrouvable();
		}

	}

	private static int nbreLigne(String pFichier) {
		String str;
		int nbreLigne = 0;
		try (BufferedReader br = new BufferedReader(new FileReader(pFichier))) {
			while ((str = br.readLine()) != null) {
				nbreLigne++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return nbreLigne;
	}
}
