package alesh.mohamed.urlshortener.ui.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import alesh.mohamed.urlshortener.ui.model.URL;

@Controller
public class MainController {

	@Autowired
	RestTemplate restTemplate;

	@RequestMapping(value = "/{shortUrl}", method = RequestMethod.GET)
	public void getURL(@PathVariable String shortUrl, HttpServletResponse httpServletResponse) {
		URL url = restTemplate.getForObject("http://localhost:8081/" + shortUrl, URL.class);
		httpServletResponse.setHeader("Location", url.getUrl());
		httpServletResponse.setStatus(302);
	}
	
	@RequestMapping(value = "/shorten/{url}", method = RequestMethod.GET)
	public String shortenURL(@PathVariable String url, Model model) {
		URL _url = new URL(url, null, false, null);
		ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:8083/shorten", _url, String.class);
		model.addAttribute("msg", response.getBody());
		return "home";
	}
	
	
    @GetMapping("/")
    public String homePage(Model model) {
        return "home";
    }
}
