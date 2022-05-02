package it.univr.GrafoPesato;

public class Main {

	public static void main(String[] args) {
		GrafoPesato grafoprova = new GrafoPesato("Alessio", "Mattia", "Willi", "Danial", "Lorenzo", "Mykle");
		
		grafoprova.newEdge(1, grafoprova.getNode("Alessio"), grafoprova.getNode("Mattia"));
		grafoprova.newEdge(1, grafoprova.getNode("Mattia"), grafoprova.getNode("Alessio"));
		grafoprova.newEdge(1, grafoprova.getNode("Mattia"), grafoprova.getNode("Willi"));
		grafoprova.newEdge(1, grafoprova.getNode("Willi"), grafoprova.getNode("Mattia"));
		grafoprova.newEdge(1, grafoprova.getNode("Willi"), grafoprova.getNode("Danial"));
		grafoprova.newEdge(1, grafoprova.getNode("Danial"), grafoprova.getNode("Lorenzo"));
		grafoprova.newEdge(1, grafoprova.getNode("Lorenzo"), grafoprova.getNode("Mykle"));
		grafoprova.print();
		grafoprova.bfs(grafoprova.getNode("Alessio"));
		System.out.println("Stampo la BFS dal nodo Alessio al nodo Mykle");
		grafoprova.printPath(grafoprova.getNode("Alessio"), grafoprova.getNode("Mykle"));
		System.out.println("\nStampo la DFS");
		grafoprova.dfs();
		//grafoprova.printEndVisit();
		grafoprova.topologicalSort();
	}
}
