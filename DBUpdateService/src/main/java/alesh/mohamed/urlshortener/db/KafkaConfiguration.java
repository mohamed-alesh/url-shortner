package alesh.mohamed.urlshortener.db;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.listener.ContainerProperties.AckMode;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import alesh.mohamed.urlshortener.db.model.URL;


@EnableKafka
@Configuration
public class KafkaConfiguration {

	@Value("${kafkaHosts}")
	private String kafkaHosts;

	@Bean
	public ConsumerFactory<String, URL> consumerFactory() {
		Map<String, Object> kafkaProps = new HashMap<String, Object>();
		kafkaProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaHosts);
		kafkaProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		kafkaProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
		kafkaProps.put(ConsumerConfig.GROUP_ID_CONFIG, "demo");
		
		JsonDeserializer<URL> deserializer = new JsonDeserializer<URL>(URL.class, false);
		//deserializer.addTrustedPackages("alesh.mohamed.urlshortener.*");
		
		
		return new DefaultKafkaConsumerFactory<String, URL>(kafkaProps, new StringDeserializer(), deserializer);
	}
	
	@Bean
    KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, URL>>
                        kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, URL> factory =
                                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        factory.setConcurrency(3);
        factory.getContainerProperties().setAckMode(AckMode.MANUAL_IMMEDIATE);
        factory.getContainerProperties().setPollTimeout(3000);
        return factory;
    }

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, URL> kafkaListener() {
		ConcurrentKafkaListenerContainerFactory<String, URL> factory = new ConcurrentKafkaListenerContainerFactory<String, URL>();
		factory.setConsumerFactory(consumerFactory());
		return factory;
	}

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
