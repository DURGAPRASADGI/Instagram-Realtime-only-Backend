package com.example.insta.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.insta.model.Post;

@Repository
public interface PostRepository  extends JpaRepository<Post, Integer>{
	

	
	@Query("SELECT p FROM Post p WHERE p.user.id = :userId")
	public List<Post> findByUserId(@Param("userId") Integer userId);	
	

	@Query("SELECT p FROM Post p WHERE p.user.id IN :users ORDER BY p.createdAt DESC")
	public List<Post> findAllPostByUserIds(@Param("users") List<Integer> userIds);
	

}
