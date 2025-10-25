import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import algorithms.Edge;
import algorithms.KruskalMST;
import algorithms.PrimMST;
import java.util.*;

public class MstCorrectnessTest {

    @Test
    public void testMSTCostEquality() {
        List<Edge> edges = new ArrayList<>();
        edges.add(new Edge(0, 1, 4));
        edges.add(new Edge(0, 2, 3));
        edges.add(new Edge(1, 2, 1));
        edges.add(new Edge(1, 3, 2));
        edges.add(new Edge(2, 3, 4));
        edges.add(new Edge(3, 4, 5));

        KruskalMST kruskal = new KruskalMST(edges, 5);
        PrimMST prim = new PrimMST(edges, 5);

        // Стоимость MST должна быть одинаковой для обоих алгоритмов
        assertEquals(kruskal.getTotalWeight(), prim.getTotalWeight(), "MST costs are not equal.");
    }

    @Test
    public void testMSTEdgeCount() {
        List<Edge> edges = new ArrayList<>();
        edges.add(new Edge(0, 1, 4));
        edges.add(new Edge(0, 2, 3));
        edges.add(new Edge(1, 2, 1));
        edges.add(new Edge(1, 3, 2));
        edges.add(new Edge(2, 3, 4));
        edges.add(new Edge(3, 4, 5));

        KruskalMST kruskal = new KruskalMST(edges, 5);
        PrimMST prim = new PrimMST(edges, 5);

        assertEquals(4, kruskal.getMst().size(), "Incorrect number of edges in Kruskal's MST.");
        assertEquals(4, prim.getMst().size(), "Incorrect number of edges in Prim's MST.");
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

        KruskalMST kruskal = new KruskalMST(edges, 5);
        PrimMST prim = new PrimMST(edges, 5);

        checkNoCyclesInMST(kruskal.getMst());
        checkNoCyclesInMST(prim.getMst());
    }

    @Test
    public void testGraphConnectivity() {
        List<Edge> edges = new ArrayList<>();
        edges.add(new Edge(0, 1, 4));
        edges.add(new Edge(0, 2, 3));
        edges.add(new Edge(1, 2, 1));
        edges.add(new Edge(1, 3, 2));
        edges.add(new Edge(2, 3, 4));
        edges.add(new Edge(3, 4, 5));

        KruskalMST kruskal = new KruskalMST(edges, 5);
        PrimMST prim = new PrimMST(edges, 5);

        checkGraphConnectivity(kruskal.getMst());
        checkGraphConnectivity(prim.getMst());
    }

    private void checkNoCyclesInMST(List<Edge> mst) {
        Set<String> edgesInMST = new HashSet<>();
        for (Edge edge : mst) {
            String edgeStr = Math.min(edge.getVertex1(), edge.getVertex2()) + "-" + Math.max(edge.getVertex1(), edge.getVertex2());
            assertFalse(edgesInMST.contains(edgeStr), "Cycle detected: " + edgeStr);
            edgesInMST.add(edgeStr);
        }
    }


    private void checkGraphConnectivity(List<Edge> mst) {
        Set<Integer> connectedVertices = new HashSet<>();
        for (Edge edge : mst) {
            connectedVertices.add(edge.getVertex1());
            connectedVertices.add(edge.getVertex2());
        }

        assertEquals(5, connectedVertices.size(), "Graph is not connected.");
    }
}