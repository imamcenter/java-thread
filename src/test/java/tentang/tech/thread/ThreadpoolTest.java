package tentang.tech.thread;

import org.junit.jupiter.api.Test;

import java.sql.Time;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadpoolTest {

  @Test
  void create() {
    var minThread = 10;
    var maxThread = 100;
    var alive = 1;
    var aliveTime = TimeUnit.MINUTES;
    var queue = new ArrayBlockingQueue<Runnable>(100);

    var threadPool = new ThreadPoolExecutor(minThread, maxThread, alive, aliveTime, queue);
  }

  @Test
  void executeRunnable() throws InterruptedException {
    var minThread = 10;
    var maxThread = 100;
    var alive = 1;
    var aliveTime = TimeUnit.MINUTES;
    var queue = new ArrayBlockingQueue<Runnable>(100);

    var executor = new ThreadPoolExecutor(minThread, maxThread, alive, aliveTime, queue);

    Runnable runnable = () -> {
      try {
        Thread.sleep(5000);
        System.out.println("Runnable from thread" +
            Thread.currentThread().getName());
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    };
    executor.execute(runnable);

//    Thread.sleep(6000);
  }

  @Test
  void shutdown() throws InterruptedException {
    var minThread = 10;
    
    var maxThread = 100;
    var alive = 1;
    var aliveTime = TimeUnit.MINUTES;
    var queue = new ArrayBlockingQueue<Runnable>(1000);

    var executor = new ThreadPoolExecutor(minThread, maxThread, alive, aliveTime, queue);

    for (int i = 0; i < 1000; i++) {
      final var task = i;
      Runnable runnable = () -> {
        try {
          Thread.sleep(1000);
          System.out.println("task " + task + " Runnable from thread" +
              Thread.currentThread().getName());
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
      };
      executor.execute(runnable);

    }
//    executor.shutdownNow();
    executor.awaitTermination(1, TimeUnit.DAYS);
  }

   @Test
  void rejected() throws InterruptedException {
    var minThread = 10;
    var maxThread = 20;
    var alive = 1;
    var aliveTime = TimeUnit.MINUTES;
    var queue = new ArrayBlockingQueue<Runnable>(10);
    var rejectedHandler = new LogRejectedExecutionHandler();

    var executor = new ThreadPoolExecutor(minThread, maxThread, alive, aliveTime, queue, rejectedHandler);

    for (int i = 0; i < 40; i++) {
      final var task = i;
      Runnable runnable = () -> {
        try {
          Thread.sleep(1000);
          System.out.println("task " + task + " Runnable from thread" +
              Thread.currentThread().getName());
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
      };
      executor.execute(runnable);
      System.out.println("selesai memasukan runnable" + i);

    }
     System.out.println("Menunggu");
//    executor.shutdownNow();
    executor.awaitTermination(1, TimeUnit.DAYS);
  }

  public static class LogRejectedExecutionHandler implements RejectedExecutionHandler {
    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
      System.out.println("task" +
          r + "is rejected");
    }
  }

}
