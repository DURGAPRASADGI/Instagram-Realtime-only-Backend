package com.example.insta.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.example.insta.dto.UserDto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String username;
	private String name;
	private String email;
	private String mobile;
	private String website;
	private String bio;
	private String gender;
	private String image;
	private String password;
	
	@Embedded
	@ElementCollection
    private Set<UserDto> follower=new HashSet<UserDto>();
	
	@Embedded
	@ElementCollection
	private Set<UserDto> following=new HashSet<UserDto>();
	
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	private List<Story> stories=new ArrayList<Story>();
	
	@ManyToMany
	private List<Post> savedPost=new ArrayList<Post>();
	

}
