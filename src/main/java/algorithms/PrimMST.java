package algorithms;

import java.util.*;

public class PrimMST {
    private List<Edge> mst;            // Список рёбер для MST
    private double totalWeight;        // Общий вес MST

    public PrimMST(List<Edge> edges, int numVertices) {
        mst = new ArrayList<>();
        totalWeight = 0;

        // Создание графа с использованием списка рёбер
        Map<Integer, List<Edge>> graph = buildGraph(edges, numVertices);

        // Приоритетная очередь для хранения рёбер с минимальным весом
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        boolean[] inMST = new boolean[numVertices]; // Массив для отслеживания посещённых вершин

        // Начинаем с первой вершины (0)
        inMST[0] = true;

        // Добавляем рёбра, исходящие из вершины 0
        pq.addAll(graph.get(0));

        // Алгоритм Прима
        while (!pq.isEmpty()) {
            Edge edge = pq.poll(); // Извлекаем ребро с минимальным весом

            int vertex = edge.getVertex1();  // Вершина, к которой подключаем
            int otherVertex = edge.getVertex2(); // Вершина, с которой мы соединяемся

            // Если вершина не в MST, добавляем её
            if (inMST[otherVertex]) {
                continue;  // Если вершина уже в MST, пропускаем
            }

            // Добавляем ребро в MST
            mst.add(edge);
            totalWeight += edge.getWeight();
            inMST[otherVertex] = true;

            // Добавляем все рёбра, исходящие от новой вершины, в очередь
            pq.addAll(graph.get(otherVertex));
        }
    }

    // Построение графа из рёбер
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
}
