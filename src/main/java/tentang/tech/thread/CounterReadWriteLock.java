package tentang.tech.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class CounterReadWriteLock {
  private Long value = 0L;
  private final ReadWriteLock l = new ReentrantReadWriteLock();

  public void increment() {
    try {
      l.writeLock().lock();
      value++;
    } finally {
      l.writeLock().unlock();
    }

  }

  public Long getCounter() {
    try {
      l.readLock().lock();
      return value;
    } finally {
      l.readLock().unlock();
    }

  }
}
