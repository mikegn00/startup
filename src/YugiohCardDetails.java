import java.util.List;

public class YugiohCardDetails {
    String name, desc;
    List<String> sets;
    int count;

    YugiohCardDetails(String name, String desc, List<String> rarity){
        this.name = name;
        this.desc = desc;
        this.sets = rarity;
        count = 0;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public List<String> getSet() {
        return sets;
    }

    public void plus(){
        count++;
    }

    public int getCount() {
        return count;
    }
}
