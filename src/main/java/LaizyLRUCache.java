import java.util.LinkedHashMap;
import java.util.Map;

public class LaizyLRUCache implements Cache{

    private final int CAP;
    private Map<Object, Object> map;

    public LaizyLRUCache(int capacity) {
        CAP = capacity;
        map = new LinkedHashMap<Object, Object>(capacity, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<Object, Object> eldest) {
                return size() > CAP;
            }
        };

    }

    public Object get(Object key) {
        return map.getOrDefault(key, -1);
    }

    public void set(Object key, Object value) {
        map.put(key, value);
    }

}
