package ch.hearc.jee2022.myvines;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import ch.hearc.jee2022.myvines.cellar.model.User;
import ch.hearc.jee2022.myvines.cellar.model.Vine;
import ch.hearc.jee2022.myvines.cellar.repository.VineRepository;
import ch.hearc.jee2022.myvines.cellar.service.impl.UserServiceImpl;

@SpringBootApplication
public class MyVinesApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyVinesApplication.class, args);
	}

	/**
	 * Define the two bases users of the application
	 * 
	 * @param serviceImpl the UserService to interact with the database
	 * @return
	 */
	@Bean
	public CommandLineRunner InitUser(UserServiceImpl serviceImpl) {
		return args -> {
			try {
				User admin = new User();
				admin.setAdmin(true);
				admin.setUsername("admin");
				admin.setPassword("admin");
				serviceImpl.save(admin);

				User user = new User();
				user.setAdmin(false);
				user.setUsername("user");
				user.setPassword("user");
				serviceImpl.save(user);
			} catch (Exception e) {

			}
		};
	}

	/**
	 * Define some vines to begin to use the application
	 * 
	 * @param repository the VineRepository to store the vines
	 * @return
	 */
	@Bean
	public CommandLineRunner InitVine(VineRepository repository) {
		return args -> {

			if (repository.count() == 0) {
				List<Vine> list = new ArrayList<>();
				Vine vine = new Vine();
				vine.setYear(2019);
				vine.setCountry("Suisse");
				vine.setName("Ticino DOC Merlot Selezione d'Ottobre");
				vine.setPrice(15.50);
				vine.setComment(
						"Robe rubis, un bouquet de baies bien mûres aux accents fruités, des notes épicées agréables en bouche avec une forte présence fruitée. Un grand classique suisse, rond et moyennement corsé, simple et toujours très apprécié.");
				list.add(vine);

				vine = new Vine();
				vine.setYear(2020);
				vine.setCountry("Suisse");
				vine.setName("Valais AOC Humagne Rouge Bibacchus");
				vine.setPrice(15.95);
				vine.setComment(
						"Robe rubis sombre moyennement soutenue, des arômes fruités typiques, un bouquet délicatement épicé de baies rouges, une maturité moyenne et une belle présence en bouche, harmonieux, des notes agréables de baies mûres avec des tanins encore un peu jeunes, un vin bien structuré et moyennement corsé.");
				list.add(vine);

				vine = new Vine();
				vine.setYear(2019);
				vine.setCountry("Suisse");
				vine.setName("Valais AOC Dôle de Salquenen Les Dailles");
				vine.setPrice(7.75);
				vine.setComment(
						"Bouquet intense de fruits bien mûrs avec des notes de baies rouges, un corps moyen au palais, ample et souple, une bonne structure,des tanins présents avec une belle concentration et d'agréables notes épicées en finale.");
				list.add(vine);

				vine = new Vine();
				vine.setYear(2021);
				vine.setCountry("Suisse");
				vine.setName("Valais AOC Johannisberg Terrasses du Rhône Bibacchus");
				vine.setPrice(8.95);
				vine.setComment(
						"Robe jaune citron foncé, un agréable nez floral aux notes exotiques, légèrement minéral, des nuances de pêche, élégant et fin en bouche, une grande richesse aromatique, des notes d'amande et de fruits jaunes bien mûrs, très équilibré avec beaucoup de corps et unefinale corsée. Un superbe Johannisberg !");
				list.add(vine);

				vine = new Vine();
				vine.setYear(2020);
				vine.setCountry("France");
				vine.setName("Alsace AOP Gewürztraminer Baron de Hoen Beblenheim");
				vine.setPrice(10.50);
				vine.setComment(
						"Jaune clair, des notes florales grasses et lourdes, une nuance exotique avec de fins arômes de rose, une douceur ample, doux et agréable, équilibré avec peu d'acidité, une nuance épicée avec une légère amertume en finale.");
				list.add(vine);

				vine = new Vine();
				vine.setYear(2021);
				vine.setCountry("Suisse");
				vine.setName("Aigle Chablais AOC Les Murailles H. Badoux");
				vine.setPrice(22.50);
				vine.setComment(
						"Nez très floral qui renferme quelques notes de levure, attaque en bouche franche et bien équilibrée, délicatement fruité et vivace, moyennement rond avec une légère amertume en finale. Un classique connu au-delà des frontières de la Suisse.");
				list.add(vine);

				vine = new Vine();
				vine.setYear(null);
				vine.setCountry("France");
				vine.setName("Taittinger Champagne Réserve brut AOC");
				vine.setPrice(49.95);
				vine.setComment(
						"Robe jaune or clair, des senteurs classique de brioche, d'écorces d'agrumes mêlées à des notes minérales, bouche d'une grande fraîcheur ; le style de Taittinger est élégant et fruité, avec de fins arômes de pamplemousse et des notes de fleurs blanches, mousse fine et persistante, merveilleux équilibre, un vin tout en finesse et en élégance.");
				list.add(vine);

				repository.saveAll(list);
			}
		};
	}

}
