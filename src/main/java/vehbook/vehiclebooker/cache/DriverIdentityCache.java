package vehbook.vehiclebooker.cache;

import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class DriverIdentityCache<K, V> {

  private final HashMap<K, V> map;
  private final int maxCapacity;

  public DriverIdentityCache() {
    map = new HashMap<>();
    this.maxCapacity = 200;
  }

  public V get(K key) {
    return map.get(key);
  }

  /**
   *
   * @param key by that value you can wipe or get value contents.
   * @param value value that now will be associated with key param.
   * @return true if assigning is successful, or false otherwise.
   *     In case of returning false assign process will be canceled.
   */
  public boolean assign(K key, V value) {
    if (map.size() >= maxCapacity) {
      return false;
    }

    if (map.containsKey(key)) {
      map.replace(key, value);
    } else {
      map.put(key, value);
    }

    return true;
  }

  public void wipe(K key) {
    map.remove(key);
  }
}
