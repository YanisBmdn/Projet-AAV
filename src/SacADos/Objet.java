package SacADos;

/**
 * @author Yanis et Audran
 * Classe qui permet de créer un objet à mettre dans un sac à dos.
 */
public class Objet {
	private String nom_Objet;
	private float poids;
	private float prix;
	private float vMoy;
	
	
	/**
	 * Constructeur permettant d'initialiser un nouveau objet avec un nom, un poids
	 * et un prix 
	 * 
	 * @param nom le nom de l'objet
	 * @param poids le poids de l'objet
	 * @param prix le prix de l'objet
	 */
	public Objet(String nom, float poids, float prix) {
		this.nom_Objet = nom;
		this.poids = poids;
		this.prix = prix;
		this.vMoy = prix / poids;
	}
	
	/**
	 * @return le poids de l'objet
	 */
	public float getPoids() {
		return poids;
	}

	/**
	 * @return le prix de l'objet
	 */
	public float getPrix() {
		return prix;
	}

	/**
	 * @return la valeur moyenne de l'objet (ratio prix/poids)
	 */
	public float getvMoy() {
		return vMoy;
	}

	
	/**
	 * @return une chaîne de caractères permettant de caractériser un objet, d'après
	 * ses attributs.
	 */
	public String toString() {
		return nom_Objet + " " + poids + " kg " + prix + " €";
	}

}