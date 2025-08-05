package br.dcx.ufpb.jefferson.springbootangularprojecthelpdesk.services;

import br.dcx.ufpb.jefferson.springbootangularprojecthelpdesk.domain.entities.Call;
import br.dcx.ufpb.jefferson.springbootangularprojecthelpdesk.repositories.CallRepository;
import br.dcx.ufpb.jefferson.springbootangularprojecthelpdesk.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CallService {

    @Autowired
    private CallRepository callRepository;

    public Call findById(Integer id) {
        Optional<Call> call = callRepository.findById(id);
        return call.orElseThrow(() -> new ObjectNotFoundException("Call not found, ID:"+id));
    }

    public List<Call> findAll() {
        return callRepository.findAll();
    }

}
