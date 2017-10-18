package application;

import java.util.concurrent.CopyOnWriteArrayList;

public class Utils {
	
	
	public void afficherInformations(Graphe g)
	{
		System.out.println("\n\n\n### Informations du "+g+" ###");
		afficherSommets(g);
		afficherArcs(g);
		System.out.println("###################################");
	}
	
	
	
	private void afficherSommets(Graphe g){
		System.out.println("Le graphe contient les sommets suivants ("+g.getListeSommets().size()+")");
		System.out.println("***************************************");
			for (Sommet s : g.getListeSommets())
			{
				System.out.println("\t"+s+" a les prédecesseurs suivants :"+s.getListePredecesseurs());
				System.out.println("\t"+s+" a les successeurs suivants :"+s.getListeSuccesseurs());
				System.out.println();
			}	
		System.out.println();
	}
	
	
	
	private void afficherArcs(Graphe g){
		System.out.println("Le graphe contient les arcs suivants");
		System.out.println("***************************************");
		for (Sommet s : g.getListeSommets()) {
			afficherArcsDuSommet(s);
		}
	}
	
	
	
	private void afficherArcsDuSommet(Sommet s){
		System.out.println(s+" contient les arcs suivants ("+s.getListeArcs().size()+")");
		for (Arc a : s.getListeArcs()) {
			System.out.println("\t"+a.toString());
		}
		System.out.println();
	}
	
	
	
	
	// On vérifie si un arc existe entre les deux sommets, dans un sens précis
	public boolean sommetArelieSommetB(Graphe g, Sommet sommetA, Sommet sommetB)
	{
		for (Sommet sommet: g.getListeSommets()) {
			if(sommet == sommetA){
				for(Arc arc : sommet.getListeArcs()){
					if( arc.getSommetArrivee() == sommetB) return true;		
				}
			}
		}
		return false;
	}

	
	
	public void viderGraphe(Graphe g)
	{
		for (Sommet s: g.getListeSommets()) {
			s.getListeArcs().clear();
			s.getListePredecesseurs().clear();
			s.getListeSuccesseurs().clear();
		}
		
		g.getListeSommets().clear();
	}



	public boolean sommetEstDansListe(Sommet sommetAVerifier, CopyOnWriteArrayList<Sommet> listeSommets) {
		for(Sommet s: listeSommets){
			if(s == sommetAVerifier) return true;
		}
		return false;
	}
	
	@SuppressWarnings("unchecked")
	public void afficherCFC(Foulkes f){
		int i=0;
		
		for (CopyOnWriteArrayList<Sommet> liste : f.listeCFCs) {
			i++;
			System.out.println("CFC n°"+i+liste);
		}
	}

	@SuppressWarnings("rawtypes")
	public boolean cfcPresenteListe(CopyOnWriteArrayList listeCFC, CopyOnWriteArrayList<CopyOnWriteArrayList> listeCFCs) {
		for (CopyOnWriteArrayList l : listeCFCs) {
			if(l == listeCFC) return true;
		}
		return false;
	}
}
