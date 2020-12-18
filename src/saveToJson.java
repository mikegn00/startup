import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class saveToJson {
    JSONArray array;
    saveToJson(){
        array = new JSONArray();

    }
    void addItems(String name, String desc, List<String> rarity){
        JSONObject ob = new JSONObject();
        ob.put("name", name);
        ob.put("desc", desc);
        ob.put("rarity", rarity);
        JSONObject o = new JSONObject();

        array.add(ob);
    }
    void submit(){
        try {
            FileWriter file = new FileWriter("cards.json");
            file.write(array.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
