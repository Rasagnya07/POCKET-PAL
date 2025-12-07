//NeedsWantsClassifier
import java.util.*;

public class NeedsWantsClassifier {
    private final Map<String,String> defaults = new LinkedHashMap<>();
    public NeedsWantsClassifier() {
        defaults.put("food","Need");
        defaults.put("groceries","Need");
        defaults.put("rent","Need");
        defaults.put("travel","Need");
        defaults.put("books","Need");
        defaults.put("subscriptions","Want");
        defaults.put("entertainment","Want");
        defaults.put("shopping","Want");
    }
    public String classify(String category) {
        if (category==null) return "Want";
        String k = category.trim().toLowerCase();
        if (defaults.containsKey(k)) return defaults.get(k);
        String[] needKeys = {"rent","bill","tuition","grocer","pharm","med","fuel","repair"};
        for (String kw: needKeys) if (k.contains(kw)) return "Need";
        String[] wantKeys = {"shop","spa","cinema","movie","game","dress","party","cafe"};
        for (String kw: wantKeys) if (k.contains(kw)) return "Want";
        return "Want";
    }
    public void setDefault(String category, String type){
        defaults.put(category.trim().toLowerCase(), type);
    }
}
