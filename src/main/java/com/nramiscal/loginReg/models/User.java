package com.nramiscal.loginReg.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;


@Entity
@Table(name="users")
public class User {
	@Id
	@GeneratedValue
	private Long id;
	
	@Column
	@Size(min=1)
	private String name;
	
	@Column
	@Size(min=1)
	private String alias;
	
	@Email
	private String username;
	
	@Column
	@Size(min=8)
	private String password;
	
	@Transient
	private String passwordConfirmation;
	
	@Column
	private Date created_at;
	
	@Column
	private Date updated_at;
	
	
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
	    name = "users_roles", 
	    joinColumns = @JoinColumn(name = "user_id"), 
	    inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles;
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
	    name = "likes", 
	    joinColumns = @JoinColumn(name = "user_id"), 
	    inverseJoinColumns = @JoinColumn(name = "post_id"))
    private List<Post> liked_posts;
    
	@OneToMany(mappedBy="user", fetch=FetchType.LAZY)
	private List<Post> posts;
    
    // controllers

	public User() {
    	
    }	

	public User(String name, String alias, String username, String password) {
		super();
		this.name = name;
		this.alias = alias;
		this.username = username;
		this.password = password;
	}

	// created_at, updated_at

	@PrePersist
	protected void onCreate(){
	this.created_at = new Date();
	}

	@PreUpdate
	protected void onUpdate(){
	this.updated_at = new Date();
	}
	
	// getters

	public Long getId() {
		return id;
	}
	
	public String getFirstName() {
		return name;
	}

	public String getAlias() {
		return alias;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getPasswordConfirmation() {
		return passwordConfirmation;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public Date getUpdated_at() {
		return updated_at;
	}
	
	// setters

	public void setId(Long id) {
		this.id = id;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setPasswordConfirmation(String passwordConfirmation) {
		this.passwordConfirmation = passwordConfirmation;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public void setUpdated_at(Date updated_at) {
		this.updated_at = updated_at;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public boolean checkIfAdmin() {
		List<Role> roles = this.getRoles();
		for (int i = 0; i < roles.size(); i++) {
			if (roles.get(i).getName().equals("ROLE_ADMIN")) {
				return true;
			}
		}
		return false;
	}

	public String getName() {
		return name;
	}



	public List<Post> getPosts() {
		return posts;
	}



	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	public List<Post> getLiked_posts() {
		return liked_posts;
	}

	public void setLiked_posts(List<Post> liked_posts) {
		this.liked_posts = liked_posts;
	}
	
	public int getNumLikes() {
		return liked_posts.size();
	}
	

	
	
}

