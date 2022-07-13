package tentang.tech.thread;

import org.junit.jupiter.api.Test;

import java.util.concurrent.locks.ReentrantLock;

public class LockTest {
  @Test
  void lock() throws InterruptedException {
    var counter = new CounterLock();
    Runnable runnable = () -> {
      for (int i = 0; i < 1_000_000; i++) {
        counter.increment();
      }
    };
    var thread = new Thread(runnable);

    var thread1 = new Thread(runnable);
    var thread2 = new Thread(runnable);

    thread.start();
    thread1.start();
    thread2.start();

    thread.join();
    thread1.join();
    thread2.join();
    System.out.println(counter.getCounter());
  }

  @Test
  void readWriteLock() throws InterruptedException {
    var counter = new CounterReadWriteLock();
    Runnable runnable = () -> {
      for (int i = 0; i < 1_000_000; i++) {
        counter.increment();
      }
    };
    var thread = new Thread(runnable);

    var thread1 = new Thread(runnable);
    var thread2 = new Thread(runnable);

    thread.start();
    thread1.start();
    thread2.start();

    thread.join();
    thread1.join();
    thread2.join();
    System.out.println(counter.getCounter());
  }

  String message;


  @Test
  void condition() throws InterruptedException {
    var lock = new ReentrantLock();
    var condition = lock.newCondition();
    var thread1 = new Thread(() -> {
      try {
        lock.lock();
        condition.await();
        System.out.println(message);
      } catch (InterruptedException e) {
        e.printStackTrace();
      } finally {
        lock.unlock();
      }
    });
    var thread2 = new Thread(() -> {
      try {
        lock.lock();
        condition.await();
        System.out.println(message);
      } catch (InterruptedException e) {
        e.printStackTrace();
      } finally {
        lock.unlock();
      }
    });
    var thread3 = new Thread(() -> {
      try {
        lock.lock();
        Thread.sleep(2000);
        message = "imam";
//        condition.signal();
        condition.signalAll();
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      } finally {
        lock.unlock();
      }
    });

    thread1.start();
    thread2.start();
    thread3.start();
    thread1.join();
    thread2.join();
    thread3.join();

  }
}