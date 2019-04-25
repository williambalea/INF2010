import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;


public class Graph {

	private List<Node> nodes = new ArrayList<Node>(); // Noeuds
	private List<Edge> edges = new ArrayList<Edge>();; // Les arcs
	static final double inf = 99999;
	private Scanner x;
	public Graph() {
		
	}
	
	// Fonction ajoutee pour ouvrir le fichier avec un try - catch
	public void ouvrirFichier(String fichier) {
		try {
			x = new Scanner (new File (fichier));
		}
		catch (Exception e) {
			System.out.println("Not found" + e);
		}
	}

	public void readFromFile(String filePath,String separtor) {
		
		ouvrirFichier(filePath);
		int compteurId = 0;
		String ligne = x.nextLine();
		Scanner scanneNode = new Scanner(ligne);
		scanneNode.useDelimiter(separtor);
		while (scanneNode.hasNext() ) {
			Node n = new Node(compteurId++, scanneNode.next());
			nodes.add(n);
		} // toutes les nodes sont crees et mis dans le tableau nodes
		
		int source = 0;
		while (x.hasNext()) {
			String ligneEdge = x.nextLine();
			Scanner scanneEdge = new Scanner(ligneEdge);
			scanneEdge.useDelimiter(",");
			while (scanneEdge.hasNext()){
				for (int destination = 0; destination < compteurId; destination ++) {
					String tmp = scanneEdge.next();
					if (!tmp.equals("inf") &&  !tmp.equals("0")) {
						Edge e = new Edge(nodes.get(source), nodes.get(destination), Double.parseDouble(tmp));
						edges.add(e);
					}
				}
				source++;
			}
		} // fichier parcouru pour placer les edges
		
		
	}
	
	public List<Edge> getOutEdges(Node source) {
		List<Edge> outEdges = new ArrayList<Edge>();
		// compl�ter
		for (int i = 0; i < edges.size(); i ++) {
			if (edges.get(i).getSource().equals(source)) {
				outEdges.add(edges.get(i));
			}
		}
		return outEdges;	
	}
	
	public List<Edge> getInEdges(Node dest) {
		List<Edge> inEdges = new ArrayList<Edge>() ; 
		// compl�ter
		for (int i = 0; i < edges.size(); i ++) {
			if (edges.get(i).getDestination().equals(dest)) {
				inEdges.add(edges.get(i));
			}
		}
		return inEdges;		
	}
	// Accesseurs 
	public List<Node> getNodes() {
		return nodes;
	}
	public void setNodes(List<Node> nodes) {
		this.nodes = nodes;
	}
	public List<Edge> getEdges() {
		return edges;
	}
	public void setEdges(List<Edge> edges) {
		this.edges = edges;
	}
	public Node getNodeByName(String name){
		for (Node node : nodes) {
			if(node.getName().equals(name)){
				return node;
			}
		}
		return null;
	}
	
	public Node getNodeById(int id){
		for (Node node : nodes) {
			if(node.getId()==id){
				return node;
			}

		}
		return null;
	}
}
