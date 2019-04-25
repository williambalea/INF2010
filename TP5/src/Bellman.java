import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.Vector;


public class Bellman {
	private Graph graph;
	private Node sourceNode;
	private List<Vector<Double>> piTable =  new ArrayList<Vector<Double>>();
	private List<Vector<Integer>> rTable =  new ArrayList<Vector<Integer>>();
	private Boolean circuitNegatif = false;
	
	public Bellman (Graph g) {
		this.graph = g;
	}
	
	public void setSourceNode(Node source) {
		this.sourceNode = source;
	}
	
	public void shortestPath() {
		//preparation
		int k = 1;
		piTable.add(new Vector<Double>());
		rTable.add(new Vector<Integer>());
		
		for (int i = 0; i < graph.getNodes().size(); i++) {
			piTable.get(0).add(graph.inf);
			rTable.get(0).add((int) graph.inf);
		} // on met toute la premiere ligne à inf
		
		piTable.get(0).set(sourceNode.getId(), 0.0); // sourceNode distance 0
		
		for (; k <= graph.getNodes().size(); k++) { // on doit arreter si k = nb de nodes
			//nouvelle iteration, nouvelle rangee, meme que k - 1
			piTable.add(new Vector<Double>(piTable.get(k - 1)));
			rTable.add(new Vector<Integer>(rTable.get(k - 1)));

			//on va comparer les distances de chaque edges
			for (int e = 0; e < graph.getEdges().size(); e++) {
				int idSourceEdge = graph.getEdges().get(e).getSource().getId();
				int idDestEdge = graph.getEdges().get(e).getDestination().getId();
				Double ancienneDist = piTable.get(k - 1).get(idDestEdge);
				
				Double valeurSource = piTable.get(k - 1).get(idSourceEdge);
				Double distance = graph.getEdges().get(e).getDistance();
				Double presenteDist = valeurSource + distance;
				
				if (ancienneDist.compareTo(presenteDist) > 0) {
					piTable.get(k).set(idDestEdge, presenteDist);
					rTable.get(k).set(idDestEdge,idSourceEdge);
				}
			}
			
			//Avant d'incrementer, verifier si les deux derniere lignes sont pareilles
			if (piTable.get(k - 1).equals(piTable.get(k)))
				break;
		}
		
		// voir si on a un circuit neg
		if (k == graph.getNodes().size() + 1) {
			circuitNegatif = true;
		} 
	}
	
	String ordre(int depart, int arrive) {
		if (arrive == depart)
			return graph.getNodes().get(depart).getName();
		else
			return ordre(depart, rTable.get(rTable.size()- 1).get(arrive)) + "->" + graph.getNodes().get(arrive).getName();
	}
	
	
	public void  diplayShortestPaths() {
		//Compléter
		String builder = "";
		if (circuitNegatif == false) {
			builder += "=> Les chemins sont : \n\n";
			for (int i = 0; i < graph.getNodes().size(); i++) {
				if (i != sourceNode.getId() ) {
					builder += "[" + sourceNode.getName() + " - " + graph.getNodes().get(i).getName() + "] ";
					builder += piTable.get(piTable.size() - 1).get(i) + " : ";
					builder += ordre(sourceNode.getId(), i) + "\n";
					
				}
			}
		}
		else if (circuitNegatif == true) {
			builder += "==> le graph contient un circuit de cout negatif\n\n";
			for (int i = 0; i < piTable.size(); i++) {
				if (piTable.get(piTable.size() - 1).get(i) < 0) {
					builder += "[" + graph.getNodes().get(i).getName() + " - " + graph.getNodes().get(i).getName() + "] : ";
					builder += ordre(i, i+2);
					builder += "->" + graph.getNodes().get(i).getName();
					break;
				}
			}
		}
		System.out.println(builder);
	}

	public void displayTables() {
	 //Compléter
		String builderPTable = "<< PITable >>:\n\t\t";
		for (int i = 0; i < graph.getNodes().size(); i ++) {
			builderPTable += graph.getNodes().get(i).getName() + "\t";
		}
		builderPTable += "\n";
		
		for (int y = 0; y < piTable.size(); y++) {
			builderPTable += y + "\t:\t";
			for (int x = 0; x < piTable.get(y).size(); x++) {
				if (piTable.get(y).get(x) >= (graph.inf - 100)) { //graph.inf - 100 car il y des nombres a 9995
					builderPTable += "inf\t";
				}
				else {
					builderPTable += piTable.get(y).get(x) + "\t";
				}
			}
			builderPTable += "\n";
		}
		
		String builderRTable = "<< RTable >>:\nk\t:\t";
		for (int i = 0; i < graph.getNodes().size(); i ++) {
			builderRTable += graph.getNodes().get(i).getName() + "\t";
		}
		builderRTable += "\n";
		
		for (int y = 0; y < rTable.size(); y++) {
			builderRTable += y + "\t:\t";
			for (int x = 0; x < rTable.get(y).size(); x++) {
				if (rTable.get(y).get(x) == graph.inf) {
					builderRTable += "-\t";
				}
				else {
					int position = rTable.get(y).get(x);
					builderRTable += graph.getNodes().get(position).getName() + "\t";
				}
			}
			builderRTable += "\n";
		}
		
		System.out.println(builderPTable + "\n" + builderRTable);
		
	}
}
