package br.dcx.ufpb.jefferson.springbootangularprojecthelpdesk.resources;

import br.dcx.ufpb.jefferson.springbootangularprojecthelpdesk.domain.entities.Technical;
import br.dcx.ufpb.jefferson.springbootangularprojecthelpdesk.domain.entities.dtos.TechnicalDTO;
import br.dcx.ufpb.jefferson.springbootangularprojecthelpdesk.services.TechnicalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Controlador responsável por expor os endpoints relacionados aos Técnicos do sistema.
 */
@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping(value = "/technicians")
@Tag(name = "Técnicos",description = "Endpoins para gerenciar técnicos")
public class TechnicalResource {

    @Autowired
    private TechnicalService technicalService;

    /**
     * Lista todos os técnicos cadastrados.
     *
     * @return Lista de técnicos (DTO)
     */
    @GetMapping
    @Operation(summary = "Lista todos os técnicos cadastrados.")
    public ResponseEntity<List<TechnicalDTO>> findAll() {
        return ResponseEntity
                .ok()
                .body(technicalService.findAll().stream()
                                .map(x -> new TechnicalDTO(x))
                        .collect(Collectors.toList()));
    }

    /**
     * Retorna os dados de um técnico específico pelo ID.
     *
     * @param id ID do técnico
     * @return Dados do técnico (DTO)
     */
    @GetMapping(value = "/{id}")
    @Operation(summary = "Retorna os dados de um técnico específico pelo ID.")
    public ResponseEntity<TechnicalDTO> getTechnicalById(@PathVariable Integer id) {
        return ResponseEntity
                .ok()
                .body(new TechnicalDTO(technicalService.findById(id)));
    }

    /**
     * Cria um novo técnico.
     * Acesso restrito a usuários com o peril ADMIN.
     *
     * @param objDTO Dados do novo técnico
     * @return Técnico criado (DTO) com URI de localização
     */
    @SecurityRequirement(name = "bearerAuth")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping
    @Operation(summary = "Cria um novo técnico.")
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

    /**
     * Atualiza os dados de um técnico existente.
     * Acesso restrito a usuários com o papel ADMIN.
     *
     * @param id ID do técnico a ser atualizado
     * @param objDTO Novos dados
     * @return Técnico atualizado (DTO)
     */
    @SecurityRequirement(name = "bearerAuth")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping(value = "/{id}")
    @Operation(summary = "Atualiza os dados de um técnico existente.")
    public ResponseEntity<TechnicalDTO> update(@PathVariable Integer id,@Valid @RequestBody TechnicalDTO objDTO){
        Technical obj = technicalService.update(id,objDTO);
        return ResponseEntity.ok().body(new TechnicalDTO(obj));
    }

    /**
     * Remove um técnico pelo ID.
     * Acesso restrito a usuários com o papel ADMIN.
     *
     * @param id ID do técnico a ser removido
     * @return Sem conteúdo (204)
     */
    @SecurityRequirement(name = "bearerAuth")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Remove um técnico pelo ID.")
    public ResponseEntity<TechnicalDTO> delete(@PathVariable Integer id){
        technicalService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
