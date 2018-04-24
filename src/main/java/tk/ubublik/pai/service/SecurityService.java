package tk.ubublik.pai.service;

import tk.ubublik.pai.entity.Role;

public interface SecurityService {

	boolean hasRole(Role role);
}
