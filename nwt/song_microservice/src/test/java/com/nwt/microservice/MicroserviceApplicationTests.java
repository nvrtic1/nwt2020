package com.nwt.microservice;

import com.nwt.microservice.model.*;
import com.nwt.microservice.repository.AlbumRepository;
import com.nwt.microservice.repository.GenreRepository;
import com.nwt.microservice.repository.SongRepository;
import com.nwt.microservice.service.SongServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MicroserviceApplicationTests {

	private final SongRepository songRepository;
	private final AlbumRepository albumRepository;
	private final GenreRepository genreRepository;

	public MicroserviceApplicationTests(SongRepository songRepository, AlbumRepository albumRepository, GenreRepository genreRepository) {
		this.songRepository = songRepository;
		this.albumRepository = albumRepository;
		this.genreRepository = genreRepository;
	}

	@Test
	public void contextLoads() {
	}


	@Autowired
	private SongServiceImpl service;

	@MockBean
	private SongRepository repository;

	@Test
	public void getSongTest() {
		// Create a couple of Film, Actor and FilmActor
		Album albumA = new Album("Miljacka", "Halidov novi album", new Date(95, 3, 11, 0, 0));
		Album albumB = new Album("OD ljubavi jace", "Album od Nedeljka novi 2020", new Date(95, 1, 10, 0, 0));
		albumRepository.saveAll(Arrays.asList(albumA, albumB));

		Genre genreA = new Genre("Narodna");
		Genre genreB = new Genre("Sevdalinka");
		genreRepository.saveAll(Arrays.asList(genreA, genreB));

		ArrayList<SongAlbum> bp1 = new ArrayList<>();

		bp1.add(new SongAlbum(albumA));
		bp1.add(new SongAlbum(albumB));

		ArrayList<SongAlbum> bp2 = new ArrayList<>();
		bp2.add(new SongAlbum(albumA));


		ArrayList<SongGenre> bg1 = new ArrayList<>();
		bg1.add(new SongGenre(genreA, new Date()));

		when(repository.findAll()).thenReturn(Stream
				.of(new Song("Sankeru", "neki opis", 5.0, 123, new Review(), bp1, bg1), new Song("Snovi od stakla", "neki opis pjesme", 4.8, 136 ,new Review(), bp2, bg1)).collect(Collectors.toList()));
		assertEquals(2, service.getAllSongs().size());
	}
}
