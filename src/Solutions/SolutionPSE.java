package Solutions;

import java.util.ArrayList;

import SacADos.Objet;
import SacADos.SacADos;

/**
 * @author Yanis et Audran
 * Classe héritant de la super classe Solution et permettant
 * de résoudre le problème du sac à dos par une procédure de 
 * séparation et d'évaluation (PSE)
 */

public class SolutionPSE extends Solution {
	SacADos solution;

	/**
	 * Constructeur d'une solution PSE
	 * 
	 * @param Objets la liste d'objets à mettre dans le sac
	 * @param capacité la capacité maximale du sac
	 */
	public SolutionPSE(ArrayList<Objet> Objets, float capacité) {
		super(Objets, capacité);
		solution = new SacADos();
	}

	/**
	 * @author Yanis et Audran
	 * Classe interne privée permettant la création de
	 * noeudObjet, permettant la création d'un arbre de recherche.
	 */
	private class NoeudObjet {
		private NoeudObjet noeudGauche;
		private NoeudObjet noeudDroit;
		private float borneInférieure;
		private float borneSupérieure;
		private int profondeur;
		private boolean coupé;
		private SacADos choix;

		/**
		 * Constructeur du premier noeud de l'arbre de recherche.
		 * 
		 * @param Objets la liste d'objets à mettre dans le sac
		 * @param Capacité la capacité maximale du sac
		 */
		public NoeudObjet(ArrayList<Objet> Objets, float capacité) {
			Solution solution = new SolutionHeuristique(Objets, capacité);
			for (Objet o : solution.résoudre()) {
				borneInférieure += o.getPrix();
			}
			for (Objet o : Objets) {
				borneSupérieure += o.getPrix();
			}
			profondeur = 0;
			choix = new SacADos();
			coupé = false;
		}

		/**
		 * Constructeur d'un noeud
		 * 
		 * @param parent le noeud parent
		 * @param Objets la liste des objets à mettre dans le sac
		 */
		public NoeudObjet(NoeudObjet parent, ArrayList<Objet> Objets) {
			this.profondeur = parent.profondeur + 1;
			this.borneInférieure = parent.borneInférieure;
			this.borneSupérieure = parent.borneSupérieure;
			this.choix = new SacADos();
			this.choix.getSac().addAll(parent.choix.getSac());
		}


		/**
		 * Méthode permettant de calculer la borne
		 * supérieure du noeud
		 * 
		 * @param Objets la liste d'objets à mettre dans le sac
		 */
		public void calculerBorneSupérieure(ArrayList<Objet> Objets) {
			float tempSup = this.choix.getValeurTotale();
			for(int i=profondeur;i<Objets.size();i++) {
				tempSup += Objets.get(i).getPrix();
			}
			this.borneSupérieure = tempSup;
		}
		
		/**
		 * Méthode permettant de calculer la borne
		 * inférieure du noeud
		 * 
		 * @param Objets la liste d'objets à mettre dans le sac
		 */
		public void calculerBorneInférieure(ArrayList<Objet> Objets) {
			if(this.borneInférieure < this.choix.getValeurTotale()) {
				this.borneInférieure = this.choix.getValeurTotale();
			}
		}
		
		/**
		 * Méthode permettant de calculer les deux bornes
		 * du noeud, et de couper le noeud si nécessaire.
		 * 
		 * @param Objets la liste d'objets à mettre dans le sac
		 */
		public void calculerBorne(ArrayList<Objet> Objets) {
			this.calculerBorneSupérieure(Objets);
			this.calculerBorneInférieure(Objets);
			if(this.borneInférieure > this.borneSupérieure) {
				this.coupé = true;
			}
		}

		/**
		 * Méthode permettant la construction d'un arbre
		 * de recherche des solutions possibles pour la résolution
		 * du problème du sac à dos
		 * 
		 * @param Objets la liste d'objets à mettre dans le sac
		 * @param capacité la capacité maximale du sac
		 */
		public void construire(ArrayList<Objet> Objets, float capacité) {
			if (profondeur < Objets.size()) {
				this.noeudDroit = new NoeudObjet(this, Objets);
				this.noeudGauche = new NoeudObjet(this, Objets);
				this.noeudDroit.choix.getSac().add(Objets.get(profondeur));
				this.calculerBorne(Objets);
				if (noeudDroit.choix.getPoids() <= capacité) {
					noeudDroit.construire(Objets, capacité);
					noeudGauche.construire(Objets, capacité);
				} else {
					noeudDroit.coupé = true;
					noeudGauche.construire(Objets, capacité);
				}
			}
		}

		/**
		 * Méthode permettant de parcourir l'arbre de recherche
		 * de manière préfixé et d'obtenir une solution optimale 
		 * au problème du sac à dos.
		 * 
		 * @return la liste d'objet solution au problème du sac à dos
		 */
		public ArrayList<Objet> parcoursPrefixeMax() {
			if (this.noeudDroit != null && !this.noeudDroit.coupé) {
				if(this.choix.getValeurTotale() > solution.getValeurTotale()) {
					solution = this.choix;
				}
				this.noeudDroit.parcoursPrefixeMax();
			}
			if (this.noeudGauche != null && !this.noeudGauche.coupé) {
				if(this.choix.getValeurTotale() > solution.getValeurTotale()) {
					solution = this.choix;
				}
				this.noeudGauche.parcoursPrefixeMax();
			}
			return solution.getSac();
		}

	}

	
	/**
	 * Permet de résoudre le problème du sac à dos par la procédure de séparation et
	 * d'évaluation (PSE)
	 * 
	 * @return la liste d'objet optimale suivant la méthode de résolution choisies
	 * @see Solution.résoudre()
	 */
	@Override
	public ArrayList<Objet> résoudre() {
		NoeudObjet root = new NoeudObjet(getObjets(), getCapacité());
		root.construire(getObjets(), getCapacité());
		return root.parcoursPrefixeMax();
	}
}