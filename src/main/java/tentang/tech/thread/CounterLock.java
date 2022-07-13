package tentang.tech.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CounterLock {
  private Long value = 0L;
  private final Lock l = new ReentrantLock();

  public void increment() {
    try {
      l.lock();
      value++;
    } finally {
      l.unlock();
    }
  }

  public Long getCounter() {
    return value;
  }
}
