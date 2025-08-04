package br.dcx.ufpb.jefferson.springbootangularprojecthelpdesk.resources;

import br.dcx.ufpb.jefferson.springbootangularprojecthelpdesk.domain.entities.Technical;
import br.dcx.ufpb.jefferson.springbootangularprojecthelpdesk.domain.entities.dtos.TechnicalDTO;
import br.dcx.ufpb.jefferson.springbootangularprojecthelpdesk.services.TechnicalService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/technicians")
public class TechnicalResource {

    @Autowired
    private TechnicalService technicalService;

    @GetMapping
    public ResponseEntity<List<TechnicalDTO>> findAll() {
        return ResponseEntity
                .ok()
                .body(technicalService.findAll().stream()
                                .map(x -> new TechnicalDTO(x))
                        .collect(Collectors.toList()));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<TechnicalDTO> getTechnicalById(@PathVariable Integer id) {
        return ResponseEntity
                .ok()
                .body(new TechnicalDTO(technicalService.findById(id)));
    }

    @PostMapping
    public ResponseEntity<TechnicalDTO> create(@Valid @RequestBody TechnicalDTO objDTO){
        Technical newObj = technicalService.create(objDTO);
        //URI é igual a URL. é a URL de acesso da nova entidade criada
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(
                        newObj.getId()
                ).toUri();
        return ResponseEntity.created(uri).body(new TechnicalDTO(newObj));
    }
}
