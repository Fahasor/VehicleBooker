package vehbook.vehiclebooker.service;

import java.util.concurrent.atomic.AtomicInteger;
import org.springframework.stereotype.Service;

@Service
public class RequestCounterService {
  public RequestCounterService() { }
  private static final AtomicInteger COUNT = new AtomicInteger(0);

  public static synchronized int increment() {
    return COUNT.incrementAndGet();
  }
}
