package vehbook.vehiclebooker.cache;

import java.util.TreeMap;
import org.springframework.stereotype.Component;

@Component
public class DriverCache<K extends Comparable<K>, V> {
    private final TreeMap<K, V> map;
    private final static int maxCapacity = 200;

    public DriverCache() {
        map = new TreeMap<>();
    }

    public V get(K key) {
        return map.get(key);
    }

    public void assign(K key, V value) {
        if(map.containsKey(key)) {
            map.replace(key, value);
        }
        else {
            if(map.size() >= maxCapacity) {
                map.pollLastEntry();
            }
            map.put(key, value);
        }
    }

    public void wipe(K key) {
        map.remove(key);
    }
}
