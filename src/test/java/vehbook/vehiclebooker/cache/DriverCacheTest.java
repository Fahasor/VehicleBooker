package vehbook.vehiclebooker.cache;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import vehbook.vehiclebooker.DriverCache;

class DriverCacheTest {

  @Test
  void testGetMaxCapacity() {
    vehbook.vehiclebooker.DriverCache<Integer, String> driverCache = new vehbook.vehiclebooker.DriverCache<>();
    assertEquals(200, driverCache.getMaxCapacity());
  }

  @Test
  void testGet() {
    vehbook.vehiclebooker.DriverCache<Integer, String> driverCache = new vehbook.vehiclebooker.DriverCache<>();
    driverCache.assign(1, "driver1");
    assertEquals("driver1", driverCache.get(1));
  }

  @Test
  void testGetNonExistingKey() {
    vehbook.vehiclebooker.DriverCache<Integer, String> driverCache = new vehbook.vehiclebooker.DriverCache<>();
    assertNull(driverCache.get(1));
  }

  @Test
  void testAssign() {
    vehbook.vehiclebooker.DriverCache<Integer, String> driverCache = new vehbook.vehiclebooker.DriverCache<>();
    driverCache.assign(1, "driver1");
    assertEquals("driver1", driverCache.get(1));
  }

  @Test
  void testAssignExistingKey() {
    vehbook.vehiclebooker.DriverCache<Integer, String> driverCache = new vehbook.vehiclebooker.DriverCache<>();
    driverCache.assign(1, "driver1");
    driverCache.assign(1, "driver2");
    assertEquals("driver2", driverCache.get(1));
  }

  @Test
  void testAssignOverCapacity() {
    vehbook.vehiclebooker.DriverCache<Integer, String> driverCache = new vehbook.vehiclebooker.DriverCache<>();
    for (int i = 0; i < 201; i++) {
      driverCache.assign(i, "driver" + i);
    }
    assertNull(driverCache.get(0));
  }

  @Test
  void testWipe() {
    vehbook.vehiclebooker.DriverCache<Integer, String> driverCache = new DriverCache<>();
    driverCache.assign(1, "driver1");
    driverCache.wipe(1);
    assertNull(driverCache.get(1));
  }
}