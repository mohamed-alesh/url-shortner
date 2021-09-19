package alesh.mohamed.urlshortener.cache.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import alesh.mohamed.urlshortener.cache.model.URL;
import alesh.mohamed.urlshortener.cache.repository.URLRepository;

@RestController
public class MainController {
	
	@Autowired
	URLRepository urlRepository;
	
	
	@Cacheable(value = "urls", key = "#hash")
	@RequestMapping(value = "/{shortUrl}", method = RequestMethod.GET)
	public URL getURL(@PathVariable String shortUrl) {
	  return urlRepository.findURLByHash(shortUrl);
	}

}
