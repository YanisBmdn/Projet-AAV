package Solutions;

import java.util.ArrayList;
import SacADos.Objet;

/**
 * @author Yanis et Audran
 * Classe héritant de la super classe Solution et permettant
 * de résoudre le problème du sac à dos par une méthode 
 * heuristique (ou gloutonne)
 */
public class SolutionHeuristique extends Solution{
	
	/**
	 * Constructeur d'une solution heuristique
	 * 
	 * @param Objets la liste d'objets à mettre dans le sac
	 * @param capacité la capacité maximale du sac
	 */
	public SolutionHeuristique(ArrayList<Objet> Objets, float capacité) {
		super(Objets,capacité);
	}
	
	
	/**
	 * Permet de résoudre le problème du sac à dos à l'aide d'un algorithme heuristique
	 * 
	 * @return la liste d'objet optimale suivant la méthode de résolution choisie
	 * @see Solution.résoudre()
	 */
	
	@Override
	public ArrayList<Objet> résoudre() {
		ArrayList<Objet> choix = new ArrayList<Objet>();
		int poids = 0;
		this.quicksort(0, getObjets().size() - 1);
		for (Objet o : getObjets()) {
			if (poids + o.getPoids() < getCapacité()) {
				poids += o.getPoids();
				choix.add(o);
			}
		}
		return choix;
	}
	
	/**
	 * Méthode permettant le tri décroissant d'une liste
	 * par appel récursif à une méthode de "partition" de 
	 * cette liste.
	 * (méthode de tri rapide)
	 * 
	 * @param début le début de la liste/sous-liste
	 * @param fin la fin de la liste/sous-liste
	 */
	public void quicksort(int début,int fin) {
		if(début<fin) {
			int indexPivot = partition(début,fin);
			quicksort(début,indexPivot-1);
			quicksort(indexPivot+1,fin);
		}
	}
	
	/**
	 * Méthode permettant de trier par ordre décroissant la
	 * liste/sous-liste d'objet (suivant leur valeur moyenne) et de définir
	 * un nouveau pivot
	 * 
	 * @param début le début de la liste/sous-liste
	 * @param fin la fin de la liste/sous-liste
	 * @return la nouvelle valeur du pivot (permettant la partition des listes)
	 */
	private int partition(int début, int fin){
		float valeurPivot = getObjets().get(fin).getvMoy();
		int d = début-1;
		for(int j=début;j<fin;j++) {
			if(getObjets().get(j).getvMoy() >= valeurPivot) {
				d++;
				Objet swapObjet = getObjets().get(d);
				getObjets().set(d, getObjets().get(j));
				getObjets().set(j, swapObjet);
				
			}
		}
		Objet swapObjet = getObjets().get(d+1);
		getObjets().set(d+1, getObjets().get(fin));
		getObjets().set(fin, swapObjet);
	
		return d+1;
	}
}