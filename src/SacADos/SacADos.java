package SacADos;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import Solutions.Solution;
import Solutions.SolutionDynamique;
import Solutions.SolutionHeuristique;
import Solutions.SolutionPSE;


/**
 * @author Yanis et Audran
 * Classe permettant la création d'un sac à dos.
 * Ce sac possède un poids et une capacité maximale.
 */
public class SacADos {
	private ArrayList<Objet> Sac;
	private ArrayList<Objet> Objets;
	private float poids;
	private float valeurTotale;
	private float capacité;

	
	/**
	 * Constructeur d'un Sac à dos vide
	 */
	public SacADos() {
		this.Objets = new ArrayList<Objet>();
		this.Sac = new ArrayList<Objet>();
		this.poids = 0;
	}
	
	/**
	 * Constructeur d'un sac à dos et initialisation de la liste 
	 * d'objet à partir d'un fichier .txt
	 * 
	 * @param chemin le chemin du fichier .txt
	 * @param capacitéMAX la capacité maximale du sac à dos
	 * @throws FileNotFoundException si le fichier spécifié n'existe 
	 * pas dans le répertoire
	 * 			
	 */
	public SacADos(String chemin, float capacitéMAX) throws FileNotFoundException {
		this();
		this.capacité = capacitéMAX;
		try {
			Scanner read = new Scanner(new File(chemin));
			read.useDelimiter(";|\n");
			while (read.hasNextLine() && read.hasNext()) {
				Objets.add(
						new Objet(read.next().strip(), Float.parseFloat(read.next()),
								Float.parseFloat(read.next())));
			}

			read.close();
		}

		catch (FileNotFoundException e) {
			throw e;
		}
	}
	
	/**
	 * @return le sac
	 */
	public ArrayList<Objet> getSac() {
		return Sac;
	}
	
	/**
	 * @return le prix total du sac
	 */
	public float getValeurTotale() {
		float p = 0;
		for(Objet o: Sac) {
			p+= o.getPrix();
		}
		return p;
		
	}
	
	/**
	 * @return le poids total du sac
	 */
	public float getPoids() {
		float p = 0;
		for(Objet o: Sac) {
			p+= o.getPoids();
		}
		return p;
		
	}
	
	
	
	/**
	 * Méthode permettant de remplir le sac, suivant la méthode
	 * choisie par l'utilisateur
	 * 
	 * @param méthode la méthode de résolution du problème
	 * @return false dans le cas où l'entrée utilisateur est fausse
	 * (faute ou méthode inexistante) sinon return true.
	 */
	public boolean remplir(String méthode) {
		Sac.clear();
		Solution solution;
		switch(méthode.toUpperCase()) {
			case "GLOUTONNE":
			case "HEURISTIQUE":
				solution = new SolutionHeuristique(Objets,capacité);
				Sac = solution.résoudre();
				return true;
			case "PROG DYNAMIQUE":
			case "DYNAMIQUE":
				solution = new SolutionDynamique(Objets,capacité);
				Sac = solution.résoudre();
				return true;
			case "PSE":
				solution = new SolutionPSE(Objets,capacité);
				Sac = solution.résoudre();
				return true;
			default:
				return false;
		}
	}
	
	/**
	 * Méthode permettant d'obtenir des informations sur le 
	 * sac à dos sous la forme d'une chaîne de caractères
	 * 
	 * @return une chaîne de caractères contenant des informations
	 * sur le sac à dos et une liste des objets qui y sont placés
	 */
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("\nLa capacité maximale du sac est de " + capacité + " kg\n");
		str.append("Les objets mis dans le sac sont les suivants :\n");
		for(Objet o: Sac) {
			str.append("\t - " + o.toString() + "\n");
			poids+= o.getPoids();
			valeurTotale+= o.getPrix();
		}
		str.append("\nLe poids total du sac est de " + poids + " kg");
		str.append(" pour un prix total de " + valeurTotale + " €\n");
		
		return str.toString();
	}
}