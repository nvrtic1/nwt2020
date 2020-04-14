package com.example.Performers;

import com.example.Performers.models.News;
import com.example.Performers.models.Singer;
import com.example.Performers.models.SingerNews;
import com.example.Performers.repository.NewsRepository;
import com.example.Performers.repository.SingerRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@EnableDiscoveryClient
@SpringBootApplication
public class PerformersApplication implements CommandLineRunner {

	@Autowired
	private final SingerRepository singerRepository;

	@Autowired
	private NewsRepository newsRepository;

	public PerformersApplication(SingerRepository singerRepository, NewsRepository newsRepository){
		this.singerRepository = singerRepository;
		this.newsRepository = newsRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(PerformersApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// Cleanup the tables
		singerRepository.deleteAllInBatch();
		newsRepository.deleteAllInBatch();

		// =======================================

		// Create a Post

		/* TODO: popuni podatcima...
		Post post = new Post("Hibernate Many to Many Example with Spring Boot",
				"Learn how to map a many to many relationship using hibernate",
				"Entire Post content with Sample code");

		// Create two tags
		Tag tag1 = new Tag("Spring Boot");
		Tag tag2 = new Tag("Hibernate");


		// Add tag references in the post
		post.getTags().add(tag1);
		post.getTags().add(tag2);

		// Add post reference in the tags
		tag1.getPosts().add(post);
		tag2.getPosts().add(post);

		postRepository.save(post);

		// =======================================
		 */



		Singer singer1 = new Singer(1, "Aldin", "Heheh", new Date(95, 1, 10, 0, 0));
		Singer singer2 = new Singer(1, "Mirza", "Radi u Kliki heh", new Date(95, 1, 10, 0, 0));
		singerRepository.saveAll(Arrays.asList(singer1, singer2));

		News news1 = new News(2, "Naslov", "Tekst");
		News news2 = new News(3, "Drugi naslov", "Tekst clanka");

		newsRepository.save(news1);
		newsRepository.save(news2);



		ArrayList<SingerNews> singerNews1 = new ArrayList<>();
		singerNews1.add(new SingerNews(news1));
		singerNews1.add(new SingerNews(news2));

		ArrayList<SingerNews> singerNews2 = new ArrayList<>();
		singerNews1.add(new SingerNews(news2));



	}

	/*
	@Bean
	public RestTemplate restTemplate() {
		RestTemplate restTemplate = new RestTemplate();
		List<HttpMessageConverter<?>> converters = restTemplate.getMessageConverters();
		for (HttpMessageConverter<?> converter : converters) {
			if (converter instanceof MappingJackson2HttpMessageConverter) {
				MappingJackson2HttpMessageConverter jsonConverter = (MappingJackson2HttpMessageConverter) converter;
				jsonConverter.setObjectMapper(new ObjectMapper());
				jsonConverter.setSupportedMediaTypes(ImmutableList.of(new MediaType("application", "json", MappingJackson2HttpMessageConverter.DEFAULT_CHARSET), new MediaType("text", "javascript", MappingJackson2HttpMessageConverter.DEFAULT_CHARSET)));
			}
		}
		return restTemplate;
	}

	 */

}
