package com.example.bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.bookstore.web.BookController;
import com.example.bookstore.web.BookControllerRest;
import com.example.bookstore.web.CategoryController;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class BookstoreApplicationTests {
	@Autowired
	private BookController bookcontroller;
	@Autowired
	private CategoryController catcontroller;

	@Autowired
	private BookControllerRest bookcontrollerrest;

	@Test
	void contextLoads() {
		assertThat(bookcontroller).isNotNull();
		assertThat(catcontroller).isNotNull();
		assertThat(bookcontrollerrest).isNotNull();
	}

}
