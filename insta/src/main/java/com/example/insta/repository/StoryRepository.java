package com.example.insta.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.insta.model.Story;

@Repository
public interface StoryRepository extends JpaRepository<Story, Integer> {
	
//	@Query("SELECT s FROM story s WHERE s.user.id=:userId")
	List<Story> findAllStoryByUserId(@Param("userId") Integer userId);
}
