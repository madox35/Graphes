package application;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;


public class Graphe {

	private String idGraphe= UUID.randomUUID().toString();
	private CopyOnWriteArrayList<Sommet> listeSommets = null;
	
	public Graphe(){
		listeSommets = new CopyOnWriteArrayList<Sommet>();
	}
	
	
	/**
	 * @param listeSommets
	 */
	public Graphe(CopyOnWriteArrayList<Sommet> listeSommets) {
		this.listeSommets = listeSommets;
	}


	public static Graphe creerGrapheAleatoire(int nombre_sommets){
		
		// Constantes
		int lower = 0;
		
		Graphe g = new Graphe();
		Utils utils = new Utils();

		// Shortcut
		CopyOnWriteArrayList<Sommet> sh = g.getListeSommets();

		// On crée tous les sommets de notre graphe
		for (int i = 1; i <= nombre_sommets; i++) {
			g.ajouterSommet(new Sommet(i));
		}
		
		// On crée aléatoirement les arcs de notre graphes
		for (int i = 0; i < nombre_sommets*2; i++) 
		{
			int r1=0, r2=0;
			
			// On évite que le sommet de départ soit le même que celui d'arrivée
			while(r1 == r2)
			{
				// On choisit 2 sommets aléatoirements
				r1 = (int)(Math.random() * (nombre_sommets - lower)) + lower;
				r2 = (int)(Math.random() * (nombre_sommets - lower)) + lower;
			}
			
			//Si un arc n'existe pas déjà entre les deux sommets
			if(!utils.sommetArelieSommetB(g, g.getListeSommets().get(r1), g.getListeSommets().get(r2))){
				sh.get(r1).ajouterArc(new Arc(sh.get(r1),sh.get(r2)));
			}
					
		}
		return g;
	}
	
	
	public static Graphe grapheEtudie(){

		Graphe g = new Graphe();
		for (int i = 1; i <= 6; i++) {
			g.ajouterSommet(new Sommet(i));
		}
		
		// Shortcut
		CopyOnWriteArrayList<Sommet> sh = g.getListeSommets();
		
		sh.get(0).ajouterArc(new Arc(sh.get(0),sh.get(1)));
		sh.get(0).ajouterArc(new Arc(sh.get(0),sh.get(3)));
		sh.get(1).ajouterArc(new Arc(sh.get(1),sh.get(2)));
		sh.get(1).ajouterArc(new Arc(sh.get(1),sh.get(4)));
		sh.get(2).ajouterArc(new Arc(sh.get(2),sh.get(4)));
		sh.get(3).ajouterArc(new Arc(sh.get(3),sh.get(2)));
		sh.get(4).ajouterArc(new Arc(sh.get(4),sh.get(3)));
		sh.get(5).ajouterArc(new Arc(sh.get(5),sh.get(2)));
		sh.get(5).ajouterArc(new Arc(sh.get(5),sh.get(4)));
		
		return g;
	}
	
	/**
	 * @return the listeSommets
	 */
	public CopyOnWriteArrayList<Sommet> getListeSommets() {
		return listeSommets;
	}



	/**
	 * @param listeSommets the listeSommets to set
	 */
	public void setListeSommets(CopyOnWriteArrayList<Sommet> listeSommets) {
		this.listeSommets = listeSommets;
	}

	
	public void ajouterSommet(Sommet s){
		listeSommets.add(s);
	}


	public void retirerSommet(Sommet s){
		
		// On efface la liste des arcs du sommets à supprimer
		s.getListeArcs().clear();
		for (Sommet sommet : getListeSommets()){
			
			// Pour chaque sommet, on vérifie si le sommet d'arriver est celui de départ
			for (Arc arc : sommet.getListeArcs()){
				
				System.out.println(sommet.getListeArcs());
				if( arc.getSommetArrivee() == s ){
					// Si c'est le cas, on supprime l'arc
					sommet.retirerArc(arc);
				}
			}
		}
		// Enfin on retire le sommet de la liste des sommets
		listeSommets.remove(s);
	}
	

	@Override
	public String toString() {
		return "Graphe["+idGraphe+"]";
	}
}
