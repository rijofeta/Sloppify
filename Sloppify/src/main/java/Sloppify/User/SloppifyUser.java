package Sloppify.User;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;


@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
public class SloppifyUser implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "hashed_password", nullable = false)
    private String password;

    @Column(name = "username", nullable = false)
    private String username;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private SloppifyUserRole sloppifyUserRole;

    @Column(name = "locked", columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean locked;

    @Column(name = "enabled", columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean enabled;

    public SloppifyUser(String email, String password, String username, SloppifyUserRole sloppifyUserRole) {
        this.email = email;
        this.password = password;
        this.username = username;
        this.sloppifyUserRole = sloppifyUserRole;
        this.locked = false;
        this.enabled = false;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (sloppifyUserRole == SloppifyUserRole.ADMIN) return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"));
        else return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {return password;}

    @Override
    public String getUsername() {return username;}

    @Override
    public boolean isAccountNonExpired() {return true;}

    @Override
    public boolean isAccountNonLocked() {return !locked;}

    @Override
    public boolean isCredentialsNonExpired() {return true;}

    @Override
    public boolean isEnabled() {return enabled;}
}
