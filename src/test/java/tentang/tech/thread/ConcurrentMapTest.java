package tentang.tech.thread;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;

public class ConcurrentMapTest {
  @Test
  void concurrentMap() throws InterruptedException {
    final var countDownLatch = new CountDownLatch(100);
    final var map = new ConcurrentHashMap<Integer, String>();
    final var executor = Executors.newFixedThreadPool(100);

    for (int i = 0; i < 100; i++) {
      final var index = i;

      executor.execute(() -> {
        try {
          Thread.sleep(1000);
          map.putIfAbsent(index, "data" + index);
        } catch (InterruptedException e) {
          e.printStackTrace();
        } finally {
          countDownLatch.countDown();
        }
      });
    }

    executor.execute(() -> {
      try {
        countDownLatch.await();
        map.forEach((integer, s) -> System.out.println(integer + " : " + s));
      } catch (InterruptedException e) {
        e.printStackTrace();
      }

    });
    executor.awaitTermination(1, TimeUnit.DAYS);
  }

  @Test
  void testCollection(){
    List<String> list = List.of("imam", "ahmad", "fahrezi");

    var list1 = Collections.synchronizedList(list);
  }

}
