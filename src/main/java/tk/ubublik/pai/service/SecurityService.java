package tk.ubublik.pai.service;

import tk.ubublik.pai.entity.Role;
import tk.ubublik.pai.entity.User;

public interface SecurityService {

	boolean hasRole(Role role);
	User getAuthenticatedUser();
}
