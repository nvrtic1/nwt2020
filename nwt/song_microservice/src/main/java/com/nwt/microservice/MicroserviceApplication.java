package com.nwt.microservice;

import com.nwt.microservice.model.*;
import com.nwt.microservice.repository.SongRepository;
import com.nwt.microservice.repository.GenreRepository;
import com.nwt.microservice.repository.SingerRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.*;

//@RequiredArgsConstructor
@SpringBootApplication
public class MicroserviceApplication /**/implements CommandLineRunner/**/ {

	private final SongRepository songRepository;
	private final SingerRepository singerRepository;
	private final GenreRepository genreRepository;

	public MicroserviceApplication(SongRepository songRepository, SingerRepository singerRepository, GenreRepository genreRepository) {
		this.songRepository = songRepository;
		this.singerRepository = singerRepository;
		this.genreRepository = genreRepository;
	}
	

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceApplication.class, args);
	}

	/**/
	@Override
	public void run(String... args) {
		// Create a couple of Film, Actor and FilmActor
		Singer singerA = new Singer("Mile", "Kitirc", new Date(95, 3, 11, 0, 0), "Neka biografija od Mileta");
		Singer singerB = new Singer("Nedljko", "Bajic", new Date(95, 1, 10, 0, 0), "Neka biografija od Baje");
		singerRepository.saveAll(Arrays.asList(singerA, singerB));

		Genre genreA = new Genre("Narodna");
		Genre genreB = new Genre("Sevdalinka");
		genreRepository.saveAll(Arrays.asList(genreA, genreB));

		ArrayList<SongSinger> bp1 = new ArrayList<>();

		bp1.add(new SongSinger(singerA));
		bp1.add(new SongSinger(singerB));

		ArrayList<SongSinger> bp2 = new ArrayList<>();
		bp2.add(new SongSinger(singerA));


		ArrayList<SongGenre> bg1 = new ArrayList<>();
		bg1.add(new SongGenre(genreA, new Date()));

		songRepository.save(new Song("Sankeru", "neki opis", 5.0, 123, new Song_Session(), bp1, bg1));
		songRepository.save(new Song("Snovi od stakla", "neki opis pjesme", 4.8, 136 ,new Song_Session(), bp2, bg1));

	}
	/**/
}