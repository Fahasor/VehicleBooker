package vehbook.vehiclebooker.cache;

import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class DriverCache<K, V> {
    private final HashMap<K, V> map;
    private final int maxCapacity;

    public DriverCache() {
        map = new HashMap<>();
        this.maxCapacity = 200;
    }

    public V get(K key) {
        return map.get(key);
    }
    public boolean assign(K key, V value) {
        if(map.size() >= maxCapacity)
            return false;

        if(map.containsKey(key))
        {
            map.replace(key, value);
        }
        else
        {
            map.put(key, value);
        }

        return true;
    }
    public void wipe(K key) {
        map.remove(key);
    }
}
