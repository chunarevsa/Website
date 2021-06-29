package com.chunarevsa.Website.controllers;

import com.chunarevsa.Website.models.Post;
import com.chunarevsa.Website.repo.PostRepositry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller // добавляем, чтобы указать, что это контроллер
public class BlogController {

	/* подключаем передачу всех записей в blog-main 
	из таблички Post*/

	//Создаём переменну которая будет ссылаться на PostRepository
	@Autowired
	private PostRepositry postRepositry1; // переменная
	// обязательно должен быть импорт PostRepositry сверху 

	@GetMapping ("/blog")
	public String blogMain (Model model) {
		Iterable<Post> posts = postRepositry1.findAll();   /* Массив данных из таблицы
		 По сути мы создаём объкт posts и обращаемся к репозиторию
		 postRepositry1. - вызываем функции для работы с таблицей */
		model.addAttribute("posts", posts);
		/* обращаемся к модели, передеаём в"posts" значение posts */

		return "blog-main";
	}

	@GetMapping ("/blog/add")
	public String blogAdd (Model model) {
		return "blog-add";
	}

	 /* Сделаем отслеживание перехода не через GetMapping, 
	а через post (его мы указывали в blog-add) */
	@PostMapping("/blog/add") // срабатывает только когда нажимается добавить статью 
	public String blogPostAdd(@RequestParam String title,  @RequestParam String anons, @RequestParam String full_text, Model model) {
	/*  @RequestParam String -добавляем получение новых параметров типом строка
 		title,anons и full_text - название берем из blog-add*/
		 Post post = new Post(title, anons, full_text); // Выделяем память под объект пост на основе модели Post
		 postRepositry1.save(post);	// Сохранение данных (Поста ) в табличку
		return "redirect:/blog"; // переадресация пользователя на главную страничку
	}



}
