package tentang.tech.thread;

import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.concurrent.*;

public class BlockingQueueTest {

  @Test
  void arrayBlockingQueue() throws InterruptedException {
    final var queue = new ArrayBlockingQueue<String>(5);
    final var executor = Executors.newFixedThreadPool(20);

    for (int i = 0; i < 10; i++) {
      executor.execute(() -> {
        try {
          queue.put("data");
          System.out.println("finish put data");
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      });
    }

    executor.execute(() -> {
      while (true) {
        try {
          Thread.sleep(2000);
          var data = queue.take();
          System.out.println("receive data : " + data);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    });
    executor.awaitTermination(1, TimeUnit.DAYS);
  }

  @Test
  void linkedBlockingQueue() throws InterruptedException {
    final var queue = new LinkedBlockingQueue<String>();
    final var executor = Executors.newFixedThreadPool(20);

    for (int i = 0; i < 10; i++) {
      executor.execute(() -> {
        try {
          queue.put("data");
          System.out.println("finish put data");
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      });
    }

    executor.execute(() -> {
      while (true) {
        try {
          Thread.sleep(2000);
          var data = queue.take();
          System.out.println("receive data : " + data);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    });
    executor.awaitTermination(1, TimeUnit.DAYS);
  }

  @Test
  void priorityBlockingQueue() throws InterruptedException {
    var queue = new PriorityBlockingQueue<Integer>(10, Comparator.reverseOrder());
    final var executor = Executors.newFixedThreadPool(20);

    for (int i = 0; i < 10; i++) {
      final var index = i;
      executor.execute(() -> {
        queue.put(index);
        System.out.println("finish put data");
      });
    }

    executor.execute(() -> {
      while (true) {
        try {
          Thread.sleep(2000);
          var data = queue.take();
          System.out.println("receive data : " + data);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    });
    executor.awaitTermination(1, TimeUnit.DAYS);
  }

  @Test
  void delayedQueue() throws InterruptedException {
    var queue = new DelayQueue<ScheduledFuture<String>>();
    final var executor = Executors.newScheduledThreadPool(10);

    for (int i = 0; i < 10; i++) {
      final var index = i;
      queue.put(executor.schedule(() -> "data" + index, i, TimeUnit.SECONDS
      ));


    }

    executor.execute(() -> {
      while (true) {
        try {
          var data = queue.take();
          System.out.println("receive data : " + data.get());
        } catch (InterruptedException | ExecutionException e) {
          e.printStackTrace();
        }
      }
    });
    executor.awaitTermination(1, TimeUnit.DAYS);
  }

  @Test
  void syncronousQueue() throws InterruptedException {
    var queue = new SynchronousQueue<String>();
    final var executor = Executors.newFixedThreadPool(20);

    for (int i = 0; i < 10; i++) {
      final var index = i;
      executor.execute(() -> {
        try {
          queue.put("data" + index);
          System.out.println("finish put data: " + index);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      });
    }

    executor.execute(() -> {
      while (true) {
        try {
          Thread.sleep(2000);
          var data = queue.take();
          System.out.println("receive data : " + data);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    });
    executor.awaitTermination(1, TimeUnit.DAYS);
  }


  @Test
  void blockingDeque() throws InterruptedException {
    var queue = new LinkedBlockingDeque<String>();
    final var executor = Executors.newFixedThreadPool(1);

    for (int i = 0; i < 10; i++) {
      final var index = i;
      executor.execute(() -> {
        try {
          queue.putFirst("data"+ index);
          System.out.println("finish put data" + index);
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
      });
    }

    executor.execute(() -> {
      while (true) {
        try {
          Thread.sleep(2000);
          var data = queue.takeFirst();
          System.out.println("receive data : " + data);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    });
    executor.awaitTermination(1, TimeUnit.DAYS);
  }
}
