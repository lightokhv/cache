import java.util.HashMap;
import java.util.Map;

public class LRUCache implements Cache {

    private int maxCapacity;
    private int totalItemsCache;
    private Map<Object, LRUNode> map;
    private LRUNode head;
    private LRUNode tail;

    public LRUCache(int capacity) {
        this.maxCapacity = capacity;
        this.totalItemsCache = 0;
        map = new HashMap<>();
        head = new LRUNode();
        tail = new LRUNode();
        head.next = tail;
        tail.prev = head;
    }

    private void update(LRUNode node) {
        remove(node);
        add(node);
    }

    private void add(LRUNode node) {
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }

    private void remove(LRUNode node) {
        LRUNode before = node.prev;
        LRUNode after = node.next;
        before.next = after;
        after.prev = before;
    }

    public void set(Object key, Object value) {
        LRUNode node = map.get(key);
        if (null == node) {
            node = new LRUNode(key, value);
            map.put(key, node);
            add(node);
            ++totalItemsCache;
        } else {
            node.value = value;
            update(node);
        }
        if (totalItemsCache > maxCapacity) {
            LRUNode nodeToDel = tail.prev;
            remove(nodeToDel);
            map.remove(nodeToDel.key);
            --totalItemsCache;
        }
    }

    public Object get(Object key) {
        LRUNode node = map.get(key);
        if (node == null)
            return -1;
        update(node);

        return node.value;
    }
}
