import java.io.FileNotFoundException;
import java.util.Scanner;
import SacADos.SacADos;
import ObjetsGenerator.ObjetGenerator;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		/**
		 * Générateur de liste d'objet développer en amont du projet
		 */
		ObjetGenerator gen = new ObjetGenerator(System.getProperty("user.dir").concat("\\Objets.txt"),15);

		boolean userIn = false;
		SacADos sac;
		Scanner sc = new Scanner(System.in);
		while (!userIn) {
			String chemin = "";
			int capacité = 0;
			String méthode = "";
			System.out.println("-- Résolution du problème du sac à dos --");
			System.out.println("Il existe trois méthodes de résolution");
			System.out.println("Veuillez saisir le chemin du fichier contenant les objets à choisir");
			chemin = sc.next();
			System.out.println("Veuillez saisir la capacité maximale du sac à remplir (en kg)");
			capacité = (int) sc.nextFloat();
			System.out.println("Veuillez maintenant choisir la méthode de résolution (Heuristique - Dynamique - PSE)");
			méthode = sc.next();
			try {
				sac = new SacADos(chemin, capacité);
				long startTime = System.nanoTime();
				userIn = sac.remplir(méthode);
				long stopTime = System.nanoTime();
				if (userIn) {
					System.out.println(sac);
					System.out.println("Temps d'exécution de l'algorithme " + méthode + " " + (stopTime - startTime) + " nanosecondes");
					System.out.println("Temps d'exécution de l'algorithme " + méthode + " " + (stopTime - startTime)/1000000 + " millisecondes");
				}
			} catch (FileNotFoundException e) {
				System.out.println("Le fichier que vous avez saisi n'existe pas ou n'est pas dans le bon répertoire");
				continue;
			}
		}
		sc.close();
	}

}
