package vehbook.vehiclebooker.cache;

import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;

@Component
public class DriverCache<K extends Comparable<K>, V> {

  private final LinkedHashMap<K, V> map;
  private static final int maxCapacity = 200;

  public DriverCache() {
    map = new LinkedHashMap<>();
  }

  int getMaxCapacity() {
    return maxCapacity;
  }

  public V get(K key) {
    return map.get(key);
  }

  /**
   * Associate key param with value param. After success of this method you can manipulate value by
   * key. If no free space in cache the value with the greatest key will be wiped additionally to
   * make free space for assigning.
   *
   * @param key   Will be associated with value.
   * @param value Will be associated with key.
   */
  public void assign(K key, V value) {
    if (map.containsKey(key)) {
      map.replace(key, value);
    } else {
      if (map.size() >= maxCapacity) {
        map.remove(map.keySet().iterator().next());
      }
      map.put(key, value);
    }
  }

  public void wipe(K key) {
    map.remove(key);
  }
}
