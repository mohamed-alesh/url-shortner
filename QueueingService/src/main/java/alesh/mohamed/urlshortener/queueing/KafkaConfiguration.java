package alesh.mohamed.urlshortener.queueing;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import alesh.mohamed.urlshortener.queueing.model.URL;


@Configuration
public class KafkaConfiguration {

	@Value("${kafkaHosts}")
	private String kafkaHosts;

	@Bean
	public ProducerFactory<String, URL> createProducerFactory(){
		Map<String, Object> kafkaProps = new HashMap<String, Object>();

		kafkaProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaHosts);
		kafkaProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		kafkaProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
		kafkaProps.put(ProducerConfig.ACKS_CONFIG, "1");
		kafkaProps.put(ProducerConfig.RETRIES_CONFIG, "1");
		kafkaProps.put(ProducerConfig.LINGER_MS_CONFIG, 5);
		return new DefaultKafkaProducerFactory<String, URL>(kafkaProps, new StringSerializer(), new JsonSerializer<URL>());
	}
	
	@Bean
	public KafkaTemplate<String, URL> kafkaTemplate(){
		return new KafkaTemplate<String, URL>(createProducerFactory());
	}
	
}
