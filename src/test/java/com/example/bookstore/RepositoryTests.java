package com.example.bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.bookstore.domain.Book;
import com.example.bookstore.domain.BookRepository;
import com.example.bookstore.domain.Category;
import com.example.bookstore.domain.CategoryRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class RepositoryTests {
	@Autowired
	private BookRepository brepository;
	@Autowired
	private CategoryRepository crepository;

	@Test
	public void findByTitleShouldReturnAuthor() {
		brepository.save(new Book("kanaruokien kokkikirja", "iso kana", 2025, "sad1238a", 13.99,
				crepository.findByName("Cooking").get(0)));
		// kirja poistetaan myöhemmässä testissä

		List<Book> books = brepository.findByTitle("kanaruokien kokkikirja");
		assertThat(books.get(0).getAuthor()).isEqualTo("iso kana");
	}

	@Test
	public void createNewBook() {
		Book book = new Book("Paul Trembley", "A Head Full of Ghosts", 2015, "ISBN434345621394", 16.30,
				crepository.findByName("Cooking").get(0));
		brepository.save(book);
		assertThat(book.getTitle()).isNotNull();
	}

	@Test
	public void deleteBook() {
		brepository.deleteById(brepository.findByTitle("kanaruokien kokkikirja").get(0).getId());
		;
		assertThat(brepository.findByTitle("kanaruokien kokkikirja")).isEmpty();
		;

	}

	@Test
	public void findByNameShouldReturnName() {
		List<Category> categories = crepository.findByName("Fantasy");
		assertThat(categories.get(0).getName()).isEqualTo("Fantasy");
	}

	@Test
	public void createNewCategory() {
		Category category = new Category("Science Fiction");
		crepository.save(category);
		assertThat(category.getName()).isNotNull();
	}

}