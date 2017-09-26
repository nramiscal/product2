package com.nramiscal.loginReg.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name="posts")
public class Post {

	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column
	@Size(min=1)
	private String content;

	
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
	    name = "likes", 
	    joinColumns = @JoinColumn(name = "post_id"), 
	    inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> users;
	
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;
	
	public Post() {
		
	}

	public Post(String content, User user) {
		super();
		this.user = user;
		this.content = content;
	}

	public Long getId() {
		return id;
	}

	public String getContent() {
		return content;
	}


	public void setId(Long id) {
		this.id = id;
	}

	public void setContent(String content) {
		this.content = content;
	}


	public void setUser(User user) {
		this.user = user;
	}



	public User getUser() {
		return user;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public int getNumUsersWhoLike() {
		return users.size();
	}
	
//	public List<User> getSubUsers(){
//		List<User> subusers = this.getUsers();
//		Iterator<User> i = subusers.iterator();
//		while (i.hasNext()) {
//		   User o = i.next();
//		  if (o.checkIfSuper()) {
//		    i.remove();
//		  }
//		}
//		return subusers;
//	}
	



	
	
		
}
