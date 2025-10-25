package utils;

import org.json.JSONArray;
import org.json.JSONObject;
import java.nio.file.Files;
import java.nio.file.Paths;

public class OutputLoader {
    public static JSONObject loadResult(int graphId) throws Exception {

        String content = new String(Files.readAllBytes(Paths.get("src/main/resources/ass_3_output.json")));
        JSONObject jsonObject = new JSONObject(content);
        JSONArray results = jsonObject.getJSONArray("results");

        for (int i = 0; i < results.length(); i++) {
            JSONObject result = results.getJSONObject(i);
            if (result.getInt("graph_id") == graphId) {
                return result;
            }
        }
        throw new Exception("Result for graph ID " + graphId + " not found.");
    }
}

