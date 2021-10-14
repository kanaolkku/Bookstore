package com.example.bookstore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.bookstore.domain.Book;
import com.example.bookstore.domain.BookRepository;
import com.example.bookstore.domain.CategoryRepository;

@Controller
public class BookController {
	@Autowired
	private BookRepository bookrepository;
	@Autowired
	private CategoryRepository categoryrepository;

	@GetMapping("/index")
	public String index() {
		return "index";
	}

	@GetMapping(value = { "/", "/booklist" })
	public String booklist(Model model) {
		model.addAttribute("books", bookrepository.findAll());
		return "booklist";
	}

	@GetMapping(value = "/add")
	public String addBook(Model model) {
		model.addAttribute("book", new Book());
		model.addAttribute("categories", categoryrepository.findAll());
		return "addbook";
	}

	@PostMapping(value = "/save")
	public String saveBook(Book book) {
		bookrepository.save(book);
		return "redirect:booklist";
	}

	@GetMapping(value = "/delete/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String deleteBook(@PathVariable("id") Long bookId, Model model) {
		bookrepository.deleteById(bookId);
		return "redirect:../booklist";
	}

	@GetMapping(value = "/edit/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String editBook(@PathVariable("id") Long bookId, Model model) {
		model.addAttribute("book", bookrepository.findById(bookId));
		return "editbook";
	}

	@GetMapping(value = "/login")
	public String login() {
		return "login";
	}

}
