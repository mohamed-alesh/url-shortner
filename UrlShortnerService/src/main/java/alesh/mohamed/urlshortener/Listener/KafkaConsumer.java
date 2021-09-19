package alesh.mohamed.urlshortener.Listener;

import java.util.Calendar;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import alesh.mohamed.urlshortener.generator.GeneratorAdapter;
import alesh.mohamed.urlshortener.model.URL;

@Service
public class KafkaConsumer {

	@Autowired
	KafkaTemplate<String, URL> kafkaTemplate;

	@Value("${new-requests-topic}")
	private String topic;

	@Value("${processed-topic}")
	private String processedRequestsTopic;
	
	@Autowired
	private GeneratorAdapter generatorAdapter;

	@KafkaListener(topics = {
			"${new-requests-topic}" }, groupId = "demo", containerFactory = "kafkaListenerContainerFactory")
	public void shorten(@Payload URL url, Acknowledgment acknowledgment)
			throws ExecutionException, InterruptedException {
		//TODO Possibly I need to check if the generated hash/random exists in Mongo. If exists, then regenerate 
		url.setHash(generatorAdapter.generate(7));
		url.setDateCreated(Calendar.getInstance().getTime().toString()); // TODO use date formatter with proper standard
																			// formatting
		kafkaTemplate.send(processedRequestsTopic, url).get();
		System.out.println("====>processed:" + url);
		acknowledgment.acknowledge();
	}
}
