package com.example.insta.dto;

import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

	private Integer id;
	private String username;
	private String email;
	private String name;
	private String userImage;
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserDto other = (UserDto) obj;
		return Objects.equals(email, other.email) && Objects.equals(id, other.id) && Objects.equals(name, other.name)
				&& Objects.equals(userImage, other.userImage) && Objects.equals(username, other.username);
	}
	@Override
	public int hashCode() {
		return Objects.hash(email, id, name, userImage, username);
	}
}
