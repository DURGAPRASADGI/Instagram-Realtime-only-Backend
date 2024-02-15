package com.example.insta.model;

import java.time.LocalDateTime;

import com.example.insta.dto.UserDto;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Stories")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Story {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name = "id",column = @Column(name="user_id")),
		@AttributeOverride(name = "email",column = @Column(name="user_email"))
	})
	private UserDto user;
	@NotNull
	private String image;
	private String caption;
	private LocalDateTime timestamp;
	
}
