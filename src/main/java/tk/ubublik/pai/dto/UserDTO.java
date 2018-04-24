package tk.ubublik.pai.dto;

import tk.ubublik.pai.entity.Role;
import tk.ubublik.pai.entity.User;

public class UserDTO extends ConvertibleDTO<User> {

	public Long id;
	public String role;

	public UserDTO() {
		super();
	}

	public UserDTO(User entity) {
		id = entity.getId();
	}

	public Role getRole(){
		if (role==null) return null;
		return Role.parse(role);
	}

	@Override
	public User toEntity() {
		return null;
	}
}