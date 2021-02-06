package Solutions;

import java.util.ArrayList;

import SacADos.Objet;

/**
 * @author Yanis et Audran
 * Classe héritant de la super classe Solution et permettant
 * de résoudre le problème du sac à dos par la programmation
 * dynamique.
 */

public class SolutionDynamique extends Solution {

	/**
	 * Constructeur d'une solution dynamique
	 * 
	 * @param Objets la liste d'objets à mettre dans le sac
	 * @param capacité la capacité maximale du sac
	 */
	public SolutionDynamique(ArrayList<Objet> Objets, float capacité) {
		super(Objets, capacité);
	}
	
	/**
	 * Permet de résoudre le problème du sac à dos par la programmation dynamique
	 * 
	 * @return la liste d'objet optimale suivant la méthode de résolution choisie
	 * @see Solution.résoudre()
	 */

	public ArrayList<Objet> résoudre() {
		ArrayList<Objet> choix = new ArrayList<>();
		float[][] m = new float[getObjets().size()][(int) (getCapacité()) + 1];

		for (int j = 0; j < getCapacité() + 1; j++) {
			if (getObjets().get(0).getPoids() > j) {
				m[0][j] = 0;
			} else
				m[0][j] = getObjets().get(0).getPrix();
		}

		for (int i = 1; i < getObjets().size(); i++) {
			for (int j = 0; j < getCapacité() + 1; j++) {
				if (getObjets().get(i).getPoids() > j) {
					m[i][j] = m[i - 1][j];
				} else {
					m[i][j] = Math.max(m[i - 1][j],
							m[i - 1][j - (int) (getObjets().get(i).getPoids())] + getObjets().get(i).getPrix());
				}
			}
		}
		
		int i = getObjets().size() - 1;
		int j = (int) getCapacité();

		while (m[i][j] == m[i][j - 1]) {
			i--;
		}

		while (i >= 0) {
			if (i == 0 || m[i][j] != m[i - 1][j]) {
				if (j - getObjets().get(i).getPoids() >= 0) {
					choix.add(getObjets().get(i));
					j -= getObjets().get(i).getPoids();
				}
			}
			i--;
		}
		return choix;
	}
}
