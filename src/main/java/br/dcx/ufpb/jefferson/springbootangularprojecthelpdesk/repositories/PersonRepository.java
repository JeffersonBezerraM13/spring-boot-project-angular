package br.dcx.ufpb.jefferson.springbootangularprojecthelpdesk.repositories;

import br.dcx.ufpb.jefferson.springbootangularprojecthelpdesk.domain.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Integer> {
}
