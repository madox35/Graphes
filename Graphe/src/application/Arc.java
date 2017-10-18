package application;

import java.util.UUID;

public class Arc {

	@SuppressWarnings("unused")
	private String idArc= UUID.randomUUID().toString();
	private Sommet sommetDepart;
	private Sommet sommetArrivee;
	
	
	/**
	 * @param sommetDepart
	 * @param sommetArrivee
	 */
	public Arc(Sommet sommetDepart, Sommet sommetArrivee) {
		this.sommetDepart = sommetDepart;
		this.sommetArrivee = sommetArrivee;
	}
	

	/**
	 * @return the sommetDepart
	 */
	public Sommet getSommetDepart() {
		return sommetDepart;
	}
	/**
	 * @param sommetDepart the sommetDepart to set
	 */
	public void setSommetDepart(Sommet sommetDepart) {
		this.sommetDepart = sommetDepart;
	}
	/**
	 * @return the sommetArrivee
	 */
	public Sommet getSommetArrivee() {
		return sommetArrivee;
	}
	/**
	 * @param sommetArrivee the sommetArrivee to set
	 */
	public void setSommetArrivee(Sommet sommetArrivee) {
		this.sommetArrivee = sommetArrivee;
	}

	
	@Override
	public String toString() {
		return sommetDepart+" --- > "+sommetArrivee;
	}
	
}
