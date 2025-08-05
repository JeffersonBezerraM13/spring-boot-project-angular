package br.dcx.ufpb.jefferson.springbootangularprojecthelpdesk.services;

import br.dcx.ufpb.jefferson.springbootangularprojecthelpdesk.domain.entities.Call;
import br.dcx.ufpb.jefferson.springbootangularprojecthelpdesk.domain.entities.Client;
import br.dcx.ufpb.jefferson.springbootangularprojecthelpdesk.domain.entities.Technical;
import br.dcx.ufpb.jefferson.springbootangularprojecthelpdesk.domain.entities.dtos.CallDTO;
import br.dcx.ufpb.jefferson.springbootangularprojecthelpdesk.domain.entities.enums.Priority;
import br.dcx.ufpb.jefferson.springbootangularprojecthelpdesk.domain.entities.enums.Status;
import br.dcx.ufpb.jefferson.springbootangularprojecthelpdesk.repositories.CallRepository;
import br.dcx.ufpb.jefferson.springbootangularprojecthelpdesk.repositories.TechnicalRepository;
import br.dcx.ufpb.jefferson.springbootangularprojecthelpdesk.services.exceptions.ObjectNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CallService {

    @Autowired
    private CallRepository callRepository;
    @Autowired
    private TechnicalService technicalService;
    @Autowired
    private ClientService clientService;

    public Call findById(Integer id) {
        Optional<Call> call = callRepository.findById(id);
        return call.orElseThrow(() -> new ObjectNotFoundException("Call not found, ID:"+id));
    }

    public List<Call> findAll() {
        return callRepository.findAll();
    }

    public Call create(@Valid CallDTO objDto) {
        return callRepository.save(newCall(objDto));
    }

    private Call newCall(CallDTO objDto) {
        //getTehcnical é o id dele já
        Technical technical = technicalService.findById(objDto.getTechnical());
        //.getClient é o id, não o obj completo
        Client client = clientService.findById(objDto.getClient());

        Call call = new Call();
        if(objDto.getId() != null) {
            call.setId(objDto.getId());
        }
        call.setTechnical(technical);
        call.setClient(client);
        //referencia o inteiro do enum
        call.setPriorit(Priority.toEnum(objDto.getPriorit()));
        call.setStatus(Status.toEnum(objDto.getPriorit()));
        call.setTitle(objDto.getTitle());
        call.setObservations(objDto.getObservations());
        return call;
    }
}
