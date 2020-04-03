package com.nwt.microservice;

import com.nwt.microservice.model.*;
import com.nwt.microservice.repository.SongRepository;
import com.nwt.microservice.repository.GenreRepository;
import com.nwt.microservice.repository.AlbumRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import java.util.*;

//@RequiredArgsConstructor
@EnableDiscoveryClient
@SpringBootApplication
public class MicroserviceApplication /**/implements CommandLineRunner/**/ {

	private final SongRepository songRepository;
	private final AlbumRepository albumRepository;
	private final GenreRepository genreRepository;

	public MicroserviceApplication(SongRepository songRepository, AlbumRepository albumRepository, GenreRepository genreRepository) {
		this.songRepository = songRepository;
		this.albumRepository = albumRepository;
		this.genreRepository = genreRepository;
	}
	

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceApplication.class, args);
	}

	/**/
	@Override
	public void run(String... args) {
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

		songRepository.save(new Song("Sankeru", "neki opis", 5.0, 123, new Review(), bp1, bg1));
		songRepository.save(new Song("Snovi od stakla", "neki opis pjesme", 4.8, 136 ,new Review(), bp2, bg1));

	}
	/**/
}