package at.htl;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeoutException;

/**
 * Hello world!
 *
 */
public class App 
{
  public static void main( final String[] args )
  {
    try {
      QueueConsumer consumer = new QueueConsumer("queue");
      Thread consumerThread = new Thread(consumer);
      consumerThread.start();
      
      Producer producer = new Producer("queue");
      
      for (int i = 0; i < 100000; i++) {
        HashMap<String, Integer> message = new HashMap<String, Integer>();
        message.put("message number", i);
        producer.sendMessage(message);
        System.out.println("Message Number "+ i +" sent.");
      }
    } catch (IOException e) {
      e.printStackTrace();
    } catch (TimeoutException e) {
      e.printStackTrace();
    }
  }
}
