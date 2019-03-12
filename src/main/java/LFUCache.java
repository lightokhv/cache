import java.util.HashMap;
import java.util.Map;

public class LFUCache implements Cache{

    private final int maxCapacity;
    private int curSize;
    private int minFrequency;
    private Map<Object, LFUNode> cache;
    private Map<Object, DLList> frequencyMap;

    public LFUCache(int capacity) {
        this.maxCapacity = capacity;
        this.curSize = 0;
        this.minFrequency = 0;

        this.cache = new HashMap<>();
        this.frequencyMap = new HashMap<>();
    }

    public Object get(Object key) {
        LFUNode curNode = cache.get(key);
        if (curNode == null) {
            return -1;
        }
        updateNode(curNode);
        return curNode.value;
    }

    public void set(Object key, Object value) {
        // corner case: check cache capacity initialization
        if (maxCapacity == 0)
            return;

        LFUNode node;
        if (cache.containsKey(key)) {
            node = cache.get(key);
            node.value = value;
            updateNode(node);
        } else {
            curSize++;
            if (curSize > maxCapacity) {

                DLList minFreqList = frequencyMap.get(minFrequency);
                LFUNode deleteNode = minFreqList.removeLast();
                cache.remove(deleteNode.key);
                curSize--;
            }
            minFrequency = 1;
            node = new LFUNode(key, value);

            DLList curList = frequencyMap.getOrDefault(node.frequency, new DLList());
            curList.add(node);
            frequencyMap.put(node.frequency, curList);
            cache.put(key, node);
        }
    }

    public void updateNode(LFUNode curNode) {
        int curFreq = curNode.frequency;
        DLList curList = frequencyMap.get(curFreq);
        curList.remove(curNode);

        if (curFreq == minFrequency && curList.size == 0)
            minFrequency++;

        curNode.frequency++;
        DLList newList = frequencyMap.getOrDefault(curNode.frequency, new DLList());
        newList.add(curNode);
        frequencyMap.put(curNode.frequency, newList);
    }


}
