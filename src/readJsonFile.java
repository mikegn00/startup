import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class readJsonFile {
    List<YugiohCardDetails> cardList;

    readJsonFile() {
        cardList = new ArrayList<>();
        JSONParser parser = new JSONParser();
        try{
            JSONArray obj = (JSONArray) parser.parse(new FileReader("cards.json"));
            for (Object o: obj){
                JSONObject card = (JSONObject) o;
                String name = (String) card.get("name");
                String desc = (String) card.get("desc");
                JSONArray array = (JSONArray) card.get("rarity");
                List<String> list = new ArrayList<>();
                Iterator<String> iterator = array.iterator();
                while (iterator.hasNext()){
                    cardList.add(new YugiohCardDetails(name, desc, iterator.next()));
                }
            }

        }catch (Exception e){

        }
    }
    List<YugiohCardDetails> getList(){
        return cardList;
    }
}
