package br.dcx.ufpb.jefferson.springbootangularprojecthelpdesk.resources;

import br.dcx.ufpb.jefferson.springbootangularprojecthelpdesk.domain.entities.Technical;
import br.dcx.ufpb.jefferson.springbootangularprojecthelpdesk.domain.entities.dtos.TechnicalDTO;
import br.dcx.ufpb.jefferson.springbootangularprojecthelpdesk.services.TechnicalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
