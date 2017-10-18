package application;

import java.util.concurrent.CopyOnWriteArrayList;

public class Foulkes extends Algorithme{

	@SuppressWarnings("rawtypes")
	public CopyOnWriteArrayList<CopyOnWriteArrayList> listeCFCs = null;
	private CopyOnWriteArrayList<Sommet> NE = null;
	
	public Foulkes(Graphe g){
		
		Utils utils = new Utils();
		
		// NE ensemble des sommets non explorés
		NE = new CopyOnWriteArrayList<>();
		for (Sommet sommet : g.getListeSommets()) {
			NE.add(sommet);
		}
		
		listeCFCs = new CopyOnWriteArrayList<>();
		CopyOnWriteArrayList<Sommet> listeCFC = null;
		
		int i=0;
		
		for (Sommet s : g.getListeSommets())
		{
		
			if(utils.sommetEstDansListe(s, NE))
			{

				listeCFC = new CopyOnWriteArrayList<>();
				listeCFC.add(s);
				NE.remove(s);
				
				if(utils.sommetArelieSommetB(g, s, s))
				{
					for (int j = i+1; j < g.getListeSommets().size(); j++)
					{
						if(	utils.sommetArelieSommetB(g, g.getListeSommets().get(i), g.getListeSommets().get(j)) && 
							utils.sommetArelieSommetB(g, g.getListeSommets().get(j), g.getListeSommets().get(i)))
						{
							listeCFC.add(g.getListeSommets().get(j));
							NE.remove(g.getListeSommets().get(j));
						}
					}
				}
			}
			
			if(!listeCFC.isEmpty() && !utils.cfcPresenteListe(listeCFC, listeCFCs))
			{
				listeCFCs.add(listeCFC);
			}
			i++;
		}
	}
}
