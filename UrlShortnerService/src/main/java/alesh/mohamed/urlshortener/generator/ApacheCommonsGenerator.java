package alesh.mohamed.urlshortener.generator;

import org.apache.commons.text.CharacterPredicates;
import org.apache.commons.text.RandomStringGenerator;

public class ApacheCommonsGenerator implements GeneratorAdapter {

	private RandomStringGenerator generator;

	public ApacheCommonsGenerator() {
		this.generator = new RandomStringGenerator.Builder().withinRange('0', 'z')
				.filteredBy(CharacterPredicates.LETTERS, CharacterPredicates.DIGITS).build();

	}

	@Override
	public String generate(int length) {
		return generator.generate(length);
	}

}
