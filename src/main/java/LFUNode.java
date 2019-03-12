public class LFUNode {

    Object key;
    Object value;
    Integer frequency;

    LFUNode prev, next;

    public LFUNode(Object key, Object value) {
        this.key = key;
        this.value = value;
        this.frequency = 1;
    }
}
