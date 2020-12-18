import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class getCardDetails {
    public static void main(String[] args) {
        try {
            saveToJson stj = new saveToJson();
            URL url = new URL("https://db.ygoprodeck.com/api/v7/cardinfo.php");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.addRequestProperty("User-Agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)");
            int resp = connection.getResponseCode();

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuffer sb = new StringBuffer();

            while ((line = in.readLine())!= null){
                sb.append(line);
            }
            in.close();
            JSONObject object = new JSONObject(sb.toString());

            JSONArray data = object.getJSONArray("data");
            for (int i = 0; i < data.length(); i++){
                JSONObject ob = data.getJSONObject(i);
                String name = (ob.get("name").toString());
                String desc = (ob.get("desc").toString());
                List<String> list = new ArrayList<>();
                try {
                    JSONArray obj = ob.getJSONArray("card_sets");
                    for (int j = 0; j < obj.length(); j++) {
                        list.add(obj.getJSONObject(j).get("set_code")+"_"+obj.getJSONObject(j).get("set_rarity").toString().replace(" ", "_"));

                    }
                    stj.addItems(name, desc, list);
                }catch (Exception e){}


            }
            stj.submit();
            System.out.println("done");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
