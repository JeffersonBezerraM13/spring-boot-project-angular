package br.dcx.ufpb.jefferson.springbootprojecthelpbridge.services;

import br.dcx.ufpb.jefferson.springbootprojecthelpbridge.domain.entities.Person;
import br.dcx.ufpb.jefferson.springbootprojecthelpbridge.repositories.PersonRepository;
import br.dcx.ufpb.jefferson.springbootprojecthelpbridge.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private PersonRepository personRepository;

    //usamos o email no projeto
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Person user = personRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return new UserDetailsImpl(user);
    }
}
