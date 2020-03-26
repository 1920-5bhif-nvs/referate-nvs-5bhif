package at.htl;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

/**
 * Hello world!
 *
 */
public class Producer implements Runnable
{
  private String topic;

  public Producer(String topic) {
    this.topic = topic;
  }

	@Override
	public void run() {
      //Assign topicName to string variable
      String topicName = topic;
      
      // create instance for properties to access producer configs   
      Properties props = new Properties();
      
      //Assign localhost id
      props.put("bootstrap.servers", "localhost:9092");
      
      //Set acknowledgements for producer requests.      
      props.put("acks", "all");
      
      //If the request fails, the producer can automatically retry,
      props.put("retries", 0);
      
      //Specify buffer size in config
      props.put("batch.size", 16384);
      
      //Reduce the no of requests less than 0   
      props.put("linger.ms", 1);
      
      //The buffer.memory controls the total amount of memory available to the producer for buffering.   
      props.put("buffer.memory", 33554432);
      
      props.put("key.serializer", 
         "org.apache.kafka.common.serialization.StringSerializer");
         
      props.put("value.serializer", 
         "org.apache.kafka.common.serialization.StringSerializer");
      
      KafkaProducer<String, String> producer = new KafkaProducer
         <String, String>(props);

      System.out.println("Starting to send something");
            
      try {
        for(int i = 0; i < 10; i++){
           producer.send(
             new ProducerRecord<String, String>(
               topicName, 
               Integer.toString(i), 
               Integer.toString(i)
             ),
             (metadata, exception) -> {
               if(exception != null){
                 System.out.println(exception.getMessage());
               }
               else {
                  System.out.println("Message sent successfully");
               }
             }
           );
        }
      } catch (Exception ex) {
        System.out.println("what the fuck");
      }
	}

}
