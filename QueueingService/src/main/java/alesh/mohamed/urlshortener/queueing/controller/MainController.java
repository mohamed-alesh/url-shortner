package alesh.mohamed.urlshortener.queueing.controller;

import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import alesh.mohamed.urlshortener.queueing.model.URL;

@RestController
public class MainController {

	@Autowired
	KafkaTemplate<String, URL> kafkaTemplate;
	@Value("${new-requests-topic}")
	private String topic;

	@PostMapping("/shorten")
	public ResponseEntity<String> shorten(@RequestBody URL url) throws ExecutionException, InterruptedException {
		kafkaTemplate.send(topic, url).get();
		return new ResponseEntity<>(
			      "Your tiny url is being generated", HttpStatus.OK);
	}

}
