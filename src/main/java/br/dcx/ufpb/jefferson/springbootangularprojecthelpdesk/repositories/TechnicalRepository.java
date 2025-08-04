package br.dcx.ufpb.jefferson.springbootangularprojecthelpdesk.repositories;

import br.dcx.ufpb.jefferson.springbootangularprojecthelpdesk.domain.entities.Person;
import br.dcx.ufpb.jefferson.springbootangularprojecthelpdesk.domain.entities.Technical;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TechnicalRepository extends JpaRepository<Technical, Integer> {
}
