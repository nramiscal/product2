package com.nramiscal.loginReg.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nramiscal.loginReg.models.Post;
import com.nramiscal.loginReg.models.User;
import com.nramiscal.loginReg.services.PostService;
import com.nramiscal.loginReg.services.UserService;
import com.nramiscal.loginReg.validator.UserValidator;


@Controller
public class HomeController {
	
	private UserService userService;
	private UserValidator userValidator;
	private PostService postService;
	
	public HomeController(UserService userService, UserValidator userValidator, PostService postService) {
		this.userService = userService;
		this.userValidator = userValidator;
		this.postService = postService;
		this.userService.initRoles();
	}
	
	
	// This page loads on successful login.
	@RequestMapping(value = {"/", "/home"})
	public String home(Principal principal, Model model, HttpSession session) {
		
		String username = principal.getName();
		User user = userService.findByUsername(username); // find user by email
		userService.updateUserDate(user.getId(), user); // set updated_at
		session.setAttribute("currentUser", user);
		model.addAttribute("currentUser", user);
		model.addAttribute("users", userService.all());
		model.addAttribute("posts", postService.getAllPosts());

		return "adminPage";	
		
	}
	
	@RequestMapping("/login")
	public String loginReg(@Valid @ModelAttribute("user") User user, @RequestParam(value="error", required=false) String error, @RequestParam(value="logout", required=false) String logout, Model model) {
        if(error != null) {
            model.addAttribute("errorMessage", "Invalid credentials. Please try again.");
        }
        if(logout != null) {
            model.addAttribute("logoutMessage", "Logout Successful");
        }
		return "loginReg";
	}
	
	@RequestMapping("/registration")
	public String registerForm(@Valid @ModelAttribute("user") User user, @RequestParam(value="error", required=false) String error, @RequestParam(value="logout", required=false) String logout, Model model) {
		return "redirect:/login";
	}
	

	@PostMapping("/registration")
	public String registration(@Valid @ModelAttribute("user") User user, BindingResult result, Model model) {
		
		userValidator.validate(user, result);
		
		if (result.hasErrors()) {
			return "loginReg";
		}
		
		userService.saveWithUserRole(user);
		return "redirect:/login";
	}
	
	@RequestMapping("/delete/{id}")
	public String deleteUser(@PathVariable("id") Long id) {
		postService.deletePostById(id);
		return "redirect:/home";
	}
	
	@RequestMapping("/makeAdmin/{id}")
	public String makeAdmin(@PathVariable("id") Long id) {
		User user = userService.findUserById(id);
		userService.updateUserWithAdminRole(user);
		return "redirect:/home";
	}
	
	@RequestMapping("/makeUser/{id}")
	public String makeUser(@PathVariable("id") Long id) {
		User user = userService.findUserById(id);
		userService.updateWithUserRole(user);
		return "redirect:/home";
	}
	
	@RequestMapping("/savepost")
	public String savepost(@RequestParam("user_id") Long id, @RequestParam("content") String content) {
		postService.addPost(content, userService.findUserById(id));
		return "redirect:/";
	}
	
	@RequestMapping("/users/{id}")
	public String showUser(@PathVariable("id") Long id, Model model) {
		model.addAttribute("user", userService.findUserById(id));
		
		return "dashboard";
	}
	
	@RequestMapping("/like/{post_id}/{user_id}")
	public String like(@PathVariable("user_id") Long user_id, @PathVariable("post_id") Long post_id) {
		User user = userService.findUserById(user_id);
		Post post = postService.findPostById(post_id);
	
		    
		List<Post> liked_posts = user.getLiked_posts();
		
		for (int i = 0; i < liked_posts.size(); i++) {
			if (liked_posts.get(i).getId() == post.getId()) {
				return "redirect:/";
			}
		}
		
		liked_posts.add(post);
		user.setLiked_posts(liked_posts);
		userService.updateUser(user);
		return "redirect:/";

		  
	}
	
	@RequestMapping("/{post_id}")
	public String showLikePage(@PathVariable("post_id") Long post_id, Model model) {
		model.addAttribute("post", postService.findPostById(post_id));
		
		return "likePage";
	}
	
}
