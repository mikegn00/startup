import java.util.List;

public class YugiohCardDetails {
    String name, desc;
    List<String> rarity;

    YugiohCardDetails(String name, String desc, List<String> rarity){
        this.name = name;
        this.desc = desc;
        this.rarity = rarity;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public List<String> getRarity() {
        return rarity;
    }
}
