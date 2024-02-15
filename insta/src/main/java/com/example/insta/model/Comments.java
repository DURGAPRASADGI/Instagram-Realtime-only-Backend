package com.example.insta.model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import com.example.insta.dto.UserDto;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "comments")
public class Comments {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name = "id",column = @Column(name="user_id")),
		@AttributeOverride(name = "email",column = @Column(name="user_email"))
	})
	private UserDto user;
	private String content;
	@Embedded
	@ElementCollection
	private Set<UserDto> likedByUser=new HashSet<UserDto>();
	
	private LocalDateTime createdAt;


}
