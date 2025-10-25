package algorithms;

import java.util.*;

public class KruskalMST {
    private List<Edge> mst = new ArrayList<>();
    private double totalWeight = 0;

    public KruskalMST(List<Edge> edges, int numVertices) {
        UnionFind uf = new UnionFind(numVertices);

        // Сортировка рёбер по весу
        Collections.sort(edges, Comparator.comparingDouble(Edge::getWeight));

        // Обрабатываем рёбра в порядке возрастания веса
        for (Edge edge : edges) {
            int vertex1 = edge.getVertex1();
            int vertex2 = edge.getVertex2();

            // Если рёбра принадлежат разным компонентам, добавляем их в MST
            if (uf.find(vertex1) != uf.find(vertex2)) {
                uf.union(vertex1, vertex2);
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
}
