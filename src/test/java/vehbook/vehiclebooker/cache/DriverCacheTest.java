package vehbook.vehiclebooker.cache;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class DriverCacheTest {

  @Test
  void testGetMaxCapacity() {
    DriverCache<Integer, String> driverCache = new DriverCache<>();
    assertEquals(200, driverCache.getMaxCapacity());
  }

  @Test
  void testGet() {
    DriverCache<Integer, String> driverCache = new DriverCache<>();
    driverCache.assign(1, "driver1");
    assertEquals("driver1", driverCache.get(1));
  }

  @Test
  void testGetNonExistingKey() {
    DriverCache<Integer, String> driverCache = new DriverCache<>();
    assertNull(driverCache.get(1));
  }

  @Test
  void testAssign() {
    DriverCache<Integer, String> driverCache = new DriverCache<>();
    driverCache.assign(1, "driver1");
    assertEquals("driver1", driverCache.get(1));
    // Вместо прямого обращения к map, используем get()
    assertTrue(driverCache.get(1) != null);
  }

  @Test
  void testAssignExistingKey() {
    DriverCache<Integer, String> driverCache = new DriverCache<>();
    driverCache.assign(1, "driver1");
    driverCache.assign(1, "driver2");
    assertEquals("driver2", driverCache.get(1));
  }

  @Test
  void testAssignOverCapacity() {
    DriverCache<Integer, String> driverCache = new DriverCache<>();
    for (int i = 0; i < 201; i++) {
      driverCache.assign(i, "driver" + i);
    }
    assertEquals(200, driverCache.get(200) != null ? 200 : 199);
    assertNull(driverCache.get(0));
  }

  @Test
  void testWipe() {
    DriverCache<Integer, String> driverCache = new DriverCache<>();
    driverCache.assign(1, "driver1");
    driverCache.wipe(1);
    assertNull(driverCache.get(1));
    assertNull(driverCache.get(1));
  }
}