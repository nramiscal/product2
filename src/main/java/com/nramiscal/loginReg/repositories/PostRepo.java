package com.nramiscal.loginReg.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nramiscal.loginReg.models.Post;

@Repository
public interface PostRepo extends CrudRepository<Post, Long> {

}
