
import org.junit.Assert;
import org.junit.Test;



import java.util.Arrays;



public class TestCacheStrategy {

    InMemoryCache memoryCache = new InMemoryCache();

    public static void print(Object ... args){
        Arrays.stream(args).forEach(System.out::println);
    }

    @Test
    public void testCacheStrategyLRU() {

        Cache lruCache = memoryCache.getInMemoryCache(3, CacheType.LRU);

        lruCache.set("one","string 1");
        lruCache.set("two","string 2");
        Assert.assertEquals(lruCache.get("one"), "string 1");
        Assert.assertEquals(lruCache.get("one"), "string 1");
        Assert.assertEquals(lruCache.get("one"), "string 1");
        lruCache.set("four","string 4");

        lruCache.set("3","string 3");

        Assert.assertEquals(lruCache.get("one"), "string 1");
        Assert.assertEquals(lruCache.get("two"), -1);
        lruCache.set("four","new string 4");
        Assert.assertEquals(lruCache.get("four"), "new string 4");
        lruCache.set("five", "555");
        Assert.assertEquals(lruCache.get("3"), -1);
        lruCache.set("six", "new 6");
        Assert.assertEquals(lruCache.get("one"), -1);
    }

    @Test
    public void testCacheStrategyLFU(){
        Cache lfuCache = memoryCache.getInMemoryCache(3, CacheType.LFU);

        lfuCache.set("one","string 1");
        lfuCache.set("two","string 2");
        Assert.assertEquals(lfuCache.get("one"), "string 1");
        Assert.assertEquals(lfuCache.get("one"), "string 1");
        Assert.assertEquals(lfuCache.get("one"), "string 1");
        lfuCache.set("four","string 4");

        lfuCache.set("3","string 3");

        Assert.assertEquals(lfuCache.get("one"), "string 1");
        Assert.assertEquals(lfuCache.get("two"), -1);
        lfuCache.set("four","new string 4");
        Assert.assertEquals(lfuCache.get("four"), "new string 4");
        lfuCache.set("five", "555");
        Assert.assertEquals(lfuCache.get("3"), -1);
        lfuCache.set("six", "new 6");
        Assert.assertEquals(lfuCache.get("one"), "string 1");
    }
}
