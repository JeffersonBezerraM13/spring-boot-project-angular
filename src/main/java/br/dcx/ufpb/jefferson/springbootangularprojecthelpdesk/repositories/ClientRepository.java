package br.dcx.ufpb.jefferson.springbootangularprojecthelpdesk.repositories;

import br.dcx.ufpb.jefferson.springbootangularprojecthelpdesk.domain.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Integer> {
}
