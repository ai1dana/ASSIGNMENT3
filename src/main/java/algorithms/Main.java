package algorithms;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<Edge> edges = new ArrayList<>();
        edges.add(new Edge(0, 1, 4));
        edges.add(new Edge(0, 2, 3));
        edges.add(new Edge(1, 2, 1));
        edges.add(new Edge(1, 3, 2));
        edges.add(new Edge(2, 3, 4));
        edges.add(new Edge(3, 4, 5));

        KruskalMST kruskal = new KruskalMST(edges, 5);

        System.out.println("Edges in MST:");
        for (Edge edge : kruskal.getMst()) {
            System.out.println(edge.getVertex1() + " - " + edge.getVertex2() + " : " + edge.getWeight());
        }
        System.out.println("Total weight of MST: " + kruskal.getTotalWeight());
    }
}

