package br.dcx.ufpb.jefferson.springbootangularprojecthelpdesk.services.db;

import br.dcx.ufpb.jefferson.springbootangularprojecthelpdesk.entities.Technical;
import br.dcx.ufpb.jefferson.springbootangularprojecthelpdesk.repositories.TechnicalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TechnicalService {

    @Autowired
    private TechnicalRepository technicalRepository;

    public Technical findById(Integer id) {
        return technicalRepository.findById(id).orElse(null);
        //TODO: implementar o tratamento de erros
    }
}
