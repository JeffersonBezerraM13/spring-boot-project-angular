package br.dcx.ufpb.jefferson.springbootangularprojecthelpdesk.services;

import br.dcx.ufpb.jefferson.springbootangularprojecthelpdesk.domain.entities.Person;
import br.dcx.ufpb.jefferson.springbootangularprojecthelpdesk.repositories.PersonRepository;
import br.dcx.ufpb.jefferson.springbootangularprojecthelpdesk.security.UserSS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private PersonRepository personRepository;

    //usamos o email no projeto
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Person> user = personRepository.findByEmail(email);
        if(user.isPresent()) {
            return new UserSS(user.get().getId(),user.get().getEmail(),user.get().getPassword(),user.get().getProfiles());
        }
        throw new UsernameNotFoundException(email);
    }
}
