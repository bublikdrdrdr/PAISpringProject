package tk.ubublik.pai.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    @JsonIgnore
    private String password;

    @Column(name = "password_changed", nullable = false)
    private Date passwordChanged;

    @Column(nullable = false)
    private Date registered;

    @Column(nullable = false)
    private boolean enabled;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "user")
    private List<Block> blocks;

    @OneToMany(mappedBy = "admin")
    private List<Block> adminBlocks;

    @OneToMany(mappedBy = "owner")
    private List<Account> accounts;

    @OneToMany(mappedBy = "user")
    private List<PasswordResetRequest> passwordResetRequests;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> set = new HashSet<>();
        switch (role){ //yes, without break;
            case ADMIN: set.add(Role.ADMIN.getGrantedAuthority());
            case MODERATOR: set.add(Role.MODERATOR.getGrantedAuthority());
            case USER: set.add(Role.USER.getGrantedAuthority());
        }
        return set;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public User(String username, String email, String password, Date passwordChanged, Date registered, boolean enabled, Role role) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.passwordChanged = passwordChanged;
        this.registered = registered;
        this.enabled = enabled;
        this.role = role;
    }
}
