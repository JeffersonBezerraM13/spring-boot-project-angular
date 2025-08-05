package br.dcx.ufpb.jefferson.springbootangularprojecthelpdesk.resources;

import br.dcx.ufpb.jefferson.springbootangularprojecthelpdesk.domain.entities.Client;
import br.dcx.ufpb.jefferson.springbootangularprojecthelpdesk.domain.entities.dtos.ClientDTO;
import br.dcx.ufpb.jefferson.springbootangularprojecthelpdesk.services.ClientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/clients")
public class ClientResource {

    @Autowired
    private ClientService ClientService;

    @GetMapping
    public ResponseEntity<List<ClientDTO>> findAll() {
        return ResponseEntity
                .ok()
                .body(ClientService.findAll().stream()
                                .map(x -> new ClientDTO(x))
                        .collect(Collectors.toList()));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ClientDTO> getClientById(@PathVariable Integer id) {
        return ResponseEntity
                .ok()
                .body(new ClientDTO(ClientService.findById(id)));
    }

    @PostMapping
    public ResponseEntity<ClientDTO> create(@Valid @RequestBody ClientDTO objDTO){
        Client newObj = ClientService.create(objDTO);
        //URI é igual a URL. é a URL de acesso da nova entidade criada
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(
                        newObj.getId()
                ).toUri();
        return ResponseEntity.created(uri).body(new ClientDTO(newObj));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ClientDTO> update(@PathVariable Integer id,@Valid @RequestBody ClientDTO objDTO){
        Client obj = ClientService.update(id,objDTO);
        return ResponseEntity.ok().body(new ClientDTO(obj));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ClientDTO> delete(@PathVariable Integer id){
        ClientService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
