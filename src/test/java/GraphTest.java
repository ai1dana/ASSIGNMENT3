
import algorithms.KruskalMST;
import algorithms.PrimMST;
import algorithms.EdgeWeightedGraph;
import utils.GraphLoader;
import utils.OutputLoader;
import org.junit.jupiter.api.Test;
import org.json.JSONObject;

import static org.junit.jupiter.api.Assertions.*;

public class GraphTest {

    @Test
    public void testGraph1() throws Exception {
        // Загружаем данные графа 1 из JSON
        EdgeWeightedGraph graph = GraphLoader.loadGraph(1); // ID графа = 1

        // Запускаем алгоритмы
        KruskalMST kruskal = new KruskalMST(graph.getEdges(), graph.getVertices());
        PrimMST prim = new PrimMST(graph.getEdges(), graph.getVertices());

        // Загружаем ожидаемый результат из output.json
        JSONObject expectedResult = OutputLoader.loadResult(1);

        // Сравниваем стоимость MST
        assertEquals(expectedResult.getJSONObject("kruskal").getDouble("total_cost"), kruskal.getTotalWeight(), "Kruskal's MST cost mismatch");
        assertEquals(expectedResult.getJSONObject("prim").getDouble("total_cost"), prim.getTotalWeight(), "Prim's MST cost mismatch");

        // Проверяем количество операций
        assertEquals(expectedResult.getJSONObject("kruskal").getInt("operations_count"), kruskal.getOperationCount(), "Kruskal's operation count mismatch");
        assertEquals(expectedResult.getJSONObject("prim").getInt("operations_count"), prim.getOperationCount(), "Prim's operation count mismatch");
    }

    // Добавьте другие тесты для проверки различных графов
}


