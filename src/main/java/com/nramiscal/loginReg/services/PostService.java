package com.nramiscal.loginReg.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nramiscal.loginReg.models.Post;
import com.nramiscal.loginReg.models.User;
import com.nramiscal.loginReg.repositories.PostRepo;

@Service
public class PostService {
	
	private PostRepo postRepo;
	
	public PostService(PostRepo postRepo) {
		this.postRepo = postRepo;
	}

	public void addPost(String content, User user) {
		Post post = new Post(content, user);
		postRepo.save(post);
		
	}

	public List<Post> getAllPosts() {
		return (List<Post>) postRepo.findAll();
	}

	public void deletePostById(Long id) {
		postRepo.delete(id);
		
	}

	public Post findPostById(Long post_id) {
		return postRepo.findOne(post_id);
	}
	
	
}
