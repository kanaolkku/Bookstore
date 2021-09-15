package com.example.bookstore.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody // pois myöhemmin, että thymeleaf toimii

public class BookController {

	@GetMapping("/index")
	public String index() {
		return "index page";
	}

}
