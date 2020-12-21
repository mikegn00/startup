import java.util.List;

public class YugiohCardDetails {
    String name, desc, rarity;

    YugiohCardDetails(String name, String desc, String rarity){
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

    public String getRarity() {
        return rarity;
    }
}
