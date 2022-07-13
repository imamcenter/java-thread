package tentang.tech.thread;

import org.junit.jupiter.api.Test;

public class ThreadTest {


  @Test
  void testThreadMain() {
    String name = Thread.currentThread().getName();
    System.out.println(name);
  }

  @Test
  void testCreateThread() {
    Runnable runnable = () -> System.out.println("hello from thread: " + Thread.currentThread().getName());

    Thread thread = new Thread(runnable);
    thread.start();

    System.out.println("hello");
  }

  @Test
  void testThreadSleep() throws InterruptedException {
    Runnable runnable = () -> {
      try {
        Thread.sleep(2_000);
        System.out.println("hello from thread: " + Thread.currentThread().getName());
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    };

    Thread thread = new Thread(runnable);
    thread.start();

    System.out.println("hello");

    Thread.sleep(3_000);
  }

  @Test
  void testThreadJoin() throws InterruptedException {
    Runnable runnable = () -> {
      try {
        Thread.sleep(2_000);
        System.out.println("hello from thread: " + Thread.currentThread().getName());
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    };

    Thread thread = new Thread(runnable);
    thread.start();
    System.out.println("menunggu selesai");
    thread.join();
    System.out.println("program selesai");
  }

  @Test
  void testThreadInterruptWrong() throws InterruptedException {
    Runnable runnable = () -> {
      for (int i = 0; i < 10; i++) {
        System.out.println("Runnable: " + i);
        try {
          Thread.sleep(1_000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }

      }
    };

    Thread thread = new Thread(runnable);
    thread.start();
    Thread.sleep(5_000);
    thread.interrupt();
    System.out.println("menunggu selesai");
    thread.join();
    System.out.println("program selesai");
  }

  @Test
  void testThreadInterruptCorrect() throws InterruptedException {
    Runnable runnable = () -> {
      for (int i = 0; i < 10; i++) {
        // manual check interrupted
        if (Thread.interrupted()) {
          return;
        }
        System.out.println("Runnable: " + i);
        try {
          Thread.sleep(1_000);
        } catch (InterruptedException e) {
          return;
        }

      }
    };

    Thread thread = new Thread(runnable);
    thread.start();
    Thread.sleep(5_000L);
    thread.interrupt();
    System.out.println("menunggu selesai");
    thread.join();
    System.out.println("program selesai");
  }

  @Test
  void testThreadName() {
    Thread thread = new Thread(() -> System.out.println("Run in thread: " + Thread.currentThread().getName()));
    thread.setName("imam");
    thread.start();

  }

  @Test
  void testThreadState() throws InterruptedException {
    Thread thread = new Thread(() -> {
      System.out.println(Thread.currentThread().getState());
      System.out.println("Run in thread: " + Thread.currentThread().getName());
    });
    thread.setName("imam");
    System.out.println(thread.getState());
    thread.start();
    thread.join();
    System.out.println(thread.getState());

  }
}