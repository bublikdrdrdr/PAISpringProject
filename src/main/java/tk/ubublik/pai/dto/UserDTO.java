package tk.ubublik.pai.dto;

import tk.ubublik.pai.entity.Role;
import tk.ubublik.pai.entity.User;

import java.util.Date;

public class UserDTO extends ConvertibleDTO<User> {

	public Long id;
	public String username;
	public String email;
	public String password;
	public Date passwordChanged;
	public Date registered;
	public Boolean enabled;
	public String role;

	public UserDTO() {
	}

	public UserDTO(Long id, String username, String email, String password, Date passwordChanged, Date registered,
	               Boolean enabled, String role) {
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.passwordChanged = passwordChanged;
		this.registered = registered;
		this.enabled = enabled;
		this.role = role;
	}

	public UserDTO(User entity) {
		this(entity.getId(), entity.getUsername(), entity.getEmail(), entity.getPassword(), entity.getPasswordChanged(),
				entity.getRegistered(), entity.isEnabled(), entity.getRole().name().toLowerCase());
	}

	public Role getRole(){
		if (role==null) return null;
		return Role.parse(role);
	}

	@Override
	public User toEntity() {
		return new User(id, username, email, password, passwordChanged, registered, enabled, getRole(), null,
				null, null);
	}
}