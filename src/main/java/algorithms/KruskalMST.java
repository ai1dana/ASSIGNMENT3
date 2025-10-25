package algorithms;

import java.util.*;

public class KruskalMST {
    private List<Edge> mst = new ArrayList<>();
    private double totalWeight = 0;
    private long comparisonCount = 0;
    private long unionCount = 0;

    public KruskalMST(List<Edge> edges, int numVertices) {
        UnionFind uf = new UnionFind(numVertices);
        Collections.sort(edges, Comparator.comparingDouble(Edge::getWeight));

        // Подсчёт сравнений и объединений
        for (Edge edge : edges) {
            comparisonCount++;
            int vertex1 = edge.getVertex1();
            int vertex2 = edge.getVertex2();
            if (uf.find(vertex1) != uf.find(vertex2)) {
                uf.union(vertex1, vertex2);
                unionCount++;
                mst.add(edge);
                totalWeight += edge.getWeight();
            }
        }
    }

    public List<Edge> getMst() {
        return mst;
    }

    public double getTotalWeight() {
        return totalWeight;
    }

    // Метод для получения количества операций
    public long getOperationCount() {
        return comparisonCount + unionCount;
    }
}
