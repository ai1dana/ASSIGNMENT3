package algorithms;

import java.util.ArrayList;
import java.util.List;

public class EdgeWeightedGraph {
    private final int vertices;
    private final List<Edge> edges;

    public EdgeWeightedGraph(int vertices) {
        this.vertices = vertices;
        this.edges = new ArrayList<>();
    }

    // Добавление рёбер в граф
    public void addEdge(Edge edge) {
        edges.add(edge);
    }

    // Получение всех рёбер
    public List<Edge> getEdges() {
        return edges;
    }

    // Получение количества вершин
    public int getVertices() {
        return vertices;
    }
}
