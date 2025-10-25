package algorithms;

import java.util.*;

public class PrimMST {
    private List<Edge> mst;
    private double totalWeight;
    private long comparisonCount = 0;
    private long extractionCount = 0;

    public PrimMST(List<Edge> edges, int numVertices) {
        mst = new ArrayList<>();
        totalWeight = 0;

        Map<Integer, List<Edge>> graph = buildGraph(edges, numVertices);
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        boolean[] inMST = new boolean[numVertices];
        inMST[0] = true;
        pq.addAll(graph.get(0));

        while (!pq.isEmpty()) {
            extractionCount++;
            Edge edge = pq.poll();
            comparisonCount++;

            int vertex = edge.getVertex1();
            int otherVertex = edge.getVertex2();
            if (inMST[otherVertex]) {
                continue;
            }
            mst.add(edge);
            totalWeight += edge.getWeight();
            inMST[otherVertex] = true;
            pq.addAll(graph.get(otherVertex));
        }
    }

    private Map<Integer, List<Edge>> buildGraph(List<Edge> edges, int numVertices) {
        Map<Integer, List<Edge>> graph = new HashMap<>();
        for (int i = 0; i < numVertices; i++) {
            graph.put(i, new ArrayList<>());
        }

        for (Edge edge : edges) {
            graph.get(edge.getVertex1()).add(edge);
            graph.get(edge.getVertex2()).add(new Edge(edge.getVertex2(), edge.getVertex1(), edge.getWeight()));
        }

        return graph;
    }

    public List<Edge> getMst() {
        return mst;
    }

    public double getTotalWeight() {
        return totalWeight;
    }

    // Метод для получения количества операций
    public long getOperationCount() {
        return comparisonCount + extractionCount;
    }
}
