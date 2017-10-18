package application;

import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

public class Sommet {
	
	public int xPos = 0;
	public int yPos = 0;
	
	@SuppressWarnings("unused")
	private String idSommet = UUID.randomUUID().toString();
	private Integer numero;
	private CopyOnWriteArrayList<Arc> listeArcs = new CopyOnWriteArrayList<Arc>();
	private CopyOnWriteArrayList<Sommet> listeSuccesseurs = new CopyOnWriteArrayList<Sommet>();
	private CopyOnWriteArrayList<Sommet> listePredecesseurs = new CopyOnWriteArrayList<Sommet>();
	

	

	/**
	 * @param numero
	 */
	public Sommet(Integer numero) {
		this.numero = numero;
	}

	

	/**
	 * @param numero
	 * @param listeArc
	 */
	public Sommet(Integer numero, CopyOnWriteArrayList<Arc> listeArc) {
		this.numero = numero;
		this.listeArcs = listeArc;
	}



	/**
	 * @param numero
	 * @param listeArc
	 * @param listeSuccesseurs
	 * @param listePredecesseurs
	 */
	public Sommet(Integer numero, CopyOnWriteArrayList<Arc> listeArcs, CopyOnWriteArrayList<Sommet> listeSuccesseurs, CopyOnWriteArrayList<Sommet> listePredecesseurs) {
		this.numero = numero;
		this.listeArcs = listeArcs;
		this.listeSuccesseurs = listeSuccesseurs;
		this.listePredecesseurs = listePredecesseurs;
	}


	public void ajouterArc(Arc a){
		a.getSommetArrivee().ajouterPredecesseur(a.getSommetDepart());
		a.getSommetDepart().ajouterSuccesseur(a.getSommetArrivee());
		listeArcs.add(a);
	}
	
	public void retirerArc(Arc a){
		a.getSommetArrivee().retirerPredecesseur(a.getSommetDepart());
		a.getSommetDepart().retirerSuccesseur(a.getSommetArrivee());
		listeArcs.remove(a);
	}
	
	public void retirerArcs(){
//		for (Arc arc : listeArcs) {
//			retirerArc(arc);
//		}
	}
	
	public void ajouterPredecesseur(Sommet s){
		listePredecesseurs.add(s);
	}
	
	public void retirerPredecesseur(Sommet s){
		listePredecesseurs.remove(s);
	}
	
	public void ajouterSuccesseur(Sommet s){
		listeSuccesseurs.add(s);
	}
	
	public void retirerSuccesseur(Sommet s){
		listeSuccesseurs.remove(s);
	}
	
	/**
	 * @return the numero
	 */
	public Integer getNumero() {
		return numero;
	}



	/**
	 * @param numero the numero to set
	 */
	public void setNumero(Integer numero) {
		this.numero = numero;
	}



	/**
	 * @return the listeArc
	 */
	public CopyOnWriteArrayList<Arc> getListeArcs() {
		return listeArcs;
	}



	/**
	 * @param listeArc the listeArc to set
	 */
	public void setListeArcs(CopyOnWriteArrayList<Arc> listeArc) {
		this.listeArcs = listeArc;
	}



	/**
	 * @return the listeSuccesseurs
	 */
	public CopyOnWriteArrayList<Sommet> getListeSuccesseurs() {
		return listeSuccesseurs;
	}



	/**
	 * @param listeSuccesseurs the listeSuccesseurs to set
	 */
	public void setListeSuccesseurs(CopyOnWriteArrayList<Sommet> listeSuccesseurs) {
		this.listeSuccesseurs = listeSuccesseurs;
	}



	/**
	 * @return the listePredecesseurs
	 */
	public CopyOnWriteArrayList<Sommet> getListePredecesseurs() {
		return listePredecesseurs;
	}



	/**
	 * @param listePredecesseurs the listePredecesseurs to set
	 */
	public void setListePredecesseurs(CopyOnWriteArrayList<Sommet> listePredecesseurs) {
		this.listePredecesseurs = listePredecesseurs;
	}



	@Override
	public String toString() {
		return "Sommet n°"+this.numero;
	}
}
