package br.dcx.ufpb.jefferson.springbootprojecthelpbridge.repositories;

import br.dcx.ufpb.jefferson.springbootprojecthelpbridge.domain.entities.Technical;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TechnicalRepository extends JpaRepository<Technical, Integer> {
}
