package br.dcx.ufpb.jefferson.springbootprojecthelpbridge.repositories;

import br.dcx.ufpb.jefferson.springbootprojecthelpbridge.domain.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Integer> {
}
