package utils;

import java.util.List;
import java.util.ArrayList;

import algorithms.Edge;
import algorithms.EdgeWeightedGraph;
import org.json.JSONArray;
import org.json.JSONObject;

import java.nio.file.Files;
import java.nio.file.Paths;


public class GraphLoader {

    // Чтение графа из JSON
    public static EdgeWeightedGraph loadGraph(int graphId) throws Exception {
        // Чтение содержимого JSON-файла
        String content = new String(Files.readAllBytes(Paths.get("src/main/resources/ass_3_input.json")));
        JSONObject jsonObject = new JSONObject(content);
        JSONArray graphs = jsonObject.getJSONArray("graphs");

        // Поиск графа по ID
        for (int i = 0; i < graphs.length(); i++) {
            JSONObject graph = graphs.getJSONObject(i);
            if (graph.getInt("id") == graphId) {
                // Создаём граф с нужным количеством вершин
                int vertices = graph.getJSONArray("nodes").length();
                EdgeWeightedGraph G = new EdgeWeightedGraph(vertices);

                // Добавляем рёбра в граф
                JSONArray edgesArray = graph.getJSONArray("edges");
                for (int j = 0; j < edgesArray.length(); j++) {
                    JSONObject edge = edgesArray.getJSONObject(j);
                    String from = edge.getString("from");
                    String to = edge.getString("to");
                    double weight = edge.getDouble("weight");

                    // Преобразуем строки в индексы для вершин
                    int fromVertex = Integer.parseInt(from); // например, "A" -> 0, "B" -> 1
                    int toVertex = Integer.parseInt(to);

                    G.addEdge(new Edge(fromVertex, toVertex, weight));
                }

                return G;
            }
        }
        throw new Exception("Graph with ID " + graphId + " not found.");
    }
}
