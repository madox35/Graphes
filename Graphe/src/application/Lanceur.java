/**
 * 
 */
package application;

/**
 * @author Hugo Jov� (s�curit�)
 *
 */
public class Lanceur {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		
//		Utils utils = new Utils();	
		Graphe g = new Graphe();
//		g = Graphe.creerGrapheAleatoire(10);
//		utils.viderGraphe(g);
		g = Graphe.grapheEtudie();

		new InterfaceGraphique(g);
	}
}
