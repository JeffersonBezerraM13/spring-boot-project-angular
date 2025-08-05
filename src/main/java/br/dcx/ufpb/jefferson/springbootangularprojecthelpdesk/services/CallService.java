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

import java.time.LocalDate;
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

    public Call update(Integer id, @Valid CallDTO objDto) {
        //assegurar que obj tenha o mesmo id que veio na URL
        //tratando o back para poder disponibilizar
        objDto.setId(id);
        //findById tem o tratamento de execções, caso ele exista vai seguir o fluxo do processamento
        Call oldObj = callRepository.findById(id).get();
        oldObj = newCall(objDto);
        //tem o mesmo id o JPA vai entender que é uma atualização
        return callRepository.save(oldObj);
    }

    private Call newCall(CallDTO objDto) {
        //getTehcnical é o id dele já
        Technical technical = technicalService.findById(objDto.getTechnical());
        //.getClient é o id, não o obj completo
        Client client = clientService.findById(objDto.getClient());

        Call call = new Call();
        if(objDto.getId() != null) {
            //entra no if para atualizar
            call.setId(objDto.getId());
        }
        //validação para poder adicionar a data de fechamendo do chamado
        if(objDto.getStatus().equals(2)) { //encerrado
            //tratando a data de fechamento
            //caso volte para o status anterior, ele volta a ficar nulo
            call.setClosingDate(LocalDate.now());
        }
        call.setTechnical(technical);
        call.setClient(client);
        //referencia o inteiro do enum
        call.setPriorit(Priority.toEnum(objDto.getPriorit()));
        call.setStatus(Status.toEnum(objDto.getStatus()));
        call.setTitle(objDto.getTitle());
        call.setObservations(objDto.getObservations());
        return call;
    }
}
