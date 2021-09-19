package alesh.mohamed.urlshortener.generator;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GenerationConfig {
	
	@Bean
	public GeneratorAdapter generatorAdapter() {
		return new ApacheCommonsGenerator();
	}
}
