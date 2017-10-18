package application;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class InterfaceGraphique extends JFrame {

	@SuppressWarnings("rawtypes")
	private CopyOnWriteArrayList<CopyOnWriteArrayList> listeCFCs = new CopyOnWriteArrayList<>();
	private JPanel contentPane = new JPanel(new BorderLayout());
	private JPanel canvas = null;
	private Box box = null;
	private JButton but_warshall = new JButton("Algorithme de Floyd-Warshall");
	private JButton but_foulkes = new JButton("Algorithme de Foulkes");
	private JButton reset = new JButton("Générer un nouveau graphe");
	private JTextArea t1 = new JTextArea(5,25);
	private JTextArea t2 = new JTextArea(5,25);
	private JTextArea t3 = new JTextArea(5,25);
	private Graphe graphe;
	
	public InterfaceGraphique(Graphe g) {
		super("Interface de visualisation d'un graphe");

		graphe = g;
		Utils u = new Utils();
		this.setContentPane(contentPane);
	   
		but_warshall.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				FloydWarshall f = new FloydWarshall(graphe);
				graphe = f.grapheFermetureTransitive;
				u.afficherInformations(graphe);
				refresh();
			}
		});
		
		
		but_foulkes.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Foulkes f = new Foulkes(graphe);
				listeCFCs = f.listeCFCs;
//				u.afficherCFC(f);
				refresh();
			}
		});

		reset.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				u.viderGraphe(graphe);
				graphe = Graphe.creerGrapheAleatoire(10);
				listeCFCs = new CopyOnWriteArrayList<>();
//				u.afficherInformations(graphe);
				refresh();				
			}
		});
		
		JPanel head = new JPanel(new FlowLayout());
		this.getContentPane().add(head, BorderLayout.NORTH);
	    head.add(but_warshall, BorderLayout.NORTH);
	    head.add(but_foulkes, BorderLayout.NORTH);
	    head.add(reset, BorderLayout.NORTH);
	    
	    ajouterCanvas();
	    dessinerInfos();

		this.getContentPane().add(box, BorderLayout.EAST);
	    
	    this.setSize(new Dimension(1200,800));
	    this.setLocationRelativeTo(null);
	    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	    this.setVisible(true);
	}	
	
	public void ajouterCanvas(){

		canvas = new VueGraphe(graphe);
	    this.getContentPane().add(canvas, BorderLayout.CENTER);
	}
	
	public void dessinerInfos(){
		box = Box.createVerticalBox();
		    
		box.add(new JLabel("Liste des sommets"));
		t1.setEditable(false);
		for (Sommet s: graphe.getListeSommets()) {
			t1.append(s.toString()+"\n");
		}
		
		JScrollPane vbar = new JScrollPane(t1);
		box.add(vbar);
		
		box.add(new JLabel("Liste des arcs"));
		for (Sommet s : graphe.getListeSommets()) {
			for (Arc a : s.getListeArcs()) {
				t2.append(a.toString()+"\n");	
			}
		}
		t2.setEditable(false);
		JScrollPane vbar2 = new JScrollPane(t2);
		box.add(vbar2);
		
		box.add(new JLabel("Liste des CFCs"));
		t3.setEditable(false);
		int i=1;
		for (CopyOnWriteArrayList<Sommet> liste : listeCFCs) {
			t3.append("CFC n°"+ i +" = " + liste+"\n");
			i++;
		}
		
		JScrollPane vbar3 = new JScrollPane(t3);
		box.add(vbar3);	
	}
	
	public void refresh(){
		contentPane.remove(canvas);
		ajouterCanvas();
	
		t1.setText("");
		t2.setText("");
		t3.setText("");
		
		for (Sommet s: graphe.getListeSommets()) {
			t1.append(s.toString()+"\n");
		}
		
		for (Sommet s : graphe.getListeSommets()) {
			for (Arc a : s.getListeArcs()) {
				t2.append(a.toString()+"\n");	
			}
		}
		
		int i=1;
		for (CopyOnWriteArrayList<Sommet> liste : listeCFCs) {
			t3.append("CFC n°"+ i +" = "+ liste +"\n");
			i++;
		}
		
		canvas.revalidate();
		box.revalidate();
		contentPane.repaint();
	}
}
