package tentang.tech.thread;

import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;

public class SemaphoreTest {

  @Test
  void test() throws InterruptedException {
    Semaphore semaphore = new Semaphore(10);
    var executor = Executors.newFixedThreadPool(100);

    for (int i = 0; i < 1000; i++) {
      executor.execute(() -> {
        try {
          semaphore.acquire();
          Thread.sleep(1000);
          System.out.println("finish");
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        } finally {
          semaphore.release();
        }
      });

    }

    executor.awaitTermination(1, TimeUnit.DAYS);
  }

}
