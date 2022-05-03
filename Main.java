package it.univr.GrafoPesato;

public class Main {

	public static void main(String[] args) {
		GrafoPesato grafoprova = new GrafoPesato("Alessio", "Mattia", "Willi", "Danial", "Lorenzo", "Mykle");
		
		grafoprova.newEdge(1, "Alessio", "Mattia");
		grafoprova.newEdge(2, "Mattia", "Alessio");
		grafoprova.newEdge(1, "Mattia", "Willi");
		grafoprova.newEdge(4, "Willi", "Mattia");
		grafoprova.newEdge(1, "Willi", "Danial");
		grafoprova.newEdge(5, "Danial", "Lorenzo");
		grafoprova.newEdge(1, "Lorenzo","Mykle");
		grafoprova.print();
		//grafoprova.bfs("Alessio");
		//System.out.println("Stampo la BFS dal nodo Alessio al nodo Mykle");
		//grafoprova.printPath("Alessio", "Mykle");
		//grafoprova.dfs();
		//grafoprova.printEndVisit();
		//grafoprova.topologicalSort();
		grafoprova.trasposto();
		grafoprova.print();
		
	}
}
