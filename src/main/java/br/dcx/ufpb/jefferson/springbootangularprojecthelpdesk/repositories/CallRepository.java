package br.dcx.ufpb.jefferson.springbootangularprojecthelpdesk.repositories;

import br.dcx.ufpb.jefferson.springbootangularprojecthelpdesk.domain.entities.Call;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CallRepository extends JpaRepository<Call, Integer> {
}
