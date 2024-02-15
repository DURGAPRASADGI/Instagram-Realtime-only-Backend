package com.example.insta.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.insta.dto.UserDto;
import com.example.insta.expections.UserExpection;
import com.example.insta.model.User;
import com.example.insta.repository.UserRepository;
import com.example.insta.security.JwtTokenClaims;
import com.example.insta.security.JwtTokenProvider;


@Service
public class UserServiceImpl  implements UserService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public User registerUser(User user) throws UserExpection {
		// TODO Auto-generated method stub
		Optional<User> isEmailExist=userRepository.findByEmail(user.getEmail());
		if(isEmailExist.isPresent()) {
			throw new UserExpection("email has already exist");
		}
		Optional<User> username=userRepository.findByUsername(user.getUsername());
		if(username.isPresent()) {
			throw new UserExpection("the username has already exist");
		}
		if(user.getEmail()==null || user.getUsername()==null || user.getPassword()==null || user.getName()==null) {
			throw new UserExpection("please fill required fields");
		}
		
		User newUser=new User();
		newUser.setEmail(user.getEmail());
		newUser.setPassword(passwordEncoder.encode(user.getPassword()));
		newUser.setUsername(user.getUsername());
		newUser.setName(user.getName());
		return userRepository.save(newUser);	
		}

	@Override
	public User findUserById(Integer UserId) throws UserExpection {
		// TODO Auto-generated method stub
		Optional<User> user=userRepository.findById(UserId);
		if(user.isPresent()) {
			return user.get();
		}
	      throw new UserExpection("user not found with id : "+UserId);
	}

	@Override
	public User findUserProfile(String token) throws UserExpection {
		// TODO Auto-generated method stub
		//Baearer bscbsbcb
		token=token.substring(7);
		JwtTokenClaims jwtTokenClaims=jwtTokenProvider.getJwtTokenFromClaims(token);
		String email= jwtTokenClaims.getUsername();
		Optional<User> opt=userRepository.findByEmail(email);
		if(opt.isPresent()) {
			return opt.get();
		}
		throw new UserExpection("invalid token .....!");
	}

	@Override
	public User findUserByUsername(String username) throws UserExpection {
		// TODO Auto-generated method stub
		Optional<User> opt=userRepository.findByUsername(username);
		if(opt.isPresent()) {
			return opt.get();
		}
		throw new UserExpection("invalid username .....!");
	}

	@Override
	public String followUser(Integer reqUserId, Integer followUserId) throws UserExpection {
		// TODO Auto-generated method stub
		User reqUser=findUserById(reqUserId);
		User followUser=findUserById(followUserId);
		
		UserDto follower=new UserDto();
		follower.setEmail(reqUser.getEmail());
		follower.setId(reqUser.getId());
		follower.setName(reqUser.getName());
		follower.setUserImage(reqUser.getImage());
		follower.setUsername(reqUser.getUsername());
		
		UserDto following =new UserDto();
		following.setEmail(followUser.getEmail());
		following.setId(followUser.getId());
		following.setName(followUser.getName());
		following.setUserImage(followUser.getImage());
		following.setUsername(followUser.getUsername());
		
		reqUser.getFollower().add(follower);
		followUser.getFollowing().add(following);
		userRepository.save(followUser);
		userRepository.save(reqUser);
		
		
		return "you are following "+followUser.getUsername();
	}

	@Override
	public String unFollowUser(Integer reqUserId, Integer followUserId) throws UserExpection {
		// TODO Auto-generated method stub
		User reqUser=findUserById(reqUserId);
		User followUser=findUserById(followUserId);
		
		UserDto follower=new UserDto();
		follower.setEmail(reqUser.getEmail());
		follower.setId(reqUser.getId());
		follower.setName(reqUser.getName());
		follower.setUserImage(reqUser.getImage());
		follower.setUsername(reqUser.getUsername());
		
		UserDto following =new UserDto();
		following.setEmail(followUser.getEmail());
		following.setId(followUser.getId());
		following.setName(followUser.getName());
		following.setUserImage(followUser.getImage());
		following.setUsername(followUser.getUsername());
		
		reqUser.getFollower().remove(follower);
		followUser.getFollowing().remove(following);
		userRepository.save(followUser);
		userRepository.save(reqUser);
		return "you are unfollowing "+followUser.getUsername();
	}

	@Override
	public List<User> findUserByIds(List<Integer> userIds) throws UserExpection {
		// TODO Auto-generated method stub
		List<User> users=userRepository.findbyUserIds(userIds);
		return users;
	}

	@Override
	public List<User> searchUser(String query) throws UserExpection {
		// TODO Auto-generated method stub
		List<User> users=userRepository.findByQuery(query);
	if(users.size()==0) {
		throw new UserExpection("user not found");
	}
		return users;
	}

	@Override
	public User updateUserDetails(User updatedUser, User existingUser) throws UserExpection {
		// TODO Auto-generated method stub
		if(updatedUser.getEmail()!=null) {
			existingUser.setEmail(updatedUser.getEmail());
		}
		return null;
	}

}
