package alesh.mohamed.urlshortener.db.listener;

import java.util.Calendar;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import alesh.mohamed.urlshortener.db.model.URL;
import alesh.mohamed.urlshortener.db.repository.URLRepository;


@Service
public class KafkaConsumer {

	@Autowired
	KafkaTemplate<String, URL> kafkaTemplate;
	
	@Autowired
	URLRepository urlRepository;

	@Value("${processed-topic}")
	private String processedRequestsTopic;
	

	@KafkaListener(topics = {
			"${processed-topic}" }, groupId = "demo", containerFactory = "kafkaListenerContainerFactory")
	public void shorten(@Payload URL url, Acknowledgment acknowledgment)
			throws ExecutionException, InterruptedException {
		urlRepository.save(url);
		System.out.println("Saved to DB");
		acknowledgment.acknowledge();
	}
}
