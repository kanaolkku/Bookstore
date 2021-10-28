package com.example.bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.bookstore.domain.Book;
import com.example.bookstore.domain.BookRepository;
import com.example.bookstore.domain.Category;
import com.example.bookstore.domain.CategoryRepository;
import com.example.bookstore.domain.User;
import com.example.bookstore.domain.UserRepository;

@SpringBootApplication
public class BookstoreApplication {

	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(BookRepository bookrepository, CategoryRepository categoryrepository,
			UserRepository userrepository) {
		return (args) -> {
			// delete existing entries
			bookrepository.deleteAll();
			categoryrepository.deleteAll();
			userrepository.deleteAll();

			log.info("adding categories");
			// add categories
			categoryrepository.save(new Category("Cooking"));
			categoryrepository.save(new Category("Fantasy"));

			log.info("adding books");
			// add books
			bookrepository.save(new Book("kanaruokien kokkikirja", "iso kana", 2025, "sad1238a", 13.99,
					categoryrepository.findByName("Cooking").get(0)));
			bookrepository.save(new Book("Aakkoset aikuisille", "Varjo G", 2010, "sasd1235a", 19.99,
					categoryrepository.findByName("Fantasy").get(0)));

			User user1 = new User("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER");
			User user2 = new User("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "ADMIN");
			userrepository.save(user1);
			userrepository.save(user2);

			log.info("fetch all books");
			for (Book book : bookrepository.findAll()) {
				log.info(book.toString());
			}

			log.info("fetch all categories");
			for (Category category : categoryrepository.findAll()) {
				log.info(category.toString());
			}
		};
	}

}
