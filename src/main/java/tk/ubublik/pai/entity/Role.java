package tk.ubublik.pai.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public enum Role {

    USER, MODERATOR, ADMIN;

    public GrantedAuthority getGrantedAuthority(){
        return new SimpleGrantedAuthority(this.name());
    }

    public static Role parse(String s){
        return Enum.valueOf(Role.class, s.toUpperCase());
    }
}
