package algorithms;

import java.util.*;

public class Graph {
    private final Map<Integer, List<Edge>> adjList;

    public Graph() {
        adjList = new HashMap<>();
    }

    public void addEdge(int u, int v, double weight) {
        adjList.putIfAbsent(u, new ArrayList<>());
        adjList.putIfAbsent(v, new ArrayList<>());

        adjList.get(u).add(new Edge(u, v, weight));
        adjList.get(v).add(new Edge(v, u, weight));
    }

    public List<Edge> getEdges() {
        List<Edge> edges = new ArrayList<>();
        for (List<Edge> list : adjList.values()) {
            edges.addAll(list);
        }
        return edges;
    }

    public List<Edge> getNeighbours(int vertex) {
        return adjList.getOrDefault(vertex, new ArrayList<>());
    }

    public Set<Integer> getVertices() {
        return adjList.keySet();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Integer, List<Edge>> entry : adjList.entrySet()) {
            sb.append(entry.getKey()).append(": ");
            for (Edge edge : entry.getValue()) {
                sb.append(edge.toString()).append(", ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
