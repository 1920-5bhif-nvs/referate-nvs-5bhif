package at.htl;

import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

/**
 * Hello world!
 *
 */
public class Consumer implements Runnable
{
  private String topic;

  public Consumer(String topic) {
    this.topic = topic;
  }

	@Override
	public void run() {
      //Assign topicName to string variable
      String topicName = topic;

      Properties props = new Properties();
      
      props.put("bootstrap.servers", "localhost:9092");
      props.put("group.id", "test");
      props.put("enable.auto.commit", "true");
      props.put("auto.commit.interval.ms", "1000");
      props.put("session.timeout.ms", "30000");
      props.put("key.deserializer", 
         "org.apache.kafka.common.serialization.StringDeserializer");
      props.put("value.deserializer", 
         "org.apache.kafka.common.serialization.StringDeserializer");
      KafkaConsumer<String, String> consumer = new KafkaConsumer
         <String, String>(props);
      
      //Kafka Consumer subscribes list of topics here.
      consumer.subscribe(Arrays.asList(topicName));
      
      //print the topic name
      System.out.println("Subscribed to topic " + topicName);

      int i = 0;
      
      while (true) {
         ConsumerRecords<String, String> records = consumer.poll(100);

         for (ConsumerRecord<String, String> record : records)
           System.out.printf("offset = %d, key = %s, value = %s\n", 
              record.offset(), record.key(), record.value());
      }
  }
}
