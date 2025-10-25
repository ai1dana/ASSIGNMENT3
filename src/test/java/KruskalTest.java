import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import algorithms.Edge;
import algorithms.KruskalMST;
import java.util.*;

public class KruskalTest {

    @Test
    public void testMSTCost() {
        List<Edge> edges = new ArrayList<>();
        edges.add(new Edge(0, 1, 4));  // Вес 4
        edges.add(new Edge(0, 2, 3));  // Вес 3
        edges.add(new Edge(1, 2, 1));  // Вес 1
        edges.add(new Edge(1, 3, 2));  // Вес 2
        edges.add(new Edge(2, 3, 4));  // Вес 4
        edges.add(new Edge(3, 4, 5));  // Вес 5

        KruskalMST kruskal = new KruskalMST(edges, 5);

        // Ожидаем, что стоимость MST будет 10.0 (1 + 2 + 3 + 4)
        assertEquals(10.0, kruskal.getTotalWeight(), 0.01, "MST cost is not correct.");
    }


    @Test
    public void testMSTEdgeCount() {
        // Пример рёбер для графа
        List<Edge> edges = new ArrayList<>();
        edges.add(new Edge(0, 1, 4));
        edges.add(new Edge(0, 2, 3));
        edges.add(new Edge(1, 2, 1));
        edges.add(new Edge(1, 3, 2));
        edges.add(new Edge(2, 3, 4));
        edges.add(new Edge(3, 4, 5));

        KruskalMST kruskal = new KruskalMST(edges, 5);

        // В MST должно быть V-1 рёбер, где V - количество вершин
        assertEquals(4, kruskal.getMst().size(), "Incorrect number of edges in Kruskal's MST.");
    }

    @Test
    public void testNoCyclesInMST() {
        // Пример рёбер для графа
        List<Edge> edges = new ArrayList<>();
        edges.add(new Edge(0, 1, 4));
        edges.add(new Edge(0, 2, 3));
        edges.add(new Edge(1, 2, 1));
        edges.add(new Edge(1, 3, 2));
        edges.add(new Edge(2, 3, 4));
        edges.add(new Edge(3, 4, 5));

        KruskalMST kruskal = new KruskalMST(edges, 5);
        List<Edge> mst = kruskal.getMst();

        // Проверяем, что все рёбра из MST не образуют циклов
        Set<String> edgesInMST = new HashSet<>();
        for (Edge edge : mst) {
            String edgeStr = Math.min(edge.getVertex1(), edge.getVertex2()) + "-" + Math.max(edge.getVertex1(), edge.getVertex2());
            assertFalse(edgesInMST.contains(edgeStr), "Cycle detected: " + edgeStr);
            edgesInMST.add(edgeStr);
        }
    }

    @Test
    public void testGraphConnectivity() {
        // Пример рёбер для графа
        List<Edge> edges = new ArrayList<>();
        edges.add(new Edge(0, 1, 4));
        edges.add(new Edge(0, 2, 3));
        edges.add(new Edge(1, 2, 1));
        edges.add(new Edge(1, 3, 2));
        edges.add(new Edge(2, 3, 4));
        edges.add(new Edge(3, 4, 5));

        KruskalMST kruskal = new KruskalMST(edges, 5);

        // Проверка связности графа — MST должна быть связанной
        List<Edge> mst = kruskal.getMst();
        Set<Integer> connectedVertices = new HashSet<>();
        for (Edge edge : mst) {
            connectedVertices.add(edge.getVertex1());
            connectedVertices.add(edge.getVertex2());
        }

        // Все вершины должны быть подключены
        assertEquals(5, connectedVertices.size(), "Graph is not connected.");
    }
}

