package tentang.tech.thread;

import org.junit.jupiter.api.Test;

public class RaceConditionTest {

  @Test
  void testRaceCondition() throws InterruptedException {
    var counter = new Counter();
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
}
