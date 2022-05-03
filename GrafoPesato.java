package it.univr.GrafoPesato;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class GrafoPesato {
	private List<Node> nodiGrafo = new ArrayList<>();
	private List<Node> topologicalGraph = new ArrayList<>();
	private List<Node> trasposto = new LinkedList<Node>();

	public GrafoPesato(String...nodi) {
		for (String x : nodi) {
			nodiGrafo.add(new Node(x));
		}
	}
	public void newEdge(int weight, String from, String to) {
		for (Node x : nodiGrafo) {
			if (x.name.compareTo(from) == 0)
				//aggiungo un nodo alla lista di adiacenza di form
				x.addEdge(weight,getNode(to));
		}
	}
	public void newNode(String name) {
		if(!nodiGrafo.contains(getNode(name)))
			nodiGrafo.add(new Node(name));
	}
	
	public void print() {		
		for (Node node : nodiGrafo) {
			System.out.println(node.toString() + " -> " + node.adj.toString());
		}
		System.out.print("\n");

	}
	
	public void trasposto() {
		trasposto = new LinkedList<GrafoPesato.Node>();
		for (Node node : nodiGrafo) {
			for (Edge edge : node.adj) {
				if(!trasposto.contains(edge.destination)) 
					trasposto.add(new Node(edge.destination.name));
				for (Node x : trasposto) {
					if(x.equals(edge.destination))
						x.addEdge(edge.weight, node);
				}
			}
		}
		nodiGrafo = trasposto;
	}
	
	public void bfs(String s) {
		Node source = getNode(s);
		this.resetNodes();
		Node u;
		source.distance=0;
		source.setGray();
		source.pi=null;
		List<Node> q = new LinkedList<>();
		q.add(source);
		while (!q.isEmpty()) {
			u=q.remove(0);
			for (Edge v : u.adj) {
				if (v.destination.color.compareTo("white") == 0) {
					v.destination.setGray();
					v.destination.distance= u.distance + 1; //numero livelli
					v.destination.distance = v.weight; //aggiornamento del peso dall'arco al prossimo nodo
					v.destination.pi = u;
					q.add(v.destination);
				}
			}
			u.setBlack();
		}
	}
	private int time;
	public void dfs() {
		this.resetNodes();
		time=0;
		for (Node u : nodiGrafo) {
			if (u.color.compareTo("white")==0)
				dfsVisit(u);
		}
	}
	
	
	private void dfsVisit(Node u) {
		time++;
		u.distance=time;
		u.setGray();
		for (Edge v : u.adj) {
			if (v.destination.color.compareTo("white")==0) {
				v.destination.pi = u;
				dfsVisit(v.destination);
			}
		}
		u.setBlack();
		time++;
		u.endTime=time;	
		//lista utilizzata in caso di ordinamento topologico
		topologicalGraph.add(u);
	}
	
	public void printEndVisit() {
		for (Node x : nodiGrafo) {
			System.out.println(x.name + "(" + x.distance +"/"+ x.endTime +")");
		}
	}
	
	public void printPath(String s, String d) {
		Node source = getNode(s);
		Node destination = getNode(d);
		if (source.equals(destination))
			System.out.print(source.toString());
		else if (destination.pi == null){
			System.out.println("Non ci sono destinazioni");
		}else{
			printPath(source.name, destination.pi.name);
			System.out.print( " ---"+ destination.distance + "--> " + destination.toString());
		}
	}
	/*
	 * ogni volta che applico un algoritmo applico il reset per non avere risultati ambigui
	 */
	private void resetNodes() {
		for (Node node : nodiGrafo) {
			node.setWhite();
			node.pi=null;
			node.distance=Integer.MAX_VALUE;
			node.endTime=0;
			topologicalGraph = new ArrayList<>();
		}
	}
	
	/*
	 * data una stringa restituisce il nodo appartenente al grafo
	 */
	private Node getNode(String name) {
		for (Node node : nodiGrafo) {
			if(node.name.compareTo(name)==0)
				return node;
		}
		throw new IllegalArgumentException("Nodo inesistente");
	}
	
	/*
	 * Ordinamento topologico
	 */
	public void topologicalSort() {
		this.resetNodes();
		this.dfs();
		java.util.Collections.reverse(topologicalGraph);
		System.out.println("");
		for (Node x : topologicalGraph) {
			System.out.println(x.name + "(" + x.distance +"/"+ x.endTime +")");
		}
		
	}
	
	//arco orientato verso una destinazione con peso
	private static class Edge{
		private int weight;
		private Node destination;
		
		private Edge(int weigth, Node destination) {
			this.weight=weigth;
			this.destination=destination;
		}
		
		public String toString() {
			return " (" + weight + ", " + destination.toString() + ") ";
		}
	}
	
	private static class Node {
		private String name;
		private String color;
		private int distance;
		private Node pi;
		private int endTime;
		//lista di adiacenza di ogni nodo
		private List<Edge> adj = new LinkedList<>();
		
		private Node(String name) {
			color = "white";
			this.name = name;
			distance = 0;
			endTime = 0;
			pi = null;
		}
		
		//aggiungere un arco
		private void addEdge(int weight, Node dest) {
			adj.add(new Edge(weight, dest));
		}
		
		public String toString() {
			return this.name;
		}
		
		public boolean equals(Object other) {
			return other instanceof Node && this.name == ((Node)other).name;
		}
		
		private void setGray() {
			color="grey";
		}
		
		private void setBlack() {
			color="black";
		}
		
		private void setWhite() {
			color="white";
		}
	}
}
