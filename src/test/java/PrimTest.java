
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import algorithms.Edge;
import algorithms.PrimMST;
import java.util.*;

public class PrimTest {

    @Test
    public void testMSTCost() {
        List<Edge> edges = new ArrayList<>();
        edges.add(new Edge(0, 1, 4));
        edges.add(new Edge(0, 2, 3));
        edges.add(new Edge(1, 2, 1));
        edges.add(new Edge(1, 3, 2));
        edges.add(new Edge(2, 3, 4));
        edges.add(new Edge(3, 4, 5));

        PrimMST prim = new PrimMST(edges, 5);

        assertEquals(11, prim.getTotalWeight());
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

        PrimMST prim = new PrimMST(edges, 5);

        List<Edge> mst = prim.getMst();
        Set<Integer> connectedVertices = new HashSet<>();
        for (Edge edge : mst) {
            connectedVertices.add(edge.getVertex1());
            connectedVertices.add(edge.getVertex2());
        }

        assertEquals(5, connectedVertices.size(), "Graph is not connected");
    }

    @Test
    public void testNoCyclesInMST() {
        List<Edge> edges = new ArrayList<>();
        edges.add(new Edge(0, 1, 4));
        edges.add(new Edge(0, 2, 3));
        edges.add(new Edge(1, 2, 1));
        edges.add(new Edge(1, 3, 2));
        edges.add(new Edge(2, 3, 4));
        edges.add(new Edge(3, 4, 5));

        PrimMST prim = new PrimMST(edges, 5);
        List<Edge> mst = prim.getMst();
        Set<String> edgesInMST = new HashSet<>();
        for (Edge edge : mst) {
            String edgeStr = Math.min(edge.getVertex1(), edge.getVertex2()) + "-" + Math.max(edge.getVertex1(), edge.getVertex2());
            assertFalse(edgesInMST.contains(edgeStr), "Cycle detected: " + edgeStr);
            edgesInMST.add(edgeStr);
        }
    }
}

