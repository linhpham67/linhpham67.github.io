import java.util.*;

public class LRUCache<K, V> {
    private final int capacity;
    private HashMap<K, V> cache;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new LinkedHashMap<>(capacity, 0.75f, true);
    }

    public V get(K key) { //get the value of the key
        return cache.getOrDefault(key, null);
    }

    public void put(K key, V value) {
        if (cache.size() >= capacity) {
            K lruKey = null;
            for (K k : cache.keySet()) {
                lruKey = k;
                break;

            }
            if (lruKey != null) {
                cache.remove(lruKey);
            }
        }

        cache.put(key, value);
    }

    public static void main(String[] args) {
        LRUCache<Integer, String> lruCache = new LRUCache<>(5);

        lruCache.put(1, "One");
        lruCache.put(2, "Two");
        lruCache.put(3, "Three");
        lruCache.put(4, "four");
        lruCache.put(5, "five");

        System.out.println("Cache contents after initial insertion: " + lruCache.cache.keySet());

        lruCache.get(1); //2,3,4,5,1
        lruCache.get(2); //3,4,5,1,2
        lruCache.get(4); //3,5,1,2,4

        lruCache.put(0, "zero"); // 5,1,2,4,0

        System.out.println("After cache: " + lruCache.cache.keySet());
        System.out.println(lruCache.get(2));
    }

}
