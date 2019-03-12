public class LRUNode {
    Object key;
    Object value;

    LRUNode prev,next;

    public LRUNode(Object key, Object value) {
        this.key = key;
        this.value = value;
    }
    LRUNode(){
        this(null,null);
    }
}
