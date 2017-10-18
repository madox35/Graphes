package application;

import javax.swing.JPanel;
import org.apache.commons.collections15.Transformer;
import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.graph.DirectedSparseMultigraph;
import edu.uci.ics.jung.visualization.BasicVisualizationServer;

@SuppressWarnings("serial")
public class VueGraphe extends JPanel {
	
	public VueGraphe(Graphe g){
		
		DirectedSparseMultigraph<Sommet, Arc>g1 = new DirectedSparseMultigraph<>();
		
		for(Sommet s: g.getListeSommets()){
			
			g1.addVertex(s);
			for (Arc a : s.getListeArcs()) {
				g1.addEdge(a, a.getSommetDepart(), a.getSommetArrivee(), edu.uci.ics.jung.graph.util.EdgeType.DIRECTED);
			}
		}
		
		BasicVisualizationServer<Sommet,Arc> vv = new BasicVisualizationServer<Sommet,Arc>(new CircleLayout<Sommet, Arc>(g1));
		vv.getRenderContext().setVertexLabelTransformer(new Transformer<Sommet, String>() {
			
			@Override
			public String transform(Sommet s) {
				return String.valueOf(s.getNumero());
			}
		});
		this.add(vv);
     }
	
	@Override
	public void repaint() {
		super.repaint();
	}
}
