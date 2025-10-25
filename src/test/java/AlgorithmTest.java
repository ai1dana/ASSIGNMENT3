import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import algorithms.KruskalMST;
import algorithms.PrimMST;
import algorithms.Edge;
import utils.GraphLoader;
import utils.OutputLoader;
import org.json.JSONObject;

import java.util.List;

public class AlgorithmTest {

    @Test
    public void testGraph1() throws Exception {
        List<Edge> edges = GraphLoader.loadGraph(1); // ID графа = 1

        KruskalMST kruskal = new KruskalMST(edges, 5);
        PrimMST prim = new PrimMST(edges, 5);

        JSONObject expectedResult = OutputLoader.loadResult(1);

        assertEquals(expectedResult.getJSONObject("kruskal").getDouble("total_cost"), kruskal.getTotalWeight(), "Kruskal's MST cost mismatch");
        assertEquals(expectedResult.getJSONObject("prim").getDouble("total_cost"), prim.getTotalWeight(), "Prim's MST cost mismatch");

        assertEquals(expectedResult.getJSONObject("kruskal").getInt("operations_count"), kruskal.getOperationCount(), "Kruskal's operation count mismatch");
        assertEquals(expectedResult.getJSONObject("prim").getInt("operations_count"), prim.getOperationCount(), "Prim's operation count mismatch");
    }

    @Test
    public void testGraph2() throws Exception {

        List<Edge> edges = GraphLoader.loadGraph(2); // ID графа = 2

        KruskalMST kruskal = new KruskalMST(edges, 4);
        PrimMST prim = new PrimMST(edges, 4);

        JSONObject expectedResult = OutputLoader.loadResult(2);

        assertEquals(expectedResult.getJSONObject("kruskal").getDouble("total_cost"), kruskal.getTotalWeight(), "Kruskal's MST cost mismatch");
        assertEquals(expectedResult.getJSONObject("prim").getDouble("total_cost"), prim.getTotalWeight(), "Prim's MST cost mismatch");

        assertEquals(expectedResult.getJSONObject("kruskal").getInt("operations_count"), kruskal.getOperationCount(), "Kruskal's operation count mismatch");
        assertEquals(expectedResult.getJSONObject("prim").getInt("operations_count"), prim.getOperationCount(), "Prim's operation count mismatch");
    }
}

