package vehbook.vehiclebooker.cache;

import lombok.*;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class Cache<K, V> {
    private final HashMap<K, V> map;

    public Cache() {
        map = new HashMap<>();
    }

    public V get(K key) {
        return map.get(key);
    }
    public void assign(K key, V value) {
        if(map.containsKey(key))
        {
            map.replace(key, value);
        }
        else
        {
            map.put(key, value);
        }
    }
    public void wipe(K key) {
        map.remove(key);
    }
}
