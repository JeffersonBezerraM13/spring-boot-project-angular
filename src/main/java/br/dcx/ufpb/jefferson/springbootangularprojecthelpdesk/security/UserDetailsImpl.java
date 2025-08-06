package br.dcx.ufpb.jefferson.springbootangularprojecthelpdesk.security;

import br.dcx.ufpb.jefferson.springbootangularprojecthelpdesk.domain.entities.Person;
import br.dcx.ufpb.jefferson.springbootangularprojecthelpdesk.domain.entities.enums.Profile;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public class UserDetailsImpl implements UserDetails {

    private String email;
    private String password;
    private Set<Profile> profiles;

    public UserDetailsImpl(Person user) {
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.profiles = user.getProfiles(); // seu enum Profile
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return profiles.stream()
                .map(p -> new SimpleGrantedAuthority(p.getDescription())) // "ROLE_ADMIN", etc.
                .collect(Collectors.toSet());
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    // Você pode retornar true direto se não quiser usar esses controles
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
        return true;
    }
}
