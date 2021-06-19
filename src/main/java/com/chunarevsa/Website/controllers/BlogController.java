package com.chunarevsa.Website.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller // добавляем, чтобы указать, что это контроллер
public class BlogController {

	@GetMapping ("/blog")
	public String blogMain (Model model) {
		return "blog-main";
	}

	
}
