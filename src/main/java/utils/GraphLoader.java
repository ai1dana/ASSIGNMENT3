package utils;



import algorithms.Edge;
import org.json.JSONArray;
import org.json.JSONObject;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class GraphLoader {
    public static List<Edge> loadGraph(int graphId) throws Exception {

        String content = new String(Files.readAllBytes(Paths.get("src/main/resources/ass_3_input.json")));
        JSONObject jsonObject = new JSONObject(content);
        JSONArray graphs = jsonObject.getJSONArray("graphs");

        for (int i = 0; i < graphs.length(); i++) {
            JSONObject graph = graphs.getJSONObject(i);
            if (graph.getInt("id") == graphId) {
                List<Edge> edges = new ArrayList<>();
                JSONArray edgesArray = graph.getJSONArray("edges");

                for (int j = 0; j < edgesArray.length(); j++) {
                    JSONObject edge = edgesArray.getJSONObject(j);
                    int from = Integer.parseInt(edge.getString("from"));
                    int to = Integer.parseInt(edge.getString("to"));
                    double weight = edge.getDouble("weight");
                    edges.add(new Edge(from, to, weight));
                }
                return edges;
            }
        }
        throw new Exception("Graph with ID " + graphId + " not found.");
    }
}
