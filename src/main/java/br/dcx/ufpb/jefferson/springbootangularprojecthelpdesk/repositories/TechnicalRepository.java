package br.dcx.ufpb.jefferson.springbootangularprojecthelpdesk.repositories;

import br.dcx.ufpb.jefferson.springbootangularprojecthelpdesk.domain.entities.Technical;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TechnicalRepository extends JpaRepository<Technical, Integer> {
}
