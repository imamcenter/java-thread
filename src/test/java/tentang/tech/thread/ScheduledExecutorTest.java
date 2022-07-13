package tentang.tech.thread;

import org.junit.jupiter.api.Test;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutorTest {

  @Test
  void testDelayedJob() throws InterruptedException {
    var executor = Executors.newScheduledThreadPool(10);
    ScheduledFuture<?> scheduledFuture = executor.schedule(() -> System.out.println("hello"), 5, TimeUnit.SECONDS);

    System.out.println(scheduledFuture.getDelay(TimeUnit.MILLISECONDS));

    executor.awaitTermination(1, TimeUnit.DAYS);
  }

  @Test
  void periodicJob() throws InterruptedException {
    var executor = Executors.newScheduledThreadPool(10);
    ScheduledFuture<?> scheduledFuture = executor.scheduleAtFixedRate(() -> System.out.println("hello"),2,2, TimeUnit.SECONDS);

    System.out.println(scheduledFuture.getDelay(TimeUnit.MILLISECONDS));

    executor.awaitTermination(1, TimeUnit.DAYS);
  }
}
