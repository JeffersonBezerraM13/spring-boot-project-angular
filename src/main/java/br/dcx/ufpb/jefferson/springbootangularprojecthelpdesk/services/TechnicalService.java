package br.dcx.ufpb.jefferson.springbootangularprojecthelpdesk.services;

import br.dcx.ufpb.jefferson.springbootangularprojecthelpdesk.domain.entities.Technical;
import br.dcx.ufpb.jefferson.springbootangularprojecthelpdesk.repositories.TechnicalRepository;
import br.dcx.ufpb.jefferson.springbootangularprojecthelpdesk.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TechnicalService {

    @Autowired
    private TechnicalRepository technicalRepository;

    public List<Technical> findAll() {
        return technicalRepository.findAll();
    }

    public Technical findById(Integer id) {
        return technicalRepository
                .findById(id)
                .orElseThrow(
                        () -> new ObjectNotFoundException("Technical not found, id: " + id)
                );
    }
}
