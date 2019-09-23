//import util.properties packages
import java.util.Properties;
import java.util.Random;

//import simple producer packages
import org.apache.kafka.clients.producer.Producer;

//import KafkaProducer packages
import org.apache.kafka.clients.producer.KafkaProducer;

//import ProducerRecord packages
import org.apache.kafka.clients.producer.ProducerRecord;

public class SimpleProducer {
    private static final String topic = "Hello-Kafka";
    public void SimpleProducer(){

    }
    public static void main(String[] args) {

        Properties props = new Properties();
        //Assign localhost id
        props.put("bootstrap.servers", "192.168.0.35:9092");

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

        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        Producer<String, String> producer = new KafkaProducer
                <String, String>(props);

        for(int i = 0; i < 10; i++)
            producer.send(new ProducerRecord<String, String>(topic, new Random().nextInt(2),
                    Integer.toString(i), Integer.toString(i)));
        System.out.println("Message sent successfull");
        producer.close();
    }


}
