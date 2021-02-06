package Solutions;

import java.util.ArrayList;
import SacADos.Objet;

/**
 * @author Yanis et Audran 
 * Classe abstraite permettant la création de méthode de
 * résolution du problème du sac à dos
 */
public abstract class Solution {
	private float capacité;
	private ArrayList<Objet> Objets;

	/**
	 * Constructeur d'une solution
	 * 
	 * @param Objets   la liste d'objets à mettre dans le sac
	 * @param capacité la capacité maximale du sac
	 */
	public Solution(ArrayList<Objet> Objets, float capacité) {
		this.Objets = Objets;
		this.capacité = capacité;
	}

	/**
	 * @return la capacité
	 */
	public float getCapacité() {
		return capacité;
	}

	/**
	 * @return la liste des objets
	 */
	public ArrayList<Objet> getObjets() {
		return Objets;
	}


	/**
	 * Méthode permettant de résoudre le problème du sac à dos
	 * 
	 * @return la liste d'objet optimale suivant la méthode de résolution choisie
	 * 
	 * @see SolutionHeuristique.résoudre()
	 * @see SolutionDynamique.résoudre()
	 * @see SolutionPSE.résoudre()
	 */
	public abstract ArrayList<Objet> résoudre();
}
