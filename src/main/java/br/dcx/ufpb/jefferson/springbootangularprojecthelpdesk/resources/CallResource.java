package br.dcx.ufpb.jefferson.springbootangularprojecthelpdesk.resources;

import br.dcx.ufpb.jefferson.springbootangularprojecthelpdesk.domain.entities.Call;
import br.dcx.ufpb.jefferson.springbootangularprojecthelpdesk.domain.entities.dtos.CallDTO;
import br.dcx.ufpb.jefferson.springbootangularprojecthelpdesk.services.CallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/calls")
public class CallResource {

    @Autowired
    private CallService callService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<CallDTO> findById (@PathVariable Integer id){
        Call obj = callService.findById(id);
        return ResponseEntity.ok(new CallDTO(obj));
    }

    @GetMapping
    public ResponseEntity<List<CallDTO>> findAll(){
        List<CallDTO> calls = callService.findAll().stream().map(c->new CallDTO(c)).collect(Collectors.toList());
        return ResponseEntity.ok(calls);
    }
}
