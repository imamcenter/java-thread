package tentang.tech.thread;

/**
 * Hello world!
 */
public class DaemonApp {
  public static void main(String[] args) throws InterruptedException {
    Thread thread = new Thread(() -> {
      try {
        Thread.sleep(3_000);
        System.out.println("run thread");
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    });
thread.setDaemon(true);
    thread.start();
    thread.join();
  }
}
