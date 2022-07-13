package tentang.tech.thread;

import org.junit.jupiter.api.Test;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Executors;

public class CyclicBarrierTest {
  @Test
  void test() {
    final var cyclicBarrier = new CyclicBarrier(5);
    final var executor = Executors.newFixedThreadPool(10);

    for (int i = 0; i < 4; i++) {
      executor.execute(() -> {
        try {
          System.out.println("waiting");
          cyclicBarrier.await();
          System.out.println("done");
        } catch (InterruptedException | BrokenBarrierException e) {
          e.printStackTrace();
        }
      });
    }
    executor.shutdown();
  }

}
