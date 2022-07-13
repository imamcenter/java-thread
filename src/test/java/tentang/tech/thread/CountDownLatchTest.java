package tentang.tech.thread;

import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CountDownLatchTest {
  @Test
  void test() throws InterruptedException {
    final var countDown = new CountDownLatch(5);
    final var executor = Executors.newFixedThreadPool(10);

    for (int i = 0; i < 5; i++) {
      executor.execute(()->{
        try {
          System.out.println("task start");
          Thread.sleep(1000);
          System.out.println("finish task");
        } catch (InterruptedException e) {
          e.printStackTrace();
        }finally {
          countDown.countDown();
        }
      });

    }
    executor.execute(()->{
      try {
        countDown.await();
        System.out.println("finish all task");
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    });

    executor.awaitTermination(1, TimeUnit.DAYS);
  }
}
