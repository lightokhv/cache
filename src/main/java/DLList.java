public class DLList {

    LFUNode head, tail;
    int size;

    DLList() {
        head = new LFUNode(null, null);
        tail = new LFUNode(null, null);
        head.next = tail;
        tail.prev = head;
    }

    void add(LFUNode node) {
        head.next.prev = node;
        node.next = head.next;
        node.prev = head;
        head.next = node;
        size++;
    }

    void remove(LFUNode node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
        size--;
    }

    LFUNode removeLast() {
        if (size > 0) {
            LFUNode node = tail.prev;
            remove(node);
            return node;
        } else return null;
    }
}
