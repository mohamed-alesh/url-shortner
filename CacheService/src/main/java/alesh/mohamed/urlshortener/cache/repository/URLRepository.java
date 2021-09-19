package alesh.mohamed.urlshortener.cache.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import alesh.mohamed.urlshortener.cache.model.URL;


public interface URLRepository extends MongoRepository<URL, String>{	 
	 @Query("{hash:'?0'}")
	 URL findURLByHash(String hash);
}
