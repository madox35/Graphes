package application;
public class FloydWarshall extends Algorithme{
	
	public Graphe grapheFermetureTransitive = null;
	
	public FloydWarshall(Graphe g)
	{
		
		Utils utils = new Utils();
		grapheFermetureTransitive = g;
		
		int N = g.getListeSommets().size();
		
		for(Sommet s : g.getListeSommets())
		{
			for (int x = 0; x < N; x++)
			{
				if(utils.sommetArelieSommetB(grapheFermetureTransitive, g.getListeSommets().get(x), s))
				{
					for (int y = 0; y < N; y++)
					{
						if(utils.sommetArelieSommetB(grapheFermetureTransitive, s, g.getListeSommets().get(y)))
						{
							// Si l'arc Sommet(x) vers Sommet(y) n'a pas déjà été créé aux précédents tours
							if(!utils.sommetArelieSommetB(grapheFermetureTransitive,g.getListeSommets().get(x),g.getListeSommets().get(y)))
							{
								grapheFermetureTransitive.getListeSommets().get(x).ajouterArc(new Arc(g.getListeSommets().get(x), g.getListeSommets().get(y)));
							}
						}
					}
				}
			}
		}
	}
}
