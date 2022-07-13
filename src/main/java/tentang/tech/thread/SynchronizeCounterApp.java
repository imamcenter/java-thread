package tentang.tech.thread;

public class SynchronizeCounterApp {

  private Long value = 0L;

  //  public synchronized void increment(){
//    value++;
//  }
  public void increment() {
    synchronized (this) {
      value++;
    }
  }

  public Long getCounter() {
    return value;
  }
}
