package com.example.insta.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.insta.dto.UserDto;
import com.example.insta.expections.CommentExpection;
import com.example.insta.expections.PostExpection;
import com.example.insta.expections.UserExpection;
import com.example.insta.model.Comments;
import com.example.insta.model.Post;
import com.example.insta.model.User;
import com.example.insta.repository.CommentRepository;
import com.example.insta.repository.PostRepository;

@Service
public class CommentServiceImpl implements CommentService {
	
	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PostService  postService;
	
	@Autowired
	private PostRepository postRepository;

	@Override
	public Comments createComment(Comments comments, Integer postId, Integer userId)
			throws UserExpection, PostExpection {
		User  user=userService.findUserById(userId);
		Post post=postService.findPostById(postId);
		UserDto userDto=new UserDto();
		userDto.setEmail(user.getEmail());
		userDto.setId(user.getId());
		userDto.setName(user.getName());
		userDto.setUserImage(user.getImage());
		userDto.setUsername(user.getUsername());
		
		comments.setUser(userDto);
		comments.setCreatedAt(LocalDateTime.now());
		Comments createdComment=commentRepository.save(comments);
		post.getComments().add(createdComment);
		postRepository.save(post);
		
		commentRepository.save(comments);
		
		return createdComment;
	}

	@Override
	public Comments findCommentById(Integer commentsId) throws CommentExpection {
		// TODO Auto-generated method stub
		Optional<Comments> opt=commentRepository.findById(commentsId);
		if(opt.isPresent()) {
			return opt.get();
		}
		throw new CommentExpection("comments has not exist");
	}

	@Override
	public Comments likeComments(Integer commentsId, Integer userId) throws CommentExpection, UserExpection {
		// TODO Auto-generated method stub
		User user=userService.findUserById(userId);
		Comments comments=findCommentById(commentsId);
		UserDto userDto=new UserDto();
		userDto.setEmail(user.getEmail());
		userDto.setId(user.getId());
		userDto.setName(user.getName());
		userDto.setUserImage(user.getImage());
		userDto.setUsername(user.getUsername());
		comments.getLikedByUser().add(userDto);
		return commentRepository.save(comments);
	}

	@Override
	public Comments unLikeComments(Integer commentsId, Integer userId) throws CommentExpection, UserExpection {
		// TODO Auto-generated method stub
		User user=userService.findUserById(userId);
		Comments comments=findCommentById(commentsId);
		UserDto userDto=new UserDto();
		userDto.setEmail(user.getEmail());
		userDto.setId(user.getId());
		userDto.setName(user.getName());
		userDto.setUserImage(user.getImage());
		userDto.setUsername(user.getUsername());
		comments.getLikedByUser().remove(userDto);
		return commentRepository.save(comments);
	}

}
