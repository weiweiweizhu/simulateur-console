package com.cda.simulateur.menu.action;

public enum HelpEnum {
	CAT("\nCAT [lecteur:][chemin][nom_de_fichier]\r\n"),
	CD("\nCD [lecteur:][chemin]\r\n" + "CD [..]\r\n" + "CD [.]\r\n"
			+ "\n  ..   Signifie que vous voulez vous placer sur le répertoire parent.\r\n"
			+ "  .    Signifie que vous voulez afficher le répertoire en cours.\r\n"
			+ "\nEntrez CD lecteur: pour afficher le répertoire en cours sur le lecteur.\r\n"
			+ "Entrez CD sans paramètres pour afficher le lecteur et le répertoire en cours.\r\n"),
	COUNT("\nCOUNT \r\n" + "COUNT [-f]\r\n" + "COUNT [-d]\r\n"),
	COPY("\nCOPY [nom_de_fichier][nom_de_fichier_copié].\r\n"),
	CRD("\nCRD [nom_de_répertoire].\r\n" + "\nCréé un répertoire dont le nom est rentré en paramètre\r\n"
			+ "dans le répertoire en cours.\r\n"),
	CRF("\nCRF [nom_de_fichier].\r\n" + "\nCréé un fichier dont le nom est rentré en paramètre\r\n"
			+ "dans le répertoire en cours.\r\n"),
	DIR("\nDIR\r\n" + "DIR [lecteur:][chemin]\r\n"
			+ "\nAffiche la liste des fichiers et sous-répertoires du répertoire en cours."
			+ "\nAffiche la liste des fichiers et sous-répertoires du répertoire spécifié.\r\n"),
	DIRNG("\nDIRNG\r\n" + "DIRNG [lecteur:][chemin]\r\n"
			+ "\nAffiche la liste des fichiers et sous-répertoires du répertoire en cours\r\n"
			+ "et affiche le nombre de fichiers et/ou de répertoires du répertoire en cours\r\n"
			+ "ou du répertoire spécifié\r\n"),
	EXIT("\nEXIT\r\n" + "\nQuitte le programme cmd-simulateur.jar (interpréteur de commandes).\r\n"),
	FIND("\nFIND\r\n" + "FIND [-starts]\"chaîne\"\r\n" + "FIND [-ends]\"chaîne\"\r\n"
			+ "FIND [-starts]\"chaîne\"[-ends]\"chaîne\"\r\n"
			+ "\n  -starts[chaîne]   Signifie que vous recherchez un fichier dont le nom commence\r\n"
			+ "                    par la chaîne de caractères entrée en paramètre.\r\n"
			+ "  -ends[chaîne]     Signifie que vous recherchez un fichier dont le nom ou l'extension\r\n"
			+ "                    se termine par la chaîne de caractères entrée en paramètre\r\n"
			+ "                    Si les deux paramètres sont spécifiés, signifie que vous recherchez un fichier dont le nom commence\r\n"
			+ "                    et se termine par les chaînes de caractères entrées en paramètre.\r\n"),
	FLINE("\nFLINE [nom_de_fichier][-n]\r\n" + "FLINE [nom_de_fichier][-d] [-f] [-s]\r\n"
			+ "\n  -n  Affiche le nombre de lignes, ne peut pas être utilisée en même temps qu'une autre option.\r\n"
			+ "  -d  Permet de définir inclusivement le numéro de ligne à partir duquel le traitement va être fait.\r\n"
			+ "  -f  Permet de définir inclusivement le numéro de ligne jusqu'auquel le traitement va être fait.\r\n"
			+ "  -s  Permet de chercher une chaîne de caractères dans une ligne.\r\n"),
	GETVARS("\nGETVARS\r\n" + "GETVARS [-env]\r\n" + "GETVARS [-prop]\r\n" + "GETVARS [-env][-prop]\r\n"
			+ "\n         Sans paramètres affiche les variables d'environnement et les propriétes de la JVM.\r\n"
			+ "  -env   Affiche les variables d'environnement.\r\n" + "  -prop  Affiche les propriétés de la JVM.\r\n"),
	HELP("\nHELP\r\n" + "HELP [commande]\r\n"
			+ "\n  commande  Affiche des informations d'aide sur cette commande.\r\n"),
	HISTORY("\nHISTORY\r\n" + "\nAffiche un historique des 10 dernières commandes.\r\n"),
	HISTCLEAR("\nHISTCLEAR\r\n" + "\nEfface le contenu de l'historique des commandes.\r\n"),
	ISPRIME("\nISPRIME [nombre]\r\n"
			+ "\nAffiche \"yes\" si le nombre entré en paramètre est un nombre premier\r\nsinon affiche \"no\".\r\n"),
	LS("\nLS\r\n" + "\nAffiche les fichiers et les répertoires du répertoire en cours.\r\n"),
	NOW("\nNOM\r\n" + "NOW [-t] [-d] [-dt] [-td]\r\n" + "\n      Sans paramètres affiche l'heure et la date.\r\n"
			+ "  -t  Affiche l'heure.\r\n" + "  -d  Affiche la date.\r\n"),
	PWD("\nLS\r\n" + "\nAffiche l'adresse du répertoire en cours.\r\n"),
	RIVER("\nRIVER [nombre][nombre]\r\n" + "\nAprès addition à eux-même de chacun des chiffres qui composent\r\n"
			+ "les nombres entrés en paramètres, affiche leur nombre commun s'ils se croisent.\r\n");

	private String description;

	HelpEnum(String pDescription) {
		this.description = pDescription;
	}

	public String getDescription() {
		return description;
	}
}
