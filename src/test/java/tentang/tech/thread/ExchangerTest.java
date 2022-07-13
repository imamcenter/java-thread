package tentang.tech.thread;

import org.junit.jupiter.api.Test;

import java.util.concurrent.Exchanger;
import java.util.concurrent.Executors;

public class ExchangerTest {

  @Test
  void test() {
    var exchanger = new Exchanger<String>();
    var executor = Executors.newFixedThreadPool(10);

    executor.execute(() -> {
      try {

        System.out.println("Thread 1 send data: First");
        var result = exchanger.exchange("First");
        System.out.println("Thread 1 receive data : " + result);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    });

    executor.execute(() -> {
      try {
        System.out.println("Thread 2 send data: Second");
        var result = exchanger.exchange("Second");
        System.out.println("Thread 2 receive data : " + result);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    });
  }
}
