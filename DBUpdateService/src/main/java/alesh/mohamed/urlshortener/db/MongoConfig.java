package alesh.mohamed.urlshortener.db;

import java.util.Collection;
import java.util.Collections;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

@Configuration
@EnableMongoRepositories(basePackages = "alesh.mohamed.urlshortener.db")

public class MongoConfig extends AbstractMongoClientConfiguration{

	@Override
	protected String getDatabaseName() {
		// TODO Auto-generated method stub
		return "demo";
	}
	
	 @Override
	    public MongoClient mongoClient() {
	        ConnectionString connectionString = new ConnectionString("mongodb://root:rootpassword@localhost:27017/demo");
	        MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
	            .applyConnectionString(connectionString)
	            .build();
	        
	        return MongoClients.create(mongoClientSettings);
	    }
	 
	    @Override
	    public Collection getMappingBasePackages() {
	        return Collections.singleton("alesh.mohamed.urlshortener.db");
	    }

}
