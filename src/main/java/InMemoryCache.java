public class InMemoryCache {

    public Cache getInMemoryCache(int sizeCache, CacheType type) {
        switch (type) {
            case LRU:
                return new LRUCache(sizeCache);

            case LFU:
                return new LFUCache(sizeCache);

            default:
                return null;
        }
    }

}
