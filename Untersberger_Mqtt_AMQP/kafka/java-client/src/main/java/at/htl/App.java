package at.htl;

/**
 * Hello world!
 *
 */
public class App 
{
  public static void main( final String[] args )
  {
    Consumer consumer = new Consumer("test");
    Producer producer = new Producer("test");

    Thread consumerThread = new Thread(consumer);
    Thread producerThread = new Thread(producer);

    consumerThread.start();
    producerThread.start();
  }
}
