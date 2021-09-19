package alesh.mohamed.urlshortener;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;

import java.nio.charset.Charset;
import java.util.Random;

import org.apache.kafka.clients.producer.MockProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;

import alesh.mohamed.urlshortener.Listener.KafkaConsumer;
import alesh.mohamed.urlshortener.generator.GeneratorAdapter;
import alesh.mohamed.urlshortener.model.URL;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext
@EmbeddedKafka(partitions = 1, brokerProperties = { "listeners=PLAINTEXT://localhost:9092", "port=9092" })
class UrlShortnerServiceTests {

	@Autowired
	private KafkaConsumer consumer;

	@Autowired
	private GeneratorAdapter generatorAdapter;

	@Test
	void contextLoads() {
	}

	@Test
	public void givenEmbeddedKafkaBroker_whenSendingtoSimpleProducer_thenMessageReceived() throws Exception {
//		MockProducer<String, URL> producer = new MockProducer<String, URL>();
//		ProducerRecord<String, URL> record = new ProducerRecord<String, URL>("test-topic", new URL("https://google.com", null, false, null));
//		producer.send(record);
	}

	@Test
	public void test_whenCallingGeneratorAdapter_thenResultLengthShouldMatch() throws Exception {
		assertEquals(7, generatorAdapter.generate(7).length());
	}

}
