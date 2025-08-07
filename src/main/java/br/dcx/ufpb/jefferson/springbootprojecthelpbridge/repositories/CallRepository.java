package br.dcx.ufpb.jefferson.springbootprojecthelpbridge.repositories;

import br.dcx.ufpb.jefferson.springbootprojecthelpbridge.domain.entities.Call;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CallRepository extends JpaRepository<Call, Integer> {
}
