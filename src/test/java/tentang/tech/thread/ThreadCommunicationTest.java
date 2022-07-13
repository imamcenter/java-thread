package tentang.tech.thread;

import org.junit.jupiter.api.Test;

public class ThreadCommunicationTest {

  String message;

  @Test
  void manual() throws InterruptedException {
    Thread thread1 = new Thread(() -> {
      while (message == null) {

      }
      System.out.println("message");
    });

    Thread thread2 = new Thread(() -> {
      message = "imam ahmad fahrezi";
    });

    thread2.start();
    thread1.start();
    thread2.join();
    thread1.join();
  }

  @Test
  void waitNotify() throws InterruptedException {
    final var o = new Object();
    Thread thread1 = new Thread(() -> {
      synchronized (o) {
        try {
          o.wait();
          System.out.println(message);
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
      }
    });

    Thread thread2 = new Thread(() -> {

      synchronized (o) {
        message = "imam ahmad fahrezi";
        o.notify();
      }
    });

    thread1.start();
    thread2.start();
    thread2.join();
    thread1.join();
  }

  @Test
  void waitNotifyAll() throws InterruptedException {
    final var o = new Object();
    Thread thread1 = new Thread(() -> {
      synchronized (o) {
        try {
          o.wait();
          System.out.println(message);
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
      }
    });
    Thread thread2 = new Thread(() -> {
      synchronized (o) {
        try {
          o.wait();
          System.out.println(message);
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
      }
    });

    Thread thread3 = new Thread(() -> {

      synchronized (o) {
        message = "imam ahmad fahrezi";
        o.notifyAll();
      }
    });

    thread1.start();
    thread2.start();
    thread3.start();

    thread3.join();
    thread2.join();
    thread1.join();
  }
}
