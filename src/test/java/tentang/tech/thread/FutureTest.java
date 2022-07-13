package tentang.tech.thread;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FutureTest {

  @Test
  void testFuture() throws InterruptedException, ExecutionException, TimeoutException {
    var executor = Executors.newSingleThreadExecutor();

    Callable<String> callable = () -> {
      Thread.sleep(5000);
      return "hi";
    };

    Future<String> future = executor.submit(callable);
    System.out.println("Selesai");
//
//    while (!future.isDone()){
//      System.out.println("waiting future");
//      Thread.sleep(1000);
//    }

    System.out.println(future.get(2, TimeUnit.SECONDS));

    Thread.sleep(6000);
  }

  @Test
  void testCancel() throws InterruptedException, ExecutionException {
    var executor = Executors.newSingleThreadExecutor();

    Callable<String> callable = () -> {
      Thread.sleep(5000);
      return "hi";
    };

    Future<String> future = executor.submit(callable);
    System.out.println("Selesai");
    Thread.sleep(2000);
    future.cancel(true);

    System.out.println(future.get());
  }


  @Test
  void invokeAll() throws InterruptedException, ExecutionException {
    var executor = Executors.newFixedThreadPool(10);
    List<Callable<String>> callables = IntStream.range(1, 11).mapToObj(value -> (Callable<String>) () -> {
      Thread.sleep(value * 500L);
      return String.valueOf(value);
    }).collect(Collectors.toList());
    var futures = executor.invokeAll(callables);
    for (Future<String> future : futures) {
      System.out.println(future.get());
    }

  }

}
