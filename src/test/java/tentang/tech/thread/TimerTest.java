package tentang.tech.thread;

import org.junit.jupiter.api.Test;

import java.util.Timer;
import java.util.TimerTask;

public class TimerTest {

  @Test
  void delayedJob() throws InterruptedException {
    var task = new TimerTask() {
      @Override
      public void run() {
        System.out.println("delayed job");
      }
    };

    var timer = new Timer();
    timer.schedule(task, 2000L);
    Thread.sleep(3000L);
  }

  @Test
  void periodicJob() throws InterruptedException {
    var task = new TimerTask() {
      @Override
      public void run() {
        System.out.println("delayed job");
      }
    };

    var timer = new Timer();
    timer.schedule(task, 2000L, 2000L);
    Thread.sleep(10_000L);
  }

}
