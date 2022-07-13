package tentang.tech.thread;

import org.junit.jupiter.api.Test;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExecutorServiceTest {
  @Test
  void testSingleExecutorService() throws InterruptedException {
    var executor = Executors.newSingleThreadExecutor();
    for (int i = 0; i < 100; i++) {

      executor.execute(() -> {
        try {
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
        System.out.println("Runnable is thread:" + Thread.currentThread().getName());
      });

    }
    executor.awaitTermination(1, TimeUnit.DAYS);
  }

  @Test
  void testExecutorServiceFix() throws InterruptedException {
    var executor = Executors.newFixedThreadPool(10);
    for (int i = 0; i < 100; i++) {

      executor.execute(() -> {
        try {
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
        System.out.println("Runnable is thread:" + Thread.currentThread().getName());
      });

    }
    executor.shutdown();
    executor.awaitTermination(1, TimeUnit.DAYS);
  }


}
